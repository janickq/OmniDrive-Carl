package frc.robot.subsystems;

//Java imports

//Vendor imports
import com.kauailabs.navx.frc.AHRS;
import com.studica.frc.Cobra;
import com.studica.frc.ServoContinuous;
import com.studica.frc.TitanQuad;
//import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

//Subsystem for omnidrive
//Use individual wheel speed PID method. Ignore!!!!
public class OmniDrive2 extends SubsystemBase
{
    //Creates all necessary hardware interface here for omni-drive

    //Motors and encoders
    private final TitanQuad[] motors;
    //private final TitanQuadEncoder[] encoders;
    private final Encoder[] encoders;

    //PID stuff
    private PIDController[] pidControllers;
    private PIDController pidControllerW;
    private double[] pidInputs;
    public double pidInputW;
    private double[] pidOutputs;
    private double[] encoderDists;
    private double[] encoderDists_2;
    private double[] encoderSpeeds;
    private double curHeading, targetHeading;

    private final Servo servo;
    private final ServoContinuous servoC;
    private double dT = 0.02;

    /**
     * Sensors
     */
    private final DigitalInput input10;
    private final DigitalOutput outDebug11;
    private final Cobra cobra;
    private final Ultrasonic sonic;
    private final AnalogInput sharp;
    private final AHRS gyro;

    /**
     * Shuffleboard
     */
    private final ShuffleboardTab tab = Shuffleboard.getTab("Training");
    private final NetworkTableEntry D_sharpIR = tab.add("Sharp IR", 0).getEntry();
    private final NetworkTableEntry D_cobraRaw = tab.add("Cobra Raw", 0).getEntry();
     private final NetworkTableEntry D_navYaw = tab.add("Nav Yaw", 0).getEntry();
    private final NetworkTableEntry D_tgtHeading = tab.add("tgtHeading", 0).getEntry();
    private final NetworkTableEntry D_curHeading = tab.add("curHeading", 0).getEntry();
    private final NetworkTableEntry D_inputDisp = tab.add("Input10", false).getEntry();
    private final NetworkTableEntry D_encoderDisp0 = tab.add("Encoder0", 0).getEntry();
    private final NetworkTableEntry D_encoderDisp1 = tab.add("Encoder1", 0).getEntry();
    private final NetworkTableEntry D_encoderDisp2 = tab.add("Encoder2", 0).getEntry();
    private final NetworkTableEntry D_inputW = tab.add("inputW", 0).getEntry();

    public OmniDrive2() {
        // Motors
        input10 = new DigitalInput(10);
        outDebug11 = new DigitalOutput(11);

        //Omni drive motors
        motors = new TitanQuad[Constants.MOTOR_NUM];
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            motors[i] = new TitanQuad(Constants.TITAN_ID, i);
            motors[i].setInverted(true);   //Positive is CW. Need to reverse
        }


        //encoders = new TitanQuadEncoder[Constants.MOTOR_NUM];
        encoders = new Encoder[Constants.MOTOR_NUM];
        encoderDists = new double[Constants.MOTOR_NUM];
        encoderDists_2 = new double[Constants.MOTOR_NUM];
        encoderSpeeds = new double[Constants.MOTOR_NUM];
        
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            encoders[i] = new Encoder(i*2, i*2+1, false, Encoder.EncodingType.k4X);
            encoders[i].setDistancePerPulse(Constants.KENCODERDISTPERPULSE);
            //encoders[i] = new TitanQuadEncoder(motors[i], i, Constants.KencoderDistPerPulse);
            //encoders[i].reset();
            encoderDists[i] = encoders[i].getDistance();
        }
        
        //Wheels controller
        //Speed controler
        pidControllers = new PIDController[Constants.MOTOR_NUM];
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            pidControllers[i] = new PIDController(2.0,32.0,0.01);
        }

        //Rotational controller
        pidControllerW = new PIDController(2.0,0.0,0.1);
        pidControllerW.enableContinuousInput(-Math.PI, Math.PI);

        //Inputs and Outputs for wheel controller
        pidInputs = new double[Constants.MOTOR_NUM];
        pidOutputs = new double[Constants.MOTOR_NUM];

        servo = new Servo(Constants.SERVO);
        servoC = new ServoContinuous(Constants.SERVO_C);

        // Sensors
        cobra = new Cobra();
        sharp = new AnalogInput(Constants.SHARP);
        sonic = new Ultrasonic(Constants.SONIC_TRIGG, Constants.SONIC_ECHO);

        // gyro for rotational heading control
        gyro = new AHRS(SPI.Port.kMXP);
        gyro.zeroYaw();
        curHeading = targetHeading = getYawRad();
    }

    public double getYawRad() {
        return -gyro.getYaw()*Math.PI/180;
    }
    public Boolean getSwitch() {
        return input10.get();
    }

    /**
     * Call for the raw ADC value
     * <p>
     * 
     * @param channel range 0 - 3 (matches what is on the adc)
     * @return value between 0 and 2047 (11-bit)
     */
    public int getCobraRawValue(final int channel) {
        return cobra.getRawValue(channel);
    }

    /**
     * Call for the voltage from the ADC
     * <p>
     * 
     * @param channel range 0 - 3 (matches what is on the adc)
     * @return voltage between 0 - 5V (0 - 3.3V if the constructor Cobra(3.3F) is
     *         used)
     */
    public double getCobraVoltage(final int channel) {
        return cobra.getVoltage(channel);
    }

    /**
     * Call for the distance measured by the Sharp IR Sensor
     * <p>
     * 
     * @return value between 0 - 100 (valid data range is 10cm - 80cm)
     */
    public double getIRDistance() {
        return (Math.pow(sharp.getAverageVoltage(), -1.2045) * 27.726);
    }

    /**
     * Call for the distance measured by the Ultrasonic Sensor
     * <p>
     * 
     * @param metric true or false for metric output
     * @return distance in mm when metric is true, and inches when metric is false
     */
    public double getSonicDistance(final boolean metric) {
        sonic.ping();
        Timer.delay(0.005);
        if (metric)
            return sonic.getRangeMM();
        else
            return sonic.getRangeInches();
    }

    /**
     * Call for the current angle from the internal NavX
     * <p>
     * 
     * @return yaw angle in degrees range -180° to 180°
     */
    public double getYaw() {
        //return gyro.getYaw();
        return gyro.getRawGyroZ();
    }

    /**
     * Resets the yaw angle back to zero
     */
    public void resetGyro() {
        gyro.zeroYaw();
    }

  
    /**
     * Sets the servo angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServoAngle(final double degrees) {
        servo.setAngle(degrees);
    }


    /**
     * Sets the servo speed
     * <p>
     * 
     * @param speed sets the speed of the servo in continous mode, range -1 to 1
     */
    public void setServoSpeed(final double speed) {
        servoC.set(speed);
    }

    /**
     * Sets the speed of all motors. For testing only
     * <p>
     * 
     * @param speed range -1 to 1 (0 stop)
     */
    public void setMotorSpeedAll(final double speed)
    {
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            motors[i].set(speed);
        }
        
    }
    
    // CCW is positive
    public void setMotorSpeed012(double speed0, double speed1, double speed2)
    {
        
        motors[0].set(speed0);
        motors[1].set(speed1);
        motors[2].set(speed2);
        
    }
    
    public void setPIDInputs(double speed0, double speed1, double speed2, double speedW)
    {
        // Use position control 
        pidInputs[0] = speed0; 
        pidInputs[1] = speed1;
        pidInputs[2] = speed2;
        pidInputW = speedW; // Integrate speed in heading
    }

    /***
     * 
     * @param x - x speed in m/s
     * @param y - y speed in m/s
     * @param w - rotational speed in rad/s
     */
    public void setRobotSpeedXYW(double x, double y, double w) {

        // The x and y speed are resolved into individual wheel speed
        // 3 wheel omni drive
        // R is distance of wheel from robot centre
        // M2 = [-sin(30)  cos(30)  R]
        // M0 = [-sin(150) cos(150) R] * [x y w]
        // M1 = [-sin(270) cos(270) R]

        double R = 0.0; //We are only interested in the x,y speed for the wheels
        
        double speed2 = (-0.5*x + 0.866025*y + R*w);
        double speed0 = (-0.5*x - 0.866025*y + R*w);
        double speed1 = ( x     + 0          + R*w);

        // The rotation speed, w, is controlled separately through the gyro
        setPIDInputs(speed0, speed1, speed2, w);
    }

    public void doPID( ){
        //This is for translational speed PID
        double dcValue = 0.0;
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            encoderDists[i] = encoders[i].getDistance();
            encoderSpeeds[i] = (encoderDists[i]-encoderDists_2[i])/dT;
            dcValue += encoderSpeeds[i];
            encoderDists_2[i] = encoderDists[i];
        }
        dcValue /= 3;  //This represents the rotational component of the encoder feedback
        for (int i=0; i<Constants.MOTOR_NUM; i++) {

            //Subtract rotational component from encoder speed
            //Rotational PID is handled by gyro separately.
            //Maybe good to combine this dc value with gyro value??????
            encoderSpeeds[i] -= dcValue;  

            //PID control for each wheel
            pidOutputs[i] = pidControllers[i].calculate(encoderSpeeds[i], pidInputs[i]);
            pidOutputs[i] = Math.min(pidOutputs[i], 1.0);
            pidOutputs[i] = Math.max(pidOutputs[i], -1.0);
        }
        
        //This is for rotational speed PID
        //This uses position control. Position for input and feedback
        curHeading = getYawRad();
        
        targetHeading += pidInputW*dT;   
        //Limit targetHeading to -Pi to +Pi
        if (targetHeading>Math.PI) targetHeading = -Math.PI*2 + targetHeading;
        if (targetHeading<-Math.PI) targetHeading = Math.PI*2 + targetHeading;

        double pidOutputW = pidControllerW.calculate(curHeading, targetHeading);

        MathUtil.clamp(pidOutputW, -1.0, 1.0);

        //Combine rotational and translational outputs to drive motors
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            //Need to limit to -1 to + 1 ???????
             motors[i].set(pidOutputs[i] + pidOutputW);
        }   
    }
    /**
     * Code that runs once every robot loop
     */
    int initCnt=0;
    @Override
    public void periodic()
    {
        System.out.print("obj");
        if (initCnt<1) {
            initCnt++;
            gyro.zeroYaw();
            curHeading = targetHeading = getYawRad();
            return;
        }
        outDebug11.set(true);
        //
        //encoders[0].getEncoderDistance();

        doPID();
        //setServoAngle(D_servoPos.getDouble(0.0));
        //setMotorSpeedAll(D_motorSpeed.getDouble(0.0));
        /**
         * Updates for outputs to the shuffleboard
         */
        D_inputDisp.setBoolean(getSwitch());
        D_sharpIR.setDouble(getIRDistance());
        //D_ultraSonic.setDouble(getSonicDistance(true)); //set to true because we want metric
        //double s0 = getCobraRawValue(0);
        //double s1 = getCobraRawValue(1);
        //double s2 = getCobraRawValue(2);
       // double s3 = getCobraRawValue(3);
        //double offset = (s0*-3.0 + s1*-1.0 + s2*1.0 + s3*3.0)/(s0+s1+s2+s3);
        D_cobraRaw.setDouble(0); //Just going to use channel 0 for demo

        //D_cobraVoltage.setDouble(getCobraVoltage(0));
        D_curHeading.setDouble(curHeading*180/Math.PI);
        D_tgtHeading.setDouble(targetHeading*180/Math.PI);
        D_navYaw.setDouble(-gyro.getYaw());
        D_encoderDisp0.setDouble(encoders[0].getRaw());//encoderSpeeds[0]);
        D_encoderDisp1.setDouble(encoders[1].getDistance());//encoderSpeeds[1]);
        D_encoderDisp2.setDouble(encoders[2].getDistance());//encoderSpeeds[2]);
        D_inputW.setDouble(pidInputW);
        outDebug11.set(false);
    }
}
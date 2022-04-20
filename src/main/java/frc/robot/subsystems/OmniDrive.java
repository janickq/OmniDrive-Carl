package frc.robot.subsystems;

//Java imports

//Vendor imports
import com.kauailabs.navx.frc.AHRS;
import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.MedianFilter;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
import frc.robot.utils.OmniDriveOdometry;

public class OmniDrive extends SubsystemBase
{

    private final static Points m_points = RobotContainer.m_points;
    //Motors and encoders
    private final TitanQuad[] motors;
    private final TitanQuadEncoder[] encoders;
    //vmx private final Encoder[] encoders;  //VMX encoder

    //PID stuff
    private PIDController[] pidControllers;
    private double[] pidInputs;
    private double[] pidOutputs;
    private double[] encoderDists;
    private double[] encoderDists_2;
    private double[] encoderSpeeds;
    private double[] wheelSpeeds;
    private double curHeading, targetHeading;
    private double[] motorOuts;
    private double headingDiv;
    private double[] pidFBs;
    private final OmniDriveOdometry m_odometry;
    //For testing. These should be in another subsystem
    private double pid_dT = Constants.PID_DT;
    LinearFilter filter3 = LinearFilter.movingAverage(20);
    MedianFilter filter1 = new MedianFilter(10);
    MedianFilter filter2 = new MedianFilter(10);
    DigitalOutput debugout1 = new DigitalOutput(8);
    // Sensors

    private final AHRS gyro;

    //Odometry



    // Shuffleboard
    private final ShuffleboardTab tab = Shuffleboard.getTab("OmniDrive");

    private final NetworkTableEntry D_navYaw = tab.add("Nav Yaw", 0).getEntry();
    private final NetworkTableEntry D_curHeading = tab.add("curHeading", 0).getEntry();
    private final NetworkTableEntry D_tgtHeading = tab.add("tgtHeading", 0).getEntry();
    private final NetworkTableEntry D_encoderDisp0 = tab.add("Encoder0", 0).getEntry();
    private final NetworkTableEntry D_encoderDisp1 = tab.add("Encoder1", 0).getEntry();
    private final NetworkTableEntry D_encoderDisp2 = tab.add("Encoder2", 0).getEntry();
    private final NetworkTableEntry D_encoderDist0 = tab.add("Encoder0dist", 0).getEntry();
    private final NetworkTableEntry D_encoderDist1 = tab.add("Encoder1dist", 0).getEntry();
    private final NetworkTableEntry D_encoderDist2 = tab.add("Encoder2dist", 0).getEntry();
    private final NetworkTableEntry D_inputW = tab.add("inputW", 0).getEntry();
    private final NetworkTableEntry D_encoderPidOut0 = tab.add("pidout0", 0).getEntry();
    private final NetworkTableEntry D_encoderPidOut1 = tab.add("pidout1", 0).getEntry();
    private final NetworkTableEntry D_encoderPidOut2 = tab.add("pidout2", 0).getEntry();
    private final NetworkTableEntry D_odoX = tab.add("odoX", 0).getEntry();
    private final NetworkTableEntry D_odoY = tab.add("odoY", 0).getEntry();
    private final NetworkTableEntry D_odoW = tab.add("odoW", 0).getEntry();
    private final NetworkTableEntry D_Compass = tab.add("Compass", 0).getEntry();
    private final NetworkTableEntry D_HeadingError = tab.add("HeadingError", 0).getEntry();
    private final NetworkTableEntry D_referencePose = tab.add("poseendflag", 0).getEntry();
    private final NetworkTableEntry D_referenceAngle = tab.add("ReferenceAngle", 0).getEntry();
    //Subsystem for omnidrive
    public OmniDrive() {


        //Omni drive motors
        motors = new TitanQuad[Constants.MOTOR_NUM];
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            motors[i] = new TitanQuad(Constants.TITAN_ID, i);
            motors[i].setInverted(true);   //Positive is CW. Need to reverse
        }


        encoders = new TitanQuadEncoder[Constants.MOTOR_NUM];
        //vmx encoders = new Encoder[Constants.MOTOR_NUM];
        encoderDists = new double[Constants.MOTOR_NUM];
        encoderDists_2 = new double[Constants.MOTOR_NUM];
        encoderSpeeds = new double[Constants.MOTOR_NUM];
        wheelSpeeds = new double[Constants.MOTOR_NUM];
        motorOuts = new double[Constants.MOTOR_NUM];

        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            //vmx encoders[i] = new Encoder(i*2, i*2+1, false, Encoder.EncodingType.k4X);
            //vmx encoders[i].setDistancePerPulse(Constants.KENCODERDISTPERPULSE);
            //vmx encoderDists[i] = encoders[i].getDistance();
            encoders[i] = new TitanQuadEncoder(motors[i], i, Constants.KENCODERDISTPERPULSE);
            encoders[i].reset();
            encoderDists_2[i] =encoderDists[i] = encoders[i].getEncoderDistance();
        }
        
        // x, y and w speed controler
        pidControllers = new PIDController[Constants.PID_NUM];
        //Speed control
        pidControllers[0] = new PIDController(1.4, 12.0, 0.0, pid_dT);  //x
        pidControllers[1] = new PIDController(1.4, 12.0, 0.0, pid_dT);  //y 2.0,32.0,0.02
        pidControllers[2] = new PIDController(2.5,0.0,0.1, pid_dT);    //w
        pidControllers[2].enableContinuousInput(-Math.PI, Math.PI);

        //Inputs and Outputs for wheel controller
        pidInputs = new double[Constants.PID_NUM];
        pidOutputs = new double[Constants.PID_NUM];
        pidFBs = new double[Constants.PID_NUM];
        // gyro for rotational heading control
        gyro = new AHRS(SPI.Port.kMXP);
        gyro.zeroYaw();


        m_odometry = new OmniDriveOdometry( Layout.Convert_mm_Pose2d(Layout.startPos));


    }


    public void updateOdom(){
        SmartDashboard.putNumber("OdomX", m_odometry.getPose().getTranslation().getX());
        SmartDashboard.putNumber("OdomY", m_odometry.getPose().getTranslation().getY());
        SmartDashboard.putNumber("OdomW", m_odometry.getPose().getRotation().getRadians());
    }


    public double getYawRad() {
        return -gyro.getYaw()*Math.PI/180;
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
        curHeading = targetHeading = getYawRad();
    }

    public void getKinematics(){

    }

    public void resetHeading() {
        curHeading = targetHeading = getYawRad();
    }
    /**
     * Sets the speed of the motor
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

    public void compensateGyro() {

        //0 - 1:34
        //0.75 - 3:35
    }
    


    public void resetPose() {
        m_odometry.resetPosition(Globals.referencePose);
    }

    public void setZeroPose() {
        m_odometry.resetPosition(m_points.getPoint("jigOffset"));
    }

    public Pose2d getPose() {
        return m_odometry.getPose();
    }
    
    /***
     * 
     * @param x - x speed in m/s
     * @param y - y speed in m/s
     * @param w - rotational speed in rad/s
     */
    public void setRobotSpeedXYW(double x, double y, double w) {
        pidInputs[0] = x; 
        pidInputs[1] = y;
        pidInputs[2] = w; 
    }
    public void setRobotSpeedType(int type, double speed) {
        pidInputs[type] = speed; 
    }
    

    public void doPID( ){
        debugout1.set(true);
        //This is for translational speed PID
        //First calculate wheel speed from encoder feedback
        double dcValue = 0.0;
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            //vmx encoderDists[i] = encoders[i].getDistance();
            encoderDists[i] = encoders[i].getEncoderDistance();
            wheelSpeeds[i] = encoderSpeeds[i] = (encoderDists[i]-encoderDists_2[i])/pid_dT;
            encoderDists_2[i] = encoderDists[i];
            //encoders[i].getSpeed() in rpm
            //wheelSpeeds[i] = encoderSpeeds[i] = -encoders[i].getSpeed()*Math.PI*0.1/60;
            dcValue += wheelSpeeds[i];
        }

        //Subtract rotational component from encoder speed
        //Rotational PID is handled by gyro separately.
        //Maybe good to combine this dc value with gyro value??????
        dcValue /= 3;
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            wheelSpeeds[i] -= dcValue;
        }

        //Estimates x and y speed from individual wheel speeds
        //See formula below
        double speedX = (-(wheelSpeeds[0] + wheelSpeeds[2]) + wheelSpeeds[1])/2;
        double speedY = (-wheelSpeeds[0] + wheelSpeeds[2])/(0.866025*2);
        pidFBs[0] = speedX;
        pidFBs[1] = speedX;

        //PID control for x and y speed
        //Speed control + feedforward
        pidOutputs[0] = pidControllers[0].calculate(speedX, pidInputs[0]) + pidInputs[0]*0.0;
        pidOutputs[1] = pidControllers[1].calculate(speedY, pidInputs[1]) + pidInputs[1]*0.0;
        
        //Translate x and y output to wheel outputs
        // The x and y speed are resolved into individual wheel speed
        // 3 wheel omni drive
        // R is distance of wheel from robot centre
        // M0 = [-sin(150) cos(150) R] * [x y w]    //Left-front wheel
        // M1 = [-sin(270) cos(270) R]              //Back wheel
        // M2 = [-sin(30)  cos(30)  R]              //Right-front wheel
        motorOuts[0] = (-0.5*pidOutputs[0] - 0.866025*pidOutputs[1]);
        motorOuts[1] = (     pidOutputs[0] + 0               );
        motorOuts[2] = (-0.5*pidOutputs[0] + 0.866025*pidOutputs[1]);
        
        /////////////////////////////////////////////////////////////////////////////////////////
        //This is for rotational speed PID
        /////////////////////////////////////////////////////////////////////////////////////////
        curHeading = getYawRad();
        
        targetHeading += pidInputs[2]*pid_dT;   

        //Limit targetHeading to -Pi to +Pi
        if (targetHeading>Math.PI) targetHeading -= Math.PI*2;
        if (targetHeading<-Math.PI) targetHeading += Math.PI*2;
        pidFBs[2] = targetHeading-curHeading;
        if (pidFBs[2]>Math.PI) pidFBs[2] -= Math.PI*2;
        if (pidFBs[2]<-Math.PI) pidFBs[2] += Math.PI*2;
        pidFBs[2] /= pid_dT;

        pidOutputs[2] = pidControllers[2].calculate(curHeading, targetHeading);

        //Limit output to -1.0 to 1.0 as PID outputs may be greater then 1.0
        double max=1.0;
        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            motorOuts[i] += pidOutputs[2];          // add w component
            max = Math.max(max, Math.abs(motorOuts[i]));
        }

        for (int i=0; i<Constants.MOTOR_NUM; i++) {
            motors[i].set(motorOuts[i]/max);
            //motors[i].set(0);   //off motor to test encoders manually
        }   

        //Use PIDInputs for odometry?
        //m_odometry.update(pidFBs[0]*pid_dT, pidFBs[1]*pid_dT, pidFBs[2]*pid_dT);
        m_odometry.update(pidInputs[0]*pid_dT, pidInputs[1]*pid_dT, pidInputs[2]*pid_dT);
        updateOdom();
        //Use feedback signal. Should be more accurate?

        debugout1.set(false);
   }
    /**
     * Code that runs once every robot loop
     */
    int initCnt=0;
    @Override
    public void periodic()
    {
  
        if (initCnt<20) {
            initCnt++;
            gyro.zeroYaw();
            curHeading = targetHeading = getYawRad();
            return;
        }

        if (!Constants.PID_THREAD ) {
            doPID();
        }
        D_curHeading.setDouble(curHeading);
        D_curHeading.setDouble(curHeading*180/Math.PI);
        D_tgtHeading.setDouble(targetHeading*180/Math.PI);
        D_navYaw.setDouble(-gyro.getYaw());
        D_encoderDisp0.setDouble(encoderSpeeds[0]);
        D_encoderDisp1.setDouble(encoderSpeeds[1]);
        D_encoderDisp2.setDouble(encoderSpeeds[2]);
        D_encoderDist0.setDouble(encoderDists[0]);
        D_encoderDist1.setDouble(encoderDists[1]);
        D_encoderDist2.setDouble(encoderDists[2]);
        D_encoderPidOut0.setDouble(pidInputs[0]);
        D_encoderPidOut1.setDouble(pidInputs[1]);
        D_encoderPidOut2.setDouble(pidInputs[2]);
        D_odoX.setDouble(m_odometry.getPose().getTranslation().getX());
        D_odoY.setDouble(m_odometry.getPose().getTranslation().getY());
        D_odoW.setDouble(m_odometry.getPose().getRotation().getRadians());

    }
}
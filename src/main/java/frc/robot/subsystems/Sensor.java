package frc.robot.subsystems;

//Java imports

//Vendor imports

import com.studica.frc.Cobra;
import com.studica.frc.ServoContinuous;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Sensor extends SubsystemBase
{
    //Creates all necessary hardware interface here for omni-drive


    //For testing. These should be in another subsystem
    private final Servo servo;
    private final ServoContinuous servoC;

    // Sensors
    private final DigitalInput input10;
    private final Cobra cobra;
    //private final Ultrasonic sonic;
    private final AnalogInput sharp;

    // Shuffleboard
    private final ShuffleboardTab tab = Shuffleboard.getTab("Sensors");
    //private final NetworkTableEntry D_servoPos = tab.add("Servo Position", 0).withWidget(BuiltInWidgets.kNumberSlider)
    //       .withProperties(Map.of("min", 0, "max", 300)).getEntry();
    private final NetworkTableEntry D_sharpIR = tab.add("Sharp IR", 0).getEntry();
    //private final NetworkTableEntry D_ultraSonic = tab.add("Ultrasonic", 0).getEntry();
    private final NetworkTableEntry D_cobraRaw = tab.add("Cobra Raw", 0).getEntry();
    //private final NetworkTableEntry D_cobraVoltage = tab.add("Cobra Voltage", 0).getEntry();
    private final NetworkTableEntry D_inputDisp = tab.add("inputDisp", 0).getEntry();

    //Subsystem for omnidrive
    public Sensor() {
        
        input10 = new DigitalInput(10);

        servo = new Servo(Constants.SERVO);
        servoC = new ServoContinuous(Constants.SERVO_C);

        // Sensors
        cobra = new Cobra();
        sharp = new AnalogInput(Constants.SHARP);
        //sonic = new Ultrasonic(Constants.SONIC_TRIGG, Constants.SONIC_ECHO);

    }

    public Boolean getSwitch() {
        return input10.get();
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
     * Code that runs once every robot loop
     */
    @Override
    public void periodic()
    {

        D_inputDisp.setBoolean(getSwitch());
        D_sharpIR.setDouble(getIRDistance());
        //D_ultraSonic.setDouble(getSonicDistance(true)); //set to true because we want metric
        double s0 = getCobraRawValue(0);
        //double s1 = getCobraRawValue(1);
        //double s2 = getCobraRawValue(2);
       // double s3 = getCobraRawValue(3);
        //double offset = (s0*-3.0 + s1*-1.0 + s2*1.0 + s3*3.0)/(s0+s1+s2+s3);
        D_cobraRaw.setDouble(s0); //Just going to use channel 0 for demo

        //D_cobraVoltage.setDouble(getCobraVoltage(0));
    }
}
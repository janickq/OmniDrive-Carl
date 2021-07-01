package frc.robot.subsystems;

//Java imports
import java.util.Map;

//Vendor imports
import com.kauailabs.navx.frc.AHRS;
import com.studica.frc.Cobra;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;

public class Sensor extends SubsystemBase
{


    double cobraValue[];

    //private final ServoContinuous servoC;

    /**
     * Sensors
     */
    //private final DigitalInput input11;
    private final Cobra cobra;
    private final Ultrasonic sonic1;
    // private final Ultrasonic sonic2;
    private final AnalogInput sharp1;
    private final AnalogInput sharp2;
    private final AHRS gyro;

    /**
     * Shuffleboard
     */

    private final ShuffleboardTab tab = Shuffleboard.getTab("Sensor");
    private final NetworkTableEntry D_servoPos = tab.add("Servo Position", 0).withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", 0, "max", 300)).getEntry();
    private final NetworkTableEntry D_motorSpeed = tab.add("Motor Speed", 0).withWidget(BuiltInWidgets.kNumberSlider)
    .withProperties(Map.of("min", -1, "max", 1)).getEntry();
    private final NetworkTableEntry D_sharpIR1 = tab.add("Sharp IR 1", 0).getEntry();
    private final NetworkTableEntry D_sharpIR2 = tab.add("Sharp IR 2", 0).getEntry();
    private final NetworkTableEntry D_ultraSonic1 = tab.add("Ultrasonic1", 0).getEntry();
    private final NetworkTableEntry D_ultraSonic2 = tab.add("Ultrasonic2", 0).getEntry();
    private final NetworkTableEntry D_cobraRaw = tab.add("Cobra Raw", 0).getEntry();
    private final NetworkTableEntry D_cobraVoltage = tab.add("Cobra Voltage", 0).getEntry();
    private final NetworkTableEntry D_cobra1 = tab.add("cobra1", 0).getEntry();
    private final NetworkTableEntry D_cobra2 = tab.add("cobra2", 0).getEntry();
    private final NetworkTableEntry D_cobra3 = tab.add("cobra3", 0).getEntry();
    private final NetworkTableEntry D_cobra4 = tab.add("cobra4", 0).getEntry();
    private final NetworkTableEntry D_globals = tab.add("Globals", 0).getEntry();
    private final NetworkTableEntry D_globalstate = tab.add("Globalstate", 0).getEntry();
    private final NetworkTableEntry D_Compass = tab.add("Compass", 0).getEntry();
    

    public Sensor() {
        cobraValue = new double[4];


        // Sensors
        cobra = new Cobra();
        sharp1 = new AnalogInput(Constants.SHARP1);
        sharp2 = new AnalogInput(Constants.SHARP2);
        sonic1 = new Ultrasonic(Constants.SONIC_TRIGG1, Constants.SONIC_ECHO1);
        // sonic2 = new Ultrasonic(Constants.SONIC_TRIGG2, Constants.SONIC_ECHO2);
        gyro = new AHRS(SPI.Port.kMXP);
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
    public double getIRDistance1() {
        return (Math.pow(sharp1.getAverageVoltage(), -1.2045) * 27.726);
    }

    public double getIRDistance2() {
        return (Math.pow(sharp2.getAverageVoltage(), -1.2045) * 27.726);
    }

    /**
     * Call for the distance measured by the Ultrasonic Sensor
     * <p>
     * 
     * @param metric true or false for metric output
     * @return distance in mm when metric is true, and inches when metric is false
     */
    public double getSonicDistance1(final boolean metric) {
        sonic1.ping();
        Timer.delay(0.005);
        if (metric)
            return sonic1.getRangeMM();
        else
            return sonic1.getRangeInches();
    }
    // public double getSonicDistance2(final boolean metric) {
    //     sonic2.ping();
    //     Timer.delay(0.005);
    //     if (metric)
    //         return sonic2.getRangeMM();
    //     else
    //         return sonic2.getRangeInches();
    // }


  



    /**
     * Sets the servo speed
     * <p>
     * 
     * @param speed sets the speed of the servo in continous mode, range -1 to 1
     */
    public void setServoSpeed(final double speed) {
        //servoC.set(speed);
    }

    public double getCobraTotal()
    {
        return (cobraValue[0]+cobraValue[1]+cobraValue[2]+cobraValue[3]);
    }

    public double offset()
    {
        return (cobraValue[0]-30.0 + cobraValue[1]-5.0 + cobraValue[2]*5.0 + cobraValue[3]*30.0)/
        (cobraValue[0]+ cobraValue[1]+ cobraValue[2]+ cobraValue[3]);
        
    }
    public static double x = 0;
    @Override
    public void periodic()
    {
        

        

        
        //setServo1Angle(D_servoPos.getDouble(0.0));
        //setServo2Angle(D_servoPos.getDouble(0.0));
        //setMotorSpeedAll(D_motorSpeed.getDouble(0.0));
        /**
         * Updates for outputs to the shuffleboard
         */

         if( x%2 == 0){
             D_sharpIR2.setDouble(getIRDistance2());
             D_sharpIR1.setDouble(getIRDistance1());
             D_ultraSonic1.setDouble(getSonicDistance1(true)); //set to true because we want metric
            //  D_ultraSonic2.setDouble(getSonicDistance2(true));
         }

         else{
             D_Compass.setDouble(gyro.getCompassHeading());
             for(int i=0; i<4; i++) {
                 cobraValue[i] = getCobraRawValue(i);
             }
             D_cobra1.setDouble(cobraValue[0]);
             D_cobra2.setDouble(cobraValue[1]);
             D_cobra3.setDouble(cobraValue[2]);
             D_cobra4.setDouble(cobraValue[3]);
         }
         x++;
        // D_cobraRaw.setDouble(offset()); //Just going to use channel 0 for demo
        // D_cobraVoltage.setDouble(getCobraVoltage(0));

        // D_globals.setDouble(Globals.distCount);
        // D_globals.setBoolean(Globals.endFlag);
        // D_globalstate.setNumber(Globals.cmdState);
    }
}
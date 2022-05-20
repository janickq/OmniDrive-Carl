package frc.robot.subsystems;

//Java imports
import java.util.Map;

import com.studica.frc.Cobra;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.MedianFilter;
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
    MedianFilter filter1 = new MedianFilter(10);
    MedianFilter filter2 = new MedianFilter(10);
    MedianFilter filter3 = new MedianFilter(10);
    MedianFilter filter4 = new MedianFilter(10);
    MedianFilter filter5 = new MedianFilter(10);
    MedianFilter filter6 = new MedianFilter(10);
    MedianFilter filter7 = new MedianFilter(10);
    MedianFilter filter8 = new MedianFilter(10);
    MedianFilter filter9 = new MedianFilter(10);
    MedianFilter filter10 = new MedianFilter(10);

    double cobraValue[];

    //private final ServoContinuous servoC;

    /**
     * Sensors
     */
    //private final DigitalInput input11;
    // private final Cobra cobra;
    // private final Ultrasonic sonic1;
    // private final Ultrasonic sonic2;
    // private final AnalogInput sharp1;
    // private final AnalogInput sharp2;
    // private final AnalogInput sharp3;


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
    private final NetworkTableEntry D_sharpIR3 = tab.add("Sharp IR 3", 0).getEntry();
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

    

    public Sensor() {

        // cobraValue = new double[4];

        // Sensors
        // cobra = new Cobra();
        // sharp1 = new AnalogInput(Constants.SHARP1);
        // sharp2 = new AnalogInput(Constants.SHARP2);
        // sharp3 = new AnalogInput(Constants.SHARP3);
        // sonic1 = new Ultrasonic(Constants.SONIC_TRIGG1, Constants.SONIC_ECHO1);
        // sonic2 = new Ultrasonic(Constants.SONIC_TRIGG2, Constants.SONIC_ECHO2);

    }


    // /**
    //  * Call for the raw ADC value
    //  * <p>
    //  * 
    //  * @param channel range 0 - 3 (matches what is on the adc)
    //  * @return value between 0 and 2047 (11-bit)
    //  */
    // public double getCobraRawValue(final int channel) {
    //     return cobra.getRawValue(channel);
    // }

    // /**
    //  * Call for the voltage from the ADC
    //  * <p>
    //  * 
    //  * @param channel range 0 - 3 (matches what is on the adc)
    //  * @return voltage between 0 - 5V (0 - 3.3V if the constructor Cobra(3.3F) is
    //  *         used)
    //  */
    // public double getCobraVoltage(final int channel) {
    //     return cobra.getVoltage(channel);
    // }

    // /**
    //  * Call for the distance measured by the Sharp IR Sensor
    //  * <p>
    //  * 
    //  * @return value between 0 - 100 (valid data range is 10cm - 80cm)
    //  */
    // public double getIRDistance1() {
    //     return filter2.calculate((Math.pow(sharp1.getAverageVoltage(), -1.2045) * 27.726));
    // }

    // public double getIRDistance2() {
    //     return filter3.calculate(Math.pow(sharp2.getAverageVoltage(), -1.2045) * 27.726);
    // }

    // public double getIRDistance3() {
    //     return filter4.calculate((Math.pow(sharp3.getAverageVoltage(), -1.2045) * 27.726));
    // }

    // /**
    //  * Call for the distance measured by the Ultrasonic Sensor
    //  * <p>
    //  * 
    //  * @param metric true or false for metric output
    //  * @return distance in mm when metric is true, and inches when metric is false
    //  */
    // public double getSonicDistance1() {


    //     return Globals.UltrasonicDistance1;

    // }
    // public double getSonicDistance2() {


    //     return Globals.UltrasonicDistance2;

    // }
    // // public double getSonicDistance2(final boolean metric) {
    // //     sonic2.ping();
    // //     Timer.delay(0.005);
    // //     if (metric)
    // //         return sonic2.getRangeMM();
    // //     else
    // //         return sonic2.getRangeInches();
    // // }


  



    // /**
    //  * Sets the servo speed
    //  * <p>
    //  * 
    //  * @param speed sets the speed of the servo in continous mode, range -1 to 1
    //  */
    // public void setServoSpeed(final double speed) {
    //     //servoC.set(speed);
    // }

    // public double getCobraTotal()
    // {
    //     return (cobraValue[0]+cobraValue[1]+cobraValue[2]+cobraValue[3]);
    // }

    // public double offset()
    // {
    //     return (cobraValue[0]-30.0 + cobraValue[1]-5.0 + cobraValue[2]*5.0 + cobraValue[3]*30.0)/
    //     (cobraValue[0]+ cobraValue[1]+ cobraValue[2]+ cobraValue[3]);
        
    // }

    public boolean flag = false;
    
    @Override
    public void periodic()
    {
        
        /**
         * Updates for outputs to the shuffleboard
         */

        //  if(!flag){
        //      D_sharpIR2.setDouble(getIRDistance2());
        //      D_sharpIR1.setDouble(getIRDistance1());
        //      D_sharpIR3.setDouble(getIRDistance3());
        //      sonic1.ping();
        //      sonic2.ping();
        //      cobraValue[0] = getCobraRawValue(0);
        //      cobraValue[1] = getCobraRawValue(1);
        //      flag = true;
        //  }

        //  else {
        //      Globals.UltrasonicDistance1 = filter5.calculate(sonic1.getRangeMM());
        //      Globals.UltrasonicDistance2 = filter8.calculate(sonic2.getRangeMM());
        //      D_ultraSonic1.setNumber(getSonicDistance1());
        //      D_ultraSonic2.setNumber(getSonicDistance2());
        //      //  for(int i=0; i<4; i++) {
        //      //      cobraValue[i] = getCobraRawValue(i);
        //      //  }
        //      cobraValue[2] = getCobraRawValue(2);
        //      cobraValue[3] = getCobraRawValue(3);
        //      flag = false;

        //  }

        //  D_cobra1.setDouble(cobraValue[0]);
        //  D_cobra2.setDouble(cobraValue[1]);
        //  D_cobra3.setDouble(cobraValue[2]);
        //  D_cobra4.setDouble(cobraValue[3]);

    }
}
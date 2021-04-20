package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Globals;
import com.studica.frc.Servo;
//import edu.wpi.first.wpilibj.Servo;
import com.studica.frc.ServoContinuous;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Arm extends SubsystemBase
{

    private final Servo servo1;
    private final Servo servo2;
    private final ServoContinuous servoC;


    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_SetPoint = tab.add("SetPoint", 0).getEntry();
    private final NetworkTableEntry D_CurrentAngle = tab.add("CurrentAngle", 0).getEntry();
    private final NetworkTableEntry D_Goal = tab.add("Goal", 0).getEntry();

    public Arm(){

        servo1 = new Servo(Constants.SERVO1);
        servo2 = new Servo(Constants.SERVO2);
        servoC = new ServoContinuous(Constants.SERVO_C);
        setServo1Angle(Globals.curAngle);
        
    }

        /**
     * Sets the servo angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServo1Angle(final double degrees) {
        servo1.setAngle(degrees);
    }

   

    public void setServo2Angle(final double degrees) {
        servo2.setAngle(degrees);
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

    @Override
    public void periodic(){
        D_CurrentAngle.setDouble(Globals.curAngle);
        D_SetPoint.setDouble(Globals.debug1);
        D_Goal.setDouble(Globals.debug2);
    }

}
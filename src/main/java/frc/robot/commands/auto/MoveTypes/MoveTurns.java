package frc.robot.commands.auto.MoveTypes;

import frc.robot.commands.auto.AutoCommand;
// import the commands
import frc.robot.commands.auto.MoveRobot;;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveTurns extends AutoCommand
{   
    public static double turnSpeed = 0.4;
    public MoveTurns()
    {
       
        super(
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI)  
  
            );
    }
}

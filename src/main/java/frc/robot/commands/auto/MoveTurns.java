package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import the commands
import frc.robot.commands.auto.MoveRobot;;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveTurns extends SequentialCommandGroup
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

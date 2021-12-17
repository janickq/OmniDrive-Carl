package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import the commands
import frc.robot.commands.auto.MoveRobot;;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveCurve extends SequentialCommandGroup
{   
    public static double turnSpeed = 0.4;
    public MoveCurve()
    {
       
        super(
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(1, 0.25, 0, turnSpeed, 0.5),  
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(1, 0.25, turnSpeed, turnSpeed, 0.5),
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(1, 0.25, turnSpeed, turnSpeed, 0.5),  
            new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),  
            new MoveRobot(1, 0.25, turnSpeed, 0, 0.5)  
            );
    }
}

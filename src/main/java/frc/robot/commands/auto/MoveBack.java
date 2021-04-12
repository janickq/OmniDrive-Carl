package frc.robot.commands.auto;

// import the commands
import frc.robot.commands.auto.MoveRobot;;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveBack extends AutoCommand
{
    public MoveBack()
    {
        super(
            new MoveRobot(2, Math.PI, 0, 0, Math.PI),  
            new MoveRobot(1, 0.5, 0, 0, 0.5),
            new MoveRobot(2, -Math.PI, 0, 0, Math.PI)
            );
    }
}

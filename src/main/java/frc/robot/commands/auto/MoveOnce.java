package frc.robot.commands.auto;

// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.RobotTest;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveOnce extends AutoCommand
{
    
    /**
     * Constructor
     */
    public MoveOnce()
    {
        /**
         * Calls SimpleDrive at a speed of 50% waits 5 seconds and stops the motors
         */
        super(new RobotTest(0.5));
    }
}
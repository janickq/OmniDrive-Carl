package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//WPI imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * AutoCommand Class
 * <p>
 * This class is used to create the inline command stackup for autonomous routines
 */
public abstract class AutoCommand extends SequentialCommandGroup
{
    /**
     * Base Constructor
     */
    public AutoCommand()
    {
        super();
    }

    /**
     * Overloaded Constructor to create inline commands
     * <p>
     * @param cmd The cmd to be executed
     */
    public AutoCommand(Command ... cmd)
    {
        super(cmd);

    }

    @Override
    public void execute() {
        SmartDashboard.putString("Current Command", super.getName());
    }

}
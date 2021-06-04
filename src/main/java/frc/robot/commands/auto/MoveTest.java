package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.Globals;


/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveTest extends AutoCommand
{
    private enum CommandSelector {
        ONE, TWO, THREE, FOUR
    }

    static public CommandSelector selectCmd123() {
        if (Globals.cmdState == 1)
            return CommandSelector.ONE;
        else if (Globals.cmdState == 2)
            return CommandSelector.TWO;
        else if (Globals.cmdState == 3)
            return CommandSelector.THREE;
        else
            return CommandSelector.FOUR;
    }

	public MoveTest()
    {

        super(
            // Select one of many commands
            // Selection command in selectCmd123
            new SelectCommand(
                Map.ofEntries(
                    Map.entry(CommandSelector.ONE, new SequentialMove()),
                    Map.entry(CommandSelector.TWO, new SequentialMove()),
                    Map.entry(CommandSelector.THREE, new SequentialMove()),
                    Map.entry(CommandSelector.FOUR, new SequentialMove()) ),
                MoveTest::selectCmd123

                //can use clearGroupedCommands() to reuse commands
            ) 
        );
    }
}
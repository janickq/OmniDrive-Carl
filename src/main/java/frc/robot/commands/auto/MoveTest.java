package frc.robot.commands.auto;

import java.util.Map;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobotSense;
import frc.robot.commands.auto.MoveTypes.MoveBack;
import frc.robot.commands.auto.MoveTypes.MoveCurve;
import frc.robot.commands.auto.MoveTypes.MoveLeft;
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
            //Select one of many commands
            //Selection command in selectCmd123
            new SelectCommand(
                Map.ofEntries(
                    Map.entry(CommandSelector.ONE, new MoveLeft()),
                    Map.entry(CommandSelector.TWO, new MoveBack()),
                    Map.entry(CommandSelector.THREE, new MoveBack()),
                    Map.entry(CommandSelector.FOUR, new MoveCurve()) ),
                MoveTest::selectCmd123

                //can use clearGroupedCommands() to reuse commands
            ) 
        );
    }
}
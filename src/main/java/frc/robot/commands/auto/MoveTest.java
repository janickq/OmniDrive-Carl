package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveTest extends AutoCommand {
    private final static Vision m_vision = RobotContainer.m_vision;

    private enum CommandSelector {
        CHIPS, NISSIN, KITKAT, BALL, END;
    }

    static public CommandSelector selectCmd123() {
        if (Globals.chipsx != 1 || Globals.chipsy != 1)
            return CommandSelector.CHIPS;
        else if (Globals.nissinx != 0 || Globals.nissiny != 0)
            return CommandSelector.NISSIN;
        else if (Globals.kitkatx != 0 || Globals.kitkaty != 0)
            return CommandSelector.KITKAT;
        else if (Globals.ballx != 0 || Globals.bally != 0)
            return CommandSelector.BALL;
        else
            return CommandSelector.END;

    }

    public MoveTest() {

        super(
                // Select one of many commands
                // Selection command in selectCmd123
                new SelectCommand(Map.ofEntries(Map.entry(CommandSelector.CHIPS, new SequentialCommandGroup(
                        new MoveRobot(0, m_vision.getChips(0), 0, 0, 0.5), new WaitCommand(1),
                        new MoveRobot(1, m_vision.getChips(1), 0, 0,
                                0.5))),
                    Map.entry(CommandSelector.NISSIN, new ComplexAuto()),
                    Map.entry(CommandSelector.KITKAT, new ComplexAuto()),
                    Map.entry(CommandSelector.BALL, new ComplexAuto()) ),
                    

                MoveTest::selectCmd123

                //can use clearGroupedCommands() to reuse commands
            ) 
        );
    }
}
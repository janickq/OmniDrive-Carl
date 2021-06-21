package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.ArmPick;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.commands.auto.PickCommands.RobotPick;
import frc.robot.subsystems.Vision;


/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class Pick extends AutoCommand {
    private final static Vision m_vision = RobotContainer.m_vision;


    private enum CommandSelector {
        CHIPS, NISSIN, KITKAT, BALL, END;
    }

    static public CommandSelector selectCmd123() {

        if (m_vision.getChips(0) != 0 && m_vision.getChips(1) != 0)
            return CommandSelector.CHIPS;
        else if (m_vision.getNissin(0) != 0 && m_vision.getNissin(1) != 0)
            return CommandSelector.NISSIN;
        else if (m_vision.getKitkat(0) != 0 && m_vision.getKitkat(1) != 0)
            return CommandSelector.KITKAT;
        else if (m_vision.getBall(0) != 0 && m_vision.getBall(1) != 0)
            return CommandSelector.BALL;
        else
            return CommandSelector.END;

    }

    public Pick(){
        super(
                // Select one of many commands
                // Selection command in selectCmd123
                /*
                item 0 = chips
                     1 = ball
                     2 = kitkat
                     3 = nissin
                */
                new SelectCommand(Map.ofEntries(
                    
                    Map.entry(CommandSelector.CHIPS, 
                        new SequentialCommandGroup( 
                            new RobotPick(0, 0, 0, 0.1),
                            new WaitCommand(0.5), 
                            new ArmPick(0, 0, 0, 0.3),
                            new WaitCommand(0.5), 
                            new GripperPick(0, 0),
                            new WaitCommand(0.5),
                            new MoveArmXY(Constants.ARM2, Constants.ARM1, 0, 0, 0.3))),

                    Map.entry(CommandSelector.NISSIN,
                        new SequentialCommandGroup( 
                            new RobotPick(3, 0, 0, 0.1),
                            new WaitCommand(0.5), 
                            new ArmPick(3, 0, 0, 0.3),
                            new WaitCommand(0.5), 
                            new GripperPick(3, 0),
                            new WaitCommand(0.5),
                            new MoveArmXY(Constants.ARM2, Constants.ARM1, 0, 0, 0.3))),

                    Map.entry(CommandSelector.KITKAT, 
                        new SequentialCommandGroup( 
                            new RobotPick(2, 0, 0, 0.1),
                            new WaitCommand(0.5), 
                            new ArmPick(2, 0, 0, 0.3),
                            new WaitCommand(0.5), 
                            new GripperPick(2, 0),
                            new WaitCommand(0.5),
                            new MoveArmXY(Constants.ARM2, Constants.ARM1, 0, 0, 0.3))),
                                
                    Map.entry(CommandSelector.BALL, 
                        new SequentialCommandGroup( 
                            new RobotPick(1, 0, 0, 0.1),
                            new WaitCommand(0.5), 
                            new ArmPick(1, 0, 0, 0.3),
                            new WaitCommand(0.5), 
                            new GripperPick(1, 0),
                            new WaitCommand(0.5),
                            new MoveArmXY(Constants.ARM2, Constants.ARM1, 0, 0, 0.3)))
                        ),
                    

                Pick::selectCmd123

                //can use clearGroupedCommands() to reuse commands
            ) 
            
        );
        // clearGroupedCommands();
    }
    

}
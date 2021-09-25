package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.ArmPickX;
import frc.robot.commands.auto.PickCommands.ArmPickY;
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
    private static double offsetX = -0.05;
    private static double offsetY = 0.15;

    private enum CommandSelector {
        CHIPS, NISSIN, KITKAT, BALL, END;
    }

    static public CommandSelector selectCmd123() {

        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */

        if (Globals.curItem == 0)
            return CommandSelector.CHIPS;
        else if (Globals.curItem == 3)
            return CommandSelector.NISSIN;
        else if (Globals.curItem == 1)
            return CommandSelector.BALL;
         else if (Globals.curItem == 2)
            return CommandSelector.KITKAT;
        else
            return CommandSelector.END;

    }

    public Pick() {

        super(
            // Select one of many commands
            // Selection command in selectCmd123
            /*
             * item 0 = chips 
             * 1 = ball 
             * 2 = kitkat 
             * 3 = nissin
             */
            new SelectCommand(Map.ofEntries(
      
                Map.entry(CommandSelector.CHIPS, 
                    new SequentialCommandGroup(
      
                        new RobotPick(0, 0, 0, 0.1), 
                        new WaitCommand(0.5), 
                        new ArmPickX(0.2),
                        new WaitCommand(1), 
                        new ArmPickY(0.3),
                        new WaitCommand(0.5),
                        new GripperPick(), 
                        new WaitCommand(0.5),
                        new MoveArmXY(Constants.ARM2 + offsetX, Constants.ARM1 + offsetY, 0, 0, 0.3)
                        
                        
                    )
                    
                ),
      
                Map.entry(CommandSelector.NISSIN,
                    new SequentialCommandGroup( 
                        
                        new RobotPick(3, 0, 0, 0.1),
                        new WaitCommand(0.5), 
                        new ArmPickX(0.2),
                        new WaitCommand(1), 
                        new ArmPickY(0.3),
                        new WaitCommand(0.5),
                        new GripperPick(),
                        new WaitCommand(0.5),
                        new MoveArmXY(Constants.ARM2 + offsetX, Constants.ARM1 + offsetY, 0, 0, 0.3)
                    )
                ),
      
                Map.entry(CommandSelector.KITKAT, 
                    new SequentialCommandGroup( 
                        
                        new RobotPick(2, 0, 0, 0.1),
                        new WaitCommand(0.5), 
                        new ArmPickX(0.2),
                        new WaitCommand(1), 
                        new GripperPick(7),
                        new ArmPickY(0.3),
                        new WaitCommand(0.5),
                        new GripperPick(),
                        new WaitCommand(0.5),
                        new MoveArmXY(Constants.ARM2 + offsetX, Constants.ARM1 + offsetY, 0, 0, 0.3)
                    )
                ),
                            
                Map.entry(CommandSelector.BALL, 
                    new SequentialCommandGroup( 
                        
                        new RobotPick(1, 0, 0, 0.1),
                        new WaitCommand(0.5), 
                        new ArmPickX(0.2),
                        new WaitCommand(1), 
                        // new GripperPick(5),
                        // new ArmPickY(0.3, 0.05),
                        new GripperPick(7),
                        new ArmPickY(0.3),
                        new WaitCommand(0.5),
                        new GripperPick(),
                        new WaitCommand(0.5),
                        new MoveArmXY(Constants.ARM2 + offsetX, Constants.ARM1 + offsetY, 0, 0, 0.3))
                ),

                Map.entry(CommandSelector.END, 

                     new InstantCommand()
                
                )
            
            ),
                
      
            Pick::selectCmd123
      
            //can use clearGroupedCommands() to reuse commands
            ) 
            
        );

    }



}

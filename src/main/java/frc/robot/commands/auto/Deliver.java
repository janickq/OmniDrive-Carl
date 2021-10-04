package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;


public class Deliver extends AutoCommand {

  private static final Points m_points = RobotContainer.m_points;

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
    else if (Globals.curItem == 2)
        return CommandSelector.KITKAT;
    else if (Globals.curItem == 1)
        return CommandSelector.BALL;
    else
        return CommandSelector.END;

  }

  public Deliver(){

    super(
      // Select one of many commands
      // Selection command in selectCmd123
      /*
       * item 0 = chips 1 = ball 2 = kitkat 3 = nissin
       */
      new SelectCommand(Map.ofEntries(

          Map.entry(CommandSelector.CHIPS, 
            new SequentialCommandGroup(
                
              new FollowPath("chipsDrop"),
              new InstantCommand(()->  Globals.itemCount[0]++),
              new AlignDrop("BlackBox")
            )

          ),

          Map.entry(CommandSelector.NISSIN,
            new SequentialCommandGroup(
              new FollowPath("nissinDrop"),
              new InstantCommand(()->  Globals.itemCount[3]++),
              new AlignDrop("YellowBox")
            )
          ),

          Map.entry(CommandSelector.KITKAT, 
            new SequentialCommandGroup(
              new FollowPath("kitKatDrop"),
              new InstantCommand(()->  Globals.itemCount[2]++),
              new AlignDrop("BlueBox")
            )
          ),
                      
          Map.entry(CommandSelector.BALL, 
            new SequentialCommandGroup(
              new FollowPath("ballDrop"),
              new InstantCommand(()->  Globals.itemCount[1]++),
              new AlignDrop("RedBox")
              

            )
          )
      ),

      Deliver::selectCmd123


      
      )
      
     );



  }
}

  


      


package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;
import frc.robot.Points;


public class DemoDeliver extends AutoCommand {

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

  public DemoDeliver(){

    super(
      // Select one of many commands
      // Selection command in selectCmd123
      /*
       * item 0 = chips 1 = ball 2 = kitkat 3 = nissin
       */
      new SelectCommand(Map.ofEntries(

          Map.entry(CommandSelector.CHIPS, 
            new SequentialCommandGroup(
                
              new MovePose("chipsDrop")
            )


          ),

          Map.entry(CommandSelector.NISSIN,
          new SequentialCommandGroup(
              new MovePose("nissinDrop")
 
          )
          ),

          Map.entry(CommandSelector.KITKAT, 
          new SequentialCommandGroup(
              new MovePose("kitKatDrop")
          )

          ),
                      
          Map.entry(CommandSelector.BALL, 
          new SequentialCommandGroup(
              new MovePose("ballDrop")

          )
          )
      ),

      Deliver::selectCmd123

      //can use clearGroupedCommands() to reuse commands
      
      )
      
     );



  }
}

  


      


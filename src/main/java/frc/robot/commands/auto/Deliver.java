package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import frc.robot.Globals;
import frc.robot.Points;


public class Deliver extends AutoCommand {

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

              new MovePose(Points.chipsDrop)

          ),

          Map.entry(CommandSelector.NISSIN,

              new MovePose(Points.nissinDrop) 
          ),

          Map.entry(CommandSelector.KITKAT, 

              new MovePose(Points.kitKatDrop)

          ),
                      
          Map.entry(CommandSelector.BALL, 

              new MovePose(Points.ballDrop)

      
          )
      ),

      Deliver::selectCmd123

      //can use clearGroupedCommands() to reuse commands
      
      )
      
     );

    //  clearGroupedCommands();
    // new MoveRobot(2, Math.PI / 2, 0, 0, 1),
    // new WaitCommand(1),
    // new MoveRobotSense(0, -20, 0, 0, spd1, () -> m_sensor.getIRDistance2() < 50),
    // new WaitCommand(1),
    // new MoveRobotSense(1, 20, 0, 0.1, spd1, () -> m_sensor.getCobraTotal() > 7000),
    // new MoveRobot(1, 0.1, 0.1, 0, spd2), 
    // new MoveRobotSense(0, -20, 0, 0, spd2, () -> m_sensor.getIRDistance2() < 50),
    // new WaitCommand(1),
    // new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
    // new MoveRobot(1, -0.1, 0, 0, spd2),
    // new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
    // new MoveRobot(1, 0.1, 0, 0, spd2),

  }
}

  


      


package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.ArmPick;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.commands.auto.PickCommands.RobotPick;
import frc.robot.subsystems.Sensor;

public class Deliver extends AutoCommand {

  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  private final static Sensor m_sensor = RobotContainer.m_sensor;

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

                new MoveRobot(2, Math.PI / 2, 0, 0, 1),
                new WaitCommand(1),
                new MoveRobotSense(0, -20, 0, 0, spd1, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 20, 0, 0.1, spd1, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, 0.1, 0.1, 0, spd2), 
                new MoveRobotSense(0, -20, 0, 0, spd2, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, -0.1, 0, 0, spd2),
                new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
                new MoveRobot(1, 0.1, 0, 0, spd2),
                new GripperPick(4)
              )
          ),

          Map.entry(CommandSelector.NISSIN,
              new SequentialCommandGroup( 
                
                new MoveRobot(2, Math.PI / 2, 0, 0, 1),
                new WaitCommand(1),
                new MoveRobotSense(0, -20, 0, 0, spd1, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 20, 0, 0.1, spd1, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, 0.1, 0.1, 0, spd2), 
                new MoveRobotSense(0, -20, 0, 0, spd2, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, -0.1, 0, 0, spd2),
                new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
                new MoveRobot(1, 0.1, 0, 0, spd2),
                new GripperPick(4)

              )
          ),

          Map.entry(CommandSelector.KITKAT, 
              new SequentialCommandGroup( 

                new MoveRobot(2, Math.PI / 2, 0, 0, 1),
                new WaitCommand(1),
                new MoveRobotSense(0, -20, 0, 0, spd1, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 20, 0, 0.1, spd1, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, 0.1, 0.1, 0, spd2), 
                new MoveRobotSense(0, -20, 0, 0, spd2, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, -0.1, 0, 0, spd2),
                new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
                new MoveRobot(1, 0.1, 0, 0, spd2),
                new GripperPick(4) 

              )
          ),
                      
          Map.entry(CommandSelector.BALL, 
              new SequentialCommandGroup( 

                new MoveRobot(2, Math.PI / 2, 0, 0, 1),
                new WaitCommand(1),
                new MoveRobotSense(0, -20, 0, 0, spd1, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 20, 0, 0.1, spd1, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, 0.1, 0.1, 0, spd2), 
                new MoveRobotSense(0, -20, 0, 0, spd2, () -> m_sensor.getIRDistance2() < 50),
                new WaitCommand(1),
                new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, -0.1, 0, 0, spd2),
                new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
                new MoveRobot(1, 0.1, 0, 0, spd2),
                new GripperPick(4)

              )
      
          )
      ),

      Deliver::selectCmd123

      //can use clearGroupedCommands() to reuse commands
      
      )
      
     );

    //  clearGroupedCommands();

  }
}

  


      


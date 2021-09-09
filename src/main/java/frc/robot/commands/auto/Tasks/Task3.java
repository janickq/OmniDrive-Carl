package frc.robot.commands.auto.Tasks;

import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.MoveRobot;

public class Task3 extends AutoCommand {
  
  public Task3() {
    super(
      new MoveRobot(2, 2*Math.PI, 0, 0, Math.PI/2)
    );
  }

}
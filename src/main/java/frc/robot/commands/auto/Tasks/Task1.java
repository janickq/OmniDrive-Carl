package frc.robot.commands.auto.Tasks;

import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.MoveRobot;

public class Task1 extends AutoCommand {
  
  public Task1() {
    super(
      new MoveRobot(1, 1, 0, 0, 0.5)
    );
  }

}
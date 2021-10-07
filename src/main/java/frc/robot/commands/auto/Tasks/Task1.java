package frc.robot.commands.auto.Tasks;

import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Sensor;

public class Task1 extends AutoCommand {

  private static final Sensor m_sensor = RobotContainer.m_sensor;
  public Task1() {
    super(
      new MoveRobotSense2(1, 1.5, 0, 0.2, 0.2, ()-> m_sensor.getCobraTotal() > 4000),
      new MoveRobot(1, 0.2, 0.5, 0, 0.2)
    );
  }

}
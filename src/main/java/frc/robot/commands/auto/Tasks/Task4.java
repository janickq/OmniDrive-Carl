package frc.robot.commands.auto.Tasks;

import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Sensor;

public class Task4 extends AutoCommand {

  private final static Sensor m_sensor = RobotContainer.m_sensor;

  public Task4() {
    super(
      new MoveRobotSense2(1, 5, 0, 0, 0.5, () -> m_sensor.getSonicDistance1() < 500),
      new MoveRobot(0, 0.5, 0, 0, 0.5),
      new MoveRobot(1, 1.3, 0, 0, 0.5),
      new MoveRobot(0, -0.5, 0, 0, 0.5),
      new MoveRobot(1, 1, 0, 0, 0.5)
    );
  }

}
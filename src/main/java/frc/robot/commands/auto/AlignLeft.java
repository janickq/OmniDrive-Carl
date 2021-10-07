package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

public class AlignLeft extends AutoCommand{
  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
  
  public AlignLeft() {

    super(

          new MoveRobotSense(1, 1, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000|| m_sensor.getSonicDistance1()< 100),
          new MoveRobot(1, -0.1, 0, 0, spd2),
          new MoveRobotSense(0, -0.25, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 4500),
          new MoveRobot(1, 0.1, 0, 0, spd2),
          new InstantCommand(() -> Globals.alignFlag = false )

    );

  }



}
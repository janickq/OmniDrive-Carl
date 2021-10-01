package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

public class AlignPick extends AutoCommand {
  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  public AlignPick() {

    super(

          new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getSonicDistance1()< 900),

          new MoveRobotSense(1, 1, 0, 0, spd2, () -> m_sensor.getSonicDistance2()< 300),
          new AlignLeft()


    );

  }



}
package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.subsystems.Vision;

public class Task6 extends AutoCommand {

  static Vision m_vision = RobotContainer.m_vision;

  public Task6() {
    super(
      new InstantCommand(m_vision::itemLook),
      new WaitCommand(2),
      new OnlyKitkat()
    );
  }
}
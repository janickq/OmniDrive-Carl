package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.Pick;
import frc.robot.subsystems.Vision;

public class Task5 extends AutoCommand {

  static final Vision m_vision = RobotContainer.m_vision;

  public Task5() {
    super(
      new InstantCommand(m_vision::itemLook),
      new WaitCommand(3),
      new InstantCommand(m_vision::getItem),
      new Pick()
    );
  }

}
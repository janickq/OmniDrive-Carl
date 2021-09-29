package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.DropPoint;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.FollowPath;
import frc.robot.commands.auto.MovePose;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class Task9 extends AutoCommand {

  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
  private final static Vision m_vision = RobotContainer.m_vision;
  private final static Points m_points = RobotContainer.m_points;

  private final static DropPoint m_drop = new DropPoint();
  
  public Task9() {
    super(
                new MovePose("Pick"),
                new InstantCommand(m_vision::boxLook),
                new InstantCommand(m_drop::getBin),
                new InstantCommand(m_drop::getBoxes),
                new InstantCommand(m_drop::generatePair),
                new InstantCommand(m_drop::getDropPose),
                new FollowPath("GreenBox")
    );
  }

}
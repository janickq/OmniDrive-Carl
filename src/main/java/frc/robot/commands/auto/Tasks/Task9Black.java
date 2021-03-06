package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.DropPoint;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AlignDrop;
import frc.robot.commands.auto.AlignLeft;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.commands.auto.FollowPath;
import frc.robot.commands.auto.MovePose;
import frc.robot.commands.auto.MovePose2;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.Tasks.MoveRobotSense2;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Task9Black extends AutoCommand {

  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
  private final static Vision m_vision = RobotContainer.m_vision;
  private final static Points m_points = RobotContainer.m_points;
  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static DropPoint m_drop = new DropPoint();

  public Task9Black() {
    super(
                new MovePose2("Pick"),
                new MovePose("Pick"),
                new MoveRobotSense2(0, 5, 0, 0, 0.1, () -> m_sensor.getSonicDistance2()< 900),
                new MoveRobotSense2(1, 1, 0, 0, 0.1, () -> m_sensor.getSonicDistance1()< 1100 || m_sensor.getCobraTotal() > 6000),
                new AlignLeft(),
                new MoveRobot(2, Math.PI/2, 0 , 0 , 0.5),
                new WaitCommand(3),
                new InstantCommand(m_drop::getBoxes),
                new WaitCommand(2),
                new MoveRobot(1, -0.8, 0, 0, 0.5),
                new WaitCommand(3),
                new InstantCommand(m_drop::getBoxes),
                new InstantCommand(m_drop::generatePair),
                new InstantCommand(m_drop::getDropPose),
                new FollowPath("BlackBox"),
                new AlignDrop("BlackBox")
                
    );
  }

}
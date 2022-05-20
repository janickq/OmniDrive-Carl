package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;

import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class MainSequence extends AutoCommand {

  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
  private final static Vision m_vision = RobotContainer.m_vision;
  private final static Points m_points = RobotContainer.m_points;
  private final static Sensor m_sensor = RobotContainer.m_sensor;


  public MainSequence() {

        super(
              new MoveRobot(1, 0.1, 0, 0, 0.1),
              new WaitCommand(5),
              new MoveRobot(1, -0.1, 0, 0, 0.1)


        );
    }



}
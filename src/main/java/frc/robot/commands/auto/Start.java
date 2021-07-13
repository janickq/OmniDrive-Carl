package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

public class Start extends AutoCommand{
  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static OmniDrive m_drive = RobotContainer.m_omnidrive;

  public Start(){

    super(
          new InstantCommand(m_drive::resetHeading),
          new WaitCommand(2), 
          new GripperPick(4),
          new MoveRobot(0, 0.5, 0, 0, spd1),
          new MoveRobotSense(1, 20, 0, spd1, spd1, () -> m_sensor.getIRDistance1() < 45),
          new MoveRobot(1, 0.35, spd1, 0, spd1), 
          new MoveRobot(2, -Math.PI / 2, 0, 0, 1),
          new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
          new MoveRobot(1, -0.1, 0, 0, spd2),
          new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
          new MoveRobot(1, 0.1, 0, 0, spd2)
    );
    // clearGroupedCommands();
  }



}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

public class AlignNull extends AutoCommand {
  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  public AlignNull() {

    super(


          new MoveRobot(0, 0.6, 0, 0, spd1),
          new MoveRobot(1, 0.3, 0, 0, spd2),
          new GripperPick(4),
          new MoveRobot(1, -0.3, 0, 0, spd2),
          new MoveRobot(0, -0.6, 0, 0, spd1),
          new InstantCommand(() -> Globals.alignFlag = false )

    );

  }



}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Reset extends AutoCommand {

  private final static Sensor m_sensor = RobotContainer.m_sensor;
  private final static Vision m_vision = RobotContainer.m_vision;
  private static double spd1 = 0.3;
  private static double spd2 = 0.1;
  
  public Reset(){
    
    super(

      new MoveRobotSense(1, -100, 0, 0.3, 0.3, ()->m_sensor.getIRDistance1() < 60),
      new MoveRobot(1, -0.05, 0.3, 0, 0.3),
      new MoveRobot(2, -Math.PI / 2, 0, 0, 1),
      new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
      new MoveRobot(1, -0.1, 0, 0, spd2),
      new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
      new MoveRobot(1, 0.1, 0, 0, spd2)
    
    );
    

  }
}
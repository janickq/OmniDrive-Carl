package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Sensor;

public class Task2 extends CommandBase {

  private static final Sensor m_sensor = RobotContainer.m_sensor;
  boolean endflag;
  private MoveRobot cmd = new MoveRobot(1, -0.5, 0, 0, 0.5);

  public Task2() {

  }
  
  @Override
  public void initialize() {
    endflag = false;
  }
  
  @Override
  public void execute() {

    if (m_sensor.getSonicDistance1() < 300)
      endflag = true;
  }
  
  @Override
  public boolean isFinished() {
    return endflag;
  }

  @Override
  public void end(boolean interrupted) {

    cmd.schedule();
    
  }

}
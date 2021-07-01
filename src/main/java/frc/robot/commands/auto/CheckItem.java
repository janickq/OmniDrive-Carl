package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class CheckItem extends CommandBase{
  
  private final static Vision m_vision = RobotContainer.m_vision;
  private boolean endFlag = false;

  public CheckItem(){
    
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    m_vision.getItem();
    endFlag = Globals.checkItem;
  }

  @Override
  public boolean isFinished() {

    return endFlag;

  }

}
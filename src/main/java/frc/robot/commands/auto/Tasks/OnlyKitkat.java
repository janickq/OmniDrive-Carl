package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.End;
import frc.robot.commands.auto.Pick;
import frc.robot.subsystems.Vision;

public class OnlyKitkat extends CommandBase {
  static final Vision m_vision = RobotContainer.m_vision;
  boolean flag;
  static End end = new End();

  public OnlyKitkat() {
    
  }
  
  @Override
  public void initialize() {
    x = 0;
    flag = false;
  }
  int x = 0;
  public void No() {

    if (Globals.curItem == 0 && x == 0) {
      end.schedule();
      x++;
    }
    else if (Globals.curItem == 1 && x == 1) {
      end.schedule();
      x++;
    }
    else if (Globals.curItem == 3 && x == 2) {
      end.schedule();
      x++;
    }

  }
  @Override
  public void execute() {
    if (Globals.curItem == 2)
      flag = true;
    else
      m_vision.getItem();
      No();
  }

  @Override
  public boolean isFinished() {
    return flag;

  }
  
  @Override
  public void end(boolean interrupted) {

  }
}
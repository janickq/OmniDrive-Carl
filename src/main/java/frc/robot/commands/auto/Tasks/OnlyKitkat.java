package frc.robot.commands.auto.Tasks;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.End;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.Pick;
import frc.robot.subsystems.Vision;

public class OnlyKitkat extends CommandBase {
  static final Vision m_vision = RobotContainer.m_vision;
  boolean flag;
  boolean[] item_flag = new boolean[4];
  static End end = new End();
  MoveRobot chipscmd = new MoveRobot(1, 0.1, 0, 0, 0.5);
  MoveRobot nissincmd = new MoveRobot(2, 2*Math.PI, 0, 0, 2);
  MoveRobot ballcmd = new MoveRobot(0, 0.1, 0, 0, 0.5);
  MoveRobot kitkatcmd = new MoveRobot(0, -0.1, 0, 0, 0.5);
  /*
    * item 0 = chips 
    * 1 = ball 
    * 2 = kitkat 
    * 3 = nissin
    */
  public OnlyKitkat() {
    
  }
  
  @Override
  public void initialize() {

    for (int i = 0; i < 4; i++) {
      item_flag[i] = true;
    }
    flag = false;
  }
  int x = 0;
  public void No() {

    // if (Globals.curItem == 0 && item_flag[0]) {
    //   chipscmd.schedule();
    //   item_flag[0] = false;
    //   x++;
    // }
    
    // else if (Globals.curItem == 1 && item_flag[1]) {
    //   ballcmd.schedule();
    //   item_flag[1] = false;
    //   x++;
    // }

    // else if (Globals.curItem == 2 && item_flag[2]) {
    //   kitkatcmd.schedule();
    //   item_flag[2] = false;
    //   x++;
    // }
    if (Globals.curItem == 3 && item_flag[3]) {
      
      item_flag[3] = false; 
      x++;
    }

  }
  @Override
  public void execute() {
    if (item_flag[3] && Globals.curItem == 3) {
      nissincmd.schedule();
      item_flag[3] = false;
      Globals.poserunFlag = false;
    } 
    if (Globals.poserunFlag) {
      m_vision.getItem();
      item_flag[3] = true;
    }
    m_vision.getItem();
  }

  @Override
  public boolean isFinished() {
    return flag;

  }
  
  @Override
  public void end(boolean interrupted) {

  }
}
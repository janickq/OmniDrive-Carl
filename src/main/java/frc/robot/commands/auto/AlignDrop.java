package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;

public class AlignDrop extends CommandBase {
  Boolean direction;
  boolean endflag;
  boolean flag;
  String box;
  AlignLeft left = new AlignLeft();
  AlignRight right = new AlignRight();
  // MoveRobot nullDrop = new MoveRobot(0, 0.5, 0, 0, Math.PI/2);
  AlignNull nullDrop = new AlignNull();
  private final static Points m_points = RobotContainer.m_points;
  
  
  public AlignDrop(String box) {
    this.box = box;
  }

  @Override
  public void initialize() {
    Globals.alignFlag = true;
    endflag = false;
    flag = false;
    if (!Globals.nullFlag)
      direction = m_points.getAlignment(box);
    else 
      direction = true;
  }

  @Override
  public void execute() {
    if (Globals.nullFlag && !flag) {
      nullDrop.schedule();
      flag = true;
      Globals.poserunFlag = false;
    }
    else if (!Globals.nullFlag) {
      if (!flag) {
        //launch command group
        if (!direction)
          right.schedule();
        if (direction)
          left.schedule();
        flag = true;
        Globals.alignFlag = true;
      }

      else {
        if (Globals.alignFlag == false) 
          //command group finished, reset flag
          endflag = true;
      }
    }
    else if(!Globals.alignFlag){
      endflag = true;
    }
  }

  @Override
  public boolean isFinished() {
    return endflag;
  }
  @Override
  public void end(boolean interrupted) {
    endflag = false;
    Globals.alignFlag = true;
    Globals.nullFlag = false;
  }


}
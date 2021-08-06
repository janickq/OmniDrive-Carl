package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

public class ZeroPose extends CommandBase{

  private static final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  boolean endFlag;

  double offsetX;
  double offsetY;
  double offsetW;
  double zeroW;
  double compassW;
  Pose2d zeroPose;
  Command cmd;
  public ZeroPose(){



  }

  @Override
  public void initialize() {

    endFlag = false;

    for(int i = 0; i < 10; i++){
      compassW += Globals.compassHeading;
    }
    compassW = compassW/10;

    zeroW = (Globals.curPose.getRotation().getRadians()
            + compassW)/2;

    offsetW = Globals.curPose.getRotation().getRadians();
    
    zeroPose = new Pose2d(Globals.curPose.getTranslation(), new Rotation2d(offsetW));

    cmd = new MovePose(zeroPose);
    cmd.schedule();

  }
  
  @Override
  public void execute() {
    if (Globals.poserunFlag)
      endFlag = true;
  }

  @Override
  public boolean isFinished() {
    return endFlag;
  }

  @Override
  public void end(boolean interrupted) {
    m_omnidrive.setPose(zeroPose);
  }

}
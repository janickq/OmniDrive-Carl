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
  boolean flag;
  double offsetW;
  double compassW;
  Command cmd;

  public ZeroPose(){



  }

  @Override
  public void initialize() {
    flag = false;
    endFlag = false;
    Globals.poserunFlag = false;

    compassW = 0;
    
    compassW = m_omnidrive.getCompassHeading();


    offsetW = (Globals.referenceHeading - compassW);
    Globals.headingError = offsetW;


    cmd = new MoveRobot(2, -offsetW , 0, 0, 0.2);


  }
  
  @Override
  public void execute() {
    if (flag==false) {
      cmd.schedule(false);
      flag = true;
    }
    else if(Globals.poserunFlag)
      endFlag = true;
    
  }



  @Override
  public boolean isFinished() {
    return endFlag;
  }


}
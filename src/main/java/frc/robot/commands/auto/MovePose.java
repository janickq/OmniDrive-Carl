package frc.robot.commands.auto;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

public class MovePose extends CommandBase{

  private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  Pose2d curPose;
  Pose2d desiredPose;
  Transform2d relativePose;
  Translation2d newdesiredTranslation;
  // Rotation2d omegadist;
  TrapezoidProfile[] profile = new TrapezoidProfile[3];
  TrapezoidProfile.State[] setpoint = new TrapezoidProfile.State[3];
  TrapezoidProfile.State[] goal = new TrapezoidProfile.State[3];
  TrapezoidProfile.Constraints[] constraints = new TrapezoidProfile.Constraints[3];
  private double[] dist = new double[3];
  private int[] m_dir = new int[3];
  private double dT = 0.02;
  private int i;
  
  public MovePose(double x, double y, double omegaRadian){

    desiredPose = new Pose2d(x, y, new Rotation2d(omegaRadian));

  }

  public MovePose(Pose2d desiredPose){
    
    this.desiredPose = desiredPose;

  }

  @Override
  public void initialize() {

    Globals.poserunFlag = false;
    i = 0;
    //gets transformed pose
    curPose = Globals.curPose;
    relativePose = new Transform2d(curPose,desiredPose);



    newdesiredTranslation = relativePose.getTranslation().rotateBy(curPose.getRotation());
    // relativePose = newdesiredPose
    Globals.debug11 = relativePose.toString();
    Globals.debug10 = newdesiredTranslation.toString();
    // relativePose = desiredPose.relativeTo(curPose);
    // omegadist = curPose.getRotation().rotateBy(Globals.referencePose.getRotation());

    //distance values
    // dist[0] = newdesiredTranslation.getX();
    // dist[1] = newdesiredTranslation.getY();
    dist[0] = relativePose.getTranslation().getX();
    dist[1] = relativePose.getTranslation().getY();
    dist[2] = relativePose.getRotation().getRadians();

    //set trapezoid profile
    for(int i = 0; i <3; i++){
      m_dir[i] = (dist[i]>0)?1:-1;
      dist[i] *= m_dir[i];   
      goal[i] = new TrapezoidProfile.State(dist[i], 0);
      setpoint[i] = new TrapezoidProfile.State(0, 0);
    }

    constraints[0] = new TrapezoidProfile.Constraints(0.5, 0.5);
    constraints[1] = new TrapezoidProfile.Constraints(0.5, 0.5);
    constraints[2] = new TrapezoidProfile.Constraints(1, 2*Math.PI);

  }

  public int moveRobot(int i){
    
    profile[i] = new TrapezoidProfile(constraints[i], goal[i], setpoint[i]);
    setpoint[i] = profile[i].calculate(dT);
    m_omnidrive.setRobotSpeedType(i, setpoint[i].velocity*m_dir[i]);
    if (setpoint[i].position>=goal[i].position)
      return i+1;
    else
      return i;

  }
   
  @Override
  public void execute() {
    

    i = moveRobot(i);
    // curPose = Points.curPoseTransformed;  
    curPose = Globals.curPose;
  }

  @Override
  public void end(boolean interrupted) {
    i = 0;
    Arrays.fill(dist, 0);
    Arrays.fill(m_dir,0);
    Globals.poserunFlag = true;
  }

  @Override
  public boolean isFinished() {
    if(curPose.equals(desiredPose)|| i > 2){
      return true;
    }
    else 
      return false;
    
  }
  
}
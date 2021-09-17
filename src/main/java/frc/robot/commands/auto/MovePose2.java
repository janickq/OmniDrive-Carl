package frc.robot.commands.auto;


import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

public class MovePose2 extends CommandBase {
  private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
  private final Points m_points = RobotContainer.m_points;

  boolean endflag;
  boolean obstacles;
  String pointName;
  Pose2d curPose;
  Pose2d desiredPose;
  Transform2d relativePose;
  Translation2d newdesiredTranslation;
  // Rotation2d omegadist;
  TrapezoidProfile[] profile = new TrapezoidProfile[2];
  TrapezoidProfile.State[] setpoint = new TrapezoidProfile.State[2];
  TrapezoidProfile.State[] goal = new TrapezoidProfile.State[2];
  TrapezoidProfile.Constraints[] constraints = new TrapezoidProfile.Constraints[2];
  private double[] dist = new double[2];
  private int[] m_dir = new int[2];
  private double dT = 0.02;
  private int i;
  boolean stringflag = false;

  public MovePose2(double x, double y, double omegaRadian) {

    desiredPose = new Pose2d(x, y, new Rotation2d(omegaRadian));

  }

  public MovePose2(Pose2d desiredPose) {
    this.desiredPose = desiredPose;
  }

  public MovePose2(String pointName) {

    this.pointName = pointName;
    stringflag = true;

  }

  public MovePose2(String pointName, boolean obstacles) {
    this.obstacles = obstacles;
    this.pointName = pointName;

  }
  @Override
  public void initialize() {

    Globals.poserunFlag = false;
    endflag = false;
    if(stringflag)
      desiredPose = m_points.getPoint(pointName);

    i = 0;
    //gets transformed pose
    curPose = Globals.curPose;
    relativePose = new Transform2d(curPose, desiredPose);

    newdesiredTranslation = relativePose.getTranslation().rotateBy(curPose.getRotation());

    //distance values
    // dist[0] = newdesiredTranslation.getX();
    // dist[1] = newdesiredTranslation.getY();
    dist[0] = relativePose.getTranslation().getX();
    dist[1] = relativePose.getTranslation().getY();

    //set trapezoid profile
    for (int i = 0; i < 2; i++) {
      m_dir[i] = (dist[i] > 0) ? 1 : -1;
      dist[i] *= m_dir[i];
      goal[i] = new TrapezoidProfile.State(dist[i], 0);
      setpoint[i] = new TrapezoidProfile.State(0, 0);
    }

    constraints[0] = new TrapezoidProfile.Constraints(0.5, 0.3);
    constraints[1] = new TrapezoidProfile.Constraints(0.5, 0.3);

  }

  public void checkObstacle() {


  }

  public void moveRobot() {
    i = 0;
    int u = 1;
    profile[i] = new TrapezoidProfile(constraints[i], goal[i], setpoint[i]);
    setpoint[i] = profile[i].calculate(dT);
    profile[u] = new TrapezoidProfile(constraints[u], goal[u], setpoint[u]);
    setpoint[u] = profile[u].calculate(dT);
    m_omnidrive.setRobotSpeedXYW(setpoint[i].velocity*m_dir[i], setpoint[u].velocity*m_dir[u], 0);
    if (setpoint[i].position >= goal[i].position && setpoint[u].position >= goal[u].position)
      endflag = true;
    

  }

  @Override
  public void execute() {

    moveRobot();

    curPose = Globals.curPose;
  }

  @Override
  public void end(boolean interrupted) {
    i = 0;
    Arrays.fill(dist, 0);
    Arrays.fill(m_dir, 0);
    Globals.poserunFlag = true;
    SmartDashboard.putString("movepose finished", "yes");
  }

  @Override
  public boolean isFinished() {
    return endflag;
    
  }
  

}
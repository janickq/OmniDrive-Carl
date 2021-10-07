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

public class MovePose3 extends CommandBase {
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
  TrapezoidProfile profile;
  TrapezoidProfile.State setpoint = new TrapezoidProfile.State();
  TrapezoidProfile.State goal = new TrapezoidProfile.State();
  TrapezoidProfile.Constraints constraints = new TrapezoidProfile.Constraints();
  private double dist;
  private int m_dir;
  private double dT = 0.02;
  private int i;
  boolean stringflag = false;
  double speedX;
  double speedY;
  double angle;

  public MovePose3(double x, double y, double omegaRadian) {

    desiredPose = new Pose2d(x, y, new Rotation2d(omegaRadian));

  }

  public MovePose3(Pose2d desiredPose) {
    this.desiredPose = desiredPose;
  }

  public MovePose3(String pointName) {

    this.pointName = pointName;
    stringflag = true;

  }

  public MovePose3(String pointName, boolean obstacles) {
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

    angle = Globals.curPose.getRotation().getRadians()+Math.atan2(relativePose.getTranslation().getY(), relativePose.getTranslation().getX());
    dist = Math.hypot(relativePose.getTranslation().getX(), relativePose.getTranslation().getX());

    //set trapezoid profile

      m_dir = (dist > 0) ? 1 : -1;
      dist *= m_dir;
      goal = new TrapezoidProfile.State(dist, 0);
      setpoint = new TrapezoidProfile.State(0, 0);
    

    constraints = new TrapezoidProfile.Constraints(0.5, 0.2);
 

  }

  public void checkObstacle() {


  }

  public void moveRobot() {
    i = 0;
    int u = 1;
    profile = new TrapezoidProfile(constraints, goal, setpoint);
    setpoint = profile.calculate(dT);
    profile = new TrapezoidProfile(constraints, goal, setpoint);
    setpoint = profile.calculate(dT);
    speedY = Math.sin(angle) * setpoint.velocity;
    speedX = Math.cos(angle) * setpoint.velocity;
    m_omnidrive.setRobotSpeedXYW(speedX, speedY, 0);
    if (setpoint.position >= goal.position)
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
    dist = 0;
    m_dir = 0;
    Globals.poserunFlag = true;
    SmartDashboard.putString("movepose finished", "yes");
  }

  @Override
  public boolean isFinished() {
    return endflag;
    
  }
  

}
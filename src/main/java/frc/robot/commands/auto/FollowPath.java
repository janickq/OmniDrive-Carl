package frc.robot.commands.auto;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.PathMap;
import frc.robot.Pathfinder;
import frc.robot.Points;
import frc.robot.RobotContainer;

public class FollowPath extends CommandBase {
  private static final Points m_points = RobotContainer.m_points;
  PathMap pathMap = new PathMap();

  ArrayList<Command> moveposes = new ArrayList<>();
  ArrayList<Pose2d> obstaclelist = new ArrayList<>();
  String pointname;
  String[] obstacles;


  boolean[] moveflag;
  boolean endflag;
  int u;

  public FollowPath(String pointname, String[] obstacles) {

    this.pointname = pointname;
    this.obstacles = obstacles;

  }
  public FollowPath(String pointname) {

    this.pointname = pointname;
    obstacles = Constants.obstacles;
    

  }
  @Override
  public void initialize() {
    var point =m_points.getPoint(pointname);
    if (point.equals(new Pose2d()) || point.getTranslation().getX() > 2.25 || point.getTranslation().getX() < 0 || point.getTranslation().getY() < 0 || point.getTranslation().getY() > 4.5){
      pointname = "Pick";
      Globals.nullFlag = true;
    }
    endflag = false;
    u = 0;
    pathMap.generateGrid(Constants.gridsize);
    pathMap.getPose(Globals.curPose, m_points.getPoint(pointname));

    for (int i = 0; i < obstacles.length; i++) {
      pathMap.getObstacles(m_points.getPoint(obstacles[i]));

    }


    pathMap.generateMap();
    pathMap.calculate();

    pathMap.Reduce();



    SmartDashboard.putString("goal", pointname);
    // movepose0 = new MovePose(pathMap.Path.get(0));
    // moveposes.add(0, new MovePose(pathMap.Path.get(0)));

    for (int i = 0; i < pathMap.Path.size(); i++) {

      SmartDashboard.putString("Pose" + i, pathMap.Path.get(i).toString());

      moveposes.add(i, new MovePose2(pathMap.Path.get(i))); 


    }
    moveposes.add(new MovePose(m_points.getPoint(pointname)));
    moveflag = new boolean[moveposes.size()];
    for (int i = 0; i < moveposes.size(); i++) {
      moveflag[i] = false;
    }
    SmartDashboard.putNumber("pathlist", pathMap.Path.size());
    SmartDashboard.putNumber("movelist", moveposes.size());

  }
  
  @Override
  public void execute() {


    if (!moveflag[u]) {
      moveposes.get(u).schedule();
      moveflag[u] = true;
    }
    else if (Globals.poserunFlag) {
      u++;
      Globals.poserunFlag = false;
    }
    if (u == moveposes.size())

      endflag = true;
    
  }
  @Override
  public boolean isFinished() {
    return endflag;
  }
  @Override
  public void end(boolean interrupted) {
    pathMap.Path.clear();
    moveposes.clear();

  }

}
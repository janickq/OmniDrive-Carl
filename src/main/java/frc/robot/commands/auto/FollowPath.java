package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  String pointname;
  String[] obstacles;
  MovePose2[] movepose2;
  boolean[] moveflag;
  boolean endflag;
  int u;

  public FollowPath(String pointname, String[] obstacles) {

    this.pointname = pointname;
    this.obstacles = obstacles;

  }
  @Override
  public void initialize() {
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
    moveflag = new boolean[pathMap.Path.size()];
    movepose2 = new MovePose2[pathMap.Path.size()];
    for (int i = 0; i < pathMap.Path.size(); i++) {

      SmartDashboard.putString("Pose" + i, pathMap.Path.get(i).toString());
      movepose2[i] = new MovePose2(pathMap.Path.get(i));
      moveflag[i] = false;

    }
    // movepose2[u].schedule();

  }
  
  @Override
  public void execute() {


    if (!moveflag[u]) {
      movepose2[u].schedule();
      moveflag[u] = true;
    }
    else if (Globals.poserunFlag) {
      u++;
      Globals.poserunFlag = false;
    }
    if(u == movepose2.length)
      endflag = true;
    
  }
@Override
  public boolean isFinished() {
    return endflag;
  }

}
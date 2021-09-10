package frc.robot;

import java.util.ArrayList;
import java.util.HashMap;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class PathMap {

  public Pathfinder pathfinder;
  public Pose2d curPose;
  public Pose2d endPose;
  public Pose2d[] Obstacles;
  public Node node;
  public boolean[][] matrix;
  public double[] gridSize;
  public int obstaclenum = 0;
  public int n;
  public int Ai;
  public int Aj;
  public int Bi;
  public int Bj;
  public HashMap<String, Pose2d> namedPath = new HashMap<>();
  public ArrayList<Pose2d> Path = new ArrayList<>();


  public void getPose(Pose2d startpoint, Pose2d endpoint) {
    curPose = startpoint;
    endPose = endpoint;
    Ai = Math.toIntExact(Math.round((curPose.getTranslation().getX() - gridSize[0])/ 2.25));
    Aj = Math.toIntExact(Math.round((curPose.getTranslation().getY() - gridSize[0]) / 4.5));
    Bi = Math.toIntExact(Math.round((endPose.getTranslation().getX() - gridSize[0])/ 2.25));
    Bj = Math.toIntExact(Math.round((endPose.getTranslation().getY() - gridSize[0])/ 4.5));
  }

  public void getObstacles(Pose2d Obstacle) {
    Obstacles[obstaclenum] = Obstacle;
    obstaclenum++;
  }

  public void generateGrid(int size) {
    n = size;
    gridSize[1] = 4.5 / n;
    gridSize[0] = 2.25 / n;
  }
  
  public void generateMap() {
    
    for (int x = 0; x <= n; x++) {
      for (int y = 0; y <= n; y++) {
        matrix[x][y] = true;
      }
    }

    for (int i = 0; i <= Obstacles.length; i++) {
      int x = Math.toIntExact(Math.round((Obstacles[i].getTranslation().getX() - gridSize[0])/ 2.25));
      int y = Math.toIntExact(Math.round((Obstacles[i].getTranslation().getY() - gridSize[0]) / 4.5));

      //larger x boundary because rectangular grid boxes
      for (int boundary = 0; boundary < 4; boundary++) {
        matrix[x + boundary][y] = false;
        matrix[x - boundary][y] = false;
      }

    }
  }
  
  public void calculate() {

    pathfinder.generateHValue(matrix, Ai, Aj, Bi, Bj, n, 1, 100, false, 1);

    for (int i = 0; i <= pathfinder.pathList.size(); i++) {
      node = pathfinder.pathList.get(i);
      namedPath.put("Waypoint" + i, new Pose2d(node.x * gridSize[0], node.y * gridSize[1], new Rotation2d(0)));
      Path.add(i, new Pose2d(node.x * gridSize[0], node.y * gridSize[1], new Rotation2d(0)));
    }

  }
  
  public void Reduce() {
    
  }

}
package frc.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathMap {

  public Pathfinder pathfinder = new Pathfinder();
  public Pose2d curPose;
  public Pose2d endPose;
  // public Pose2d[] Obstacles = new Pose2d[2];
  public ArrayList<Pose2d> Obstacles = new ArrayList<>();
  public Node node;
  public Node node2;
  public Rotation2d rotation;
  public boolean[][] matrix;
  public double[] gridSize = new double[2];
  public int obstaclenum = 0;
  public int n;
  public int Ai;
  public int Aj;
  public int Bi;
  public int Bj;
  public HashMap<String, Pose2d> namedPath = new HashMap<>();
  public ArrayList<Pose2d> Path = new ArrayList<>();
  public Points m_points = RobotContainer.m_points;

  public void getPose(Pose2d startpoint, Pose2d endpoint) {
    curPose = startpoint;
    endPose = endpoint;
    Ai = Math.toIntExact(Math.round(((curPose.getTranslation().getX()) / 2.25) * n/2));
    Aj = Math.toIntExact(Math.round(((curPose.getTranslation().getY()) / 4.5) * n));
    Bi = Math.toIntExact(Math.round(((endPose.getTranslation().getX()) / 2.25) * n/2));
    Bj = Math.toIntExact(Math.round(((endPose.getTranslation().getY()) / 4.5) * n));
  }

  public void getObstacles(Pose2d Obstacle) {
    Obstacles.add(obstaclenum, Obstacle);
    obstaclenum++;
  }

  public void generateGrid(int size) {
    n = size;
    gridSize[1] = 4.5 / n;
    gridSize[0] = 4.5 / n;
    matrix = new boolean[n][n];
  }

  public void generateMap() {

    for (int x = 0; x < n / 2; x++) {
      for (int y = 0; y < n; y++) {
        matrix[x][y] = true;
        matrix[n - x - 1][y] = false;
      }
    }
    for (int x = 0; x < n / 2; x++) {

      for (int y = 0; y < 4; y++) {
        matrix[x][n - y - 1] = false;
      }
    }
    for (int x = 0; x < 4; x++) {
      for (int y = n / 2; y < n; y++) {
        matrix[x][y] = false;
        matrix[n / 2 - x - 1][y] = false;
      }
    }

    for (int i = 0; i < Obstacles.size(); i++) {
      int x = Math.toIntExact(Math.round((Obstacles.get(i).getTranslation().getX() / 2.25) * n / 2));
      int y = Math.toIntExact(Math.round((Obstacles.get(i).getTranslation().getY() / 4.5) * n));
      createBoundary(13, 13, x, y, false);
    }
    createBoundary(10, 10, 
    Math.toIntExact(Math.round((
        m_points.getPoint("Bin").getTranslation().getX() / 2.25) * n
            / 2)),
        Math.toIntExact(
            Math.round((
                 m_points.getPoint("Bin").getTranslation().getY() / 4.5) * n)), false);
    createBoundary(13, 11, 37, 25, false);
    clearPath(Ai, Aj);
    clearPath(Bi, Bj);
 

  }
  
  public void clearPath(int u, int m) {
    boolean check = false;
    int size = 0;
    int x = Math.min(n, Math.max(u, 0));
    int y = Math.min(n, Math.max(m, 0));

    while (true) {
      check = matrix[Math.min((x + size), n-1)][y];
      if (check) {
        for (int i = 0; i <= size; i++) {
          matrix[x + i][y] = true;
        }
        break;
      }
      check = matrix[Math.max((x - size), 0)][y];
      if (check) {
        for (int i = 0; i <= size; i++) {
          matrix[x - i][y] = true;
        }
        break;
      }
      check = matrix[x][Math.min((y + size), n-1)];
      if (check) {
        for (int i = 0; i <= size; i++) {
          matrix[x][y + i] = true;
        }
        break;
      }
      check = matrix[x][Math.max((y - size), 0)];
      if (check) {
        for (int i = 0; i <= size; i++) {
          matrix[x][y - i] = true;
        }
        break;
      }
      size++;
    }

    // createBoundary(size, 1, x, y, true);
    // createBoundary(1, size, x, y, true);

  }

  public void createBoundary(int xsize, int ysize, int x, int y, boolean boundary) {

    for (int xboundary = 0; xboundary < xsize; xboundary++) {
      for (int yboundary = 0; yboundary < ysize; yboundary++) {
        int xplus = Math.min(n-1, Math.max((x + xboundary), 0));
        int xminus = Math.min(n-1, Math.max((x - xboundary), 0));
        int yplus = Math.min(n-1, Math.max((y + yboundary), 0));
        int yminus = Math.min(n-1, Math.max((y - yboundary), 0));


        matrix[xplus][yplus] = boundary;
        matrix[xplus][yminus] = boundary;
        matrix[xminus][yplus] = boundary;
        matrix[xminus][yminus] = boundary;
      }
    }
  
}

  public void calculate() {

    pathfinder.generateHValue(matrix, Ai, Aj, Bi, Bj, n, 10, 15, true, 3);
    SmartDashboard.putNumber("Pathlistsize", pathfinder.pathList.size());
    // if(curPose.getTranslation().getY()<endPose.getTranslation().getY())
    Collections.reverse(pathfinder.pathList);

    for (int i = 0; i < pathfinder.pathList.size(); i++) {
      
      node = pathfinder.pathList.get(i);

      if(i<pathfinder.pathList.size()-1){
        node2 = pathfinder.pathList.get(i+1);
        rotation = new Rotation2d(Math.atan((node.x - node2.x)/(node2.y - node.y + 0.0)));
      }
      else
        rotation = new Rotation2d(0);

      // namedPath.put("Waypoint" + i, new Pose2d(node.x * gridSize[0], node.y * gridSize[1], rotation));
      Path.add(i, new Pose2d(node.x * gridSize[0], node.y * gridSize[1], rotation));
    }

  }

  public void Reduce() {
    int i = 1;
    while(i<Path.size()-1){
      if(Path.get(i).getRotation().equals(Path.get(i-1).getRotation()))
        Path.remove(i);
      else
        i++;
    }

  }

}
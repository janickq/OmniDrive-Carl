package frc.robot;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;



public class Points{

  public Map<String, Pose2d> pointMap = new HashMap<>();
  public Map<String, Pose2d> obstacleMap = new HashMap<>();
  public Map<String, Boolean> commandMap = new HashMap<>();

  public Pose2d binPick = new Pose2d();
  public Pose2d Pick = new Pose2d(0.8, 1.12, new Rotation2d(-Math.PI / 2));
  public Pose2d Map = new Pose2d(0.85, 1.1 + 0.13, new Rotation2d(0));
  public Pose2d kitKatDrop = new Pose2d();//0.5, 3.95, new Rotation2d(0));
  public Pose2d chipsDrop = new Pose2d();//0.5, 3.95, new Rotation2d(0));
  public Pose2d waypoint1 = new Pose2d();//0.75, 3, new Rotation2d(0));
  public Pose2d nissinDrop = new Pose2d();//1.55, 3.6, new Rotation2d(-Math.PI/2));
  public Pose2d ballDrop = new Pose2d();//1.55, 3.6, new Rotation2d(-Math.PI/2));
  public Pose2d camOffset = new Pose2d();//-0.1, 0.3, new Rotation2d(0));
  public Pose2d BlueBox = new Pose2d(0, 0, new Rotation2d(0));
  public Pose2d BlackBox = new Pose2d(0, 0, new Rotation2d(0));
  public Pose2d RedBox = new Pose2d(0, 0, new Rotation2d(0));
  public Pose2d GreenBox = new Pose2d(0, 0, new Rotation2d(0));
  public Pose2d YellowBox = new Pose2d(0, 0, new Rotation2d(0));
  public Pose2d Bin = new Pose2d();
  public Pose2d Drop1 = new Pose2d();
  public Pose2d Drop2 = new Pose2d();
  public Pose2d UnadjustedDrop1 = new Pose2d();
  public Pose2d UnadjustedDrop2 = new Pose2d();
  public Pose2d unadjustedBin = new Pose2d();
  public Pose2d Bin2 = new Pose2d(1.1, 1.4, new Rotation2d(0));
  
  // public static Pose2d curPoseTransformed;
  public final Pose2d jigOffset = new Pose2d(0.22, 0.23, new Rotation2d(0));
  public final Pose2d Zero = new Pose2d(0, 0, new Rotation2d(0));


  public Points() {
    pointMap.put("Map", Map);
    pointMap.put("Bin2", Bin2);
    pointMap.put("unadjustedBin", unadjustedBin);
    pointMap.put("Zero", Zero);
    pointMap.put("Pick", Pick);
    pointMap.put("kitKatDrop", kitKatDrop);
    pointMap.put("chipsDrop", chipsDrop);
    pointMap.put("waypoint1", waypoint1);
    pointMap.put("nissinDrop", nissinDrop);
    pointMap.put("ballDrop", ballDrop);
    pointMap.put("jigOffset", jigOffset);
    pointMap.put("camOffset", camOffset);
    pointMap.put("Bin", Bin);
    pointMap.put("YellowBox", YellowBox);
    pointMap.put("GreenBox", GreenBox);
    pointMap.put("RedBox", RedBox);
    pointMap.put("BlackBox", BlackBox);
    pointMap.put("BlueBox", BlueBox);
    pointMap.put("Drop1", Drop1);
    pointMap.put("Drop2", Drop2);
    pointMap.put("unadjustedDrop1", UnadjustedDrop1);
    pointMap.put("unadjustedDrop2", UnadjustedDrop2);
    pointMap.put("binPick", binPick);
  }
  


  public void updatePoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);;
    obstacleMap.put(pointname, newpose);
  }

  public void addPoint(String pointname, Pose2d newpose) {
    pointMap.put(pointname, newpose);
    obstacleMap.put(pointname, newpose);
  }
  // @param right = true, left = false
  public void setAlignment(String pointname, Boolean direction) {
    SmartDashboard.putBoolean(pointname, direction);
    commandMap.put(pointname, direction);
    
  }
  // @param right = true, left = false
  public Boolean getAlignment(String pointname) {

    return commandMap.get(pointname);

  }
  

  public Pose2d getPoint(String pointname) {
    if (pointMap.containsKey(pointname))
      return pointMap.get(pointname);
    else
      return Globals.curPose;
  }

  public Pose2d getObstacle(String pointname) {
    if (obstacleMap.containsKey(pointname))
      return obstacleMap.get(pointname);
    else
      return Globals.curPose;
    
  }



  



  



}
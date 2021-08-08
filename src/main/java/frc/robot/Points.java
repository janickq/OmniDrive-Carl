package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;


public class Points {

  public Map<String, Pose2d> pointMap = new HashMap<>();

  public Pose2d Pick = new Pose2d(1.1-0.15, 1.1+0.16, new Rotation2d(-Math.PI/2));
  public Pose2d kitKatDrop = new Pose2d(1, 2.6, new Rotation2d(0));
  public Pose2d chipsDrop = new Pose2d(0.5, 2.6, new Rotation2d(0));
  public Pose2d waypoint1 = new Pose2d(0.35, 1.1, new Rotation2d(0));
  public Pose2d nissinDrop = new Pose2d(1, 2.6, new Rotation2d(0));
  public Pose2d ballDrop = new Pose2d(0.5, 2.6, new Rotation2d(0));
  
  // public static Pose2d curPoseTransformed;
  public final Pose2d jigOffset = new Pose2d(0.22, 0.23, new Rotation2d(0));

  public Points(){

    pointMap.put("Pick", Pick);
    pointMap.put("kitKatDrop", kitKatDrop);
    pointMap.put("chipsDrop", chipsDrop);
    pointMap.put("waypoint1", waypoint1);
    pointMap.put("nissinDrop", nissinDrop);
    pointMap.put("ballDrop", ballDrop);
    pointMap.put("jigOffset", jigOffset);

  }

  public void updatePoint(String pointname, Pose2d newpose) {
    pointMap.replace(pointname, newpose);
  }
  

  public Pose2d getPoint(String pointname){
    if (pointMap.containsKey(pointname))
      return pointMap.get(pointname);
    else
      return Globals.curPose;
  }




  



  



}
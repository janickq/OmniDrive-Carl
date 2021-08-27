package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;


public class Points {

  public Map<String, Pose2d> pointMap = new HashMap<>();

  public Pose2d Pick = new Pose2d(1.1-0.14, 1.1+0.16, new Rotation2d(-Math.PI/2));
  public Pose2d kitKatDrop = new Pose2d(0.5, 3.95, new Rotation2d(0));
  public Pose2d chipsDrop = new Pose2d(0.5, 3.95, new Rotation2d(0));
  public Pose2d waypoint1 = new Pose2d(0, 1, new Rotation2d(0));
  public Pose2d nissinDrop = new Pose2d(1.55, 3.6, new Rotation2d(-Math.PI/2));
  public Pose2d ballDrop = new Pose2d(1.55, 3.6, new Rotation2d(-Math.PI/2));
  public Pose2d camOffset = new Pose2d(-0.1, 0.3, new Rotation2d(0));
  public Pose2d BlueBox = new Pose2d();
  public Pose2d BlackBox = new Pose2d();
  public Pose2d RedBox = new Pose2d();
  public Pose2d GreenBox = new Pose2d();
  public Pose2d YellowBox = new Pose2d();
  public Pose2d Bin = new Pose2d();
  public Pose2d Drop1 = new Pose2d();
  public Pose2d Drop2 = new Pose2d();
  
  // public static Pose2d curPoseTransformed;
  public final Pose2d jigOffset = new Pose2d(0.22, 0.23, new Rotation2d(0));
  public final Pose2d Zero = new Pose2d(0, 0, new Rotation2d(0));


  public Points(){

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

  }

  public void updatePoint(String pointname, Pose2d newpose) {
    pointMap.replace(pointname, newpose);
  }

  public void addPoint(String pointname, Pose2d newpose){
    pointMap.put(pointname, newpose);
  }
  

  public Pose2d getPoint(String pointname){
    if (pointMap.containsKey(pointname))
      return pointMap.get(pointname);
    else
      return Globals.curPose;
  }




  



  



}
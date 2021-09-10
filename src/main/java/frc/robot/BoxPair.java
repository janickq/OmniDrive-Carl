package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class BoxPair {

  public String box1;
  public String box2;
  public double distance;
  public Pose2d dropPoint;

  public BoxPair(String box1, String box2, double distance) {
    this.box1 = box1;
    this.box2 = box2;
    this.distance = distance;
  }

  public double getDistance() {
    return distance;
  }

}
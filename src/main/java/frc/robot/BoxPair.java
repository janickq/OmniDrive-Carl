package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class BoxPair{

  public String box1;
  public String box2;
  String item1;
  String item2;
  public double distance;
  public Pose2d dropPoint;

  public BoxPair(String box1, String box2, double distance, String item1, String item2) {
    this.box1 = box1;
    this.box2 = box2;
    this.item1 = box1;
    this.item2 = box2;
    this.distance = distance;
  }

  public double getDistance() {
    return distance;
  }
  // @Override
  // public int compareTo(BoxPair o)
  // {
  //   return this.distance.compareTo(o.getDistance());
  // }

}
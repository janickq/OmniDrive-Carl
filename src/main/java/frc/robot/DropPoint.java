package frc.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DropPoint {

  ArrayList<MedianFilter> filters = new ArrayList<>(16);
  Comparator<BoxPair> byDistance = Comparator.comparing(BoxPair::getDistance);

  String[] boxes = new String[] { "RedBox", "BlueBox", "YellowBox", "BlackBox", "GreenBox" };
  BoxPair[] boxPair;
  
  Pose2d Drop1;
  Pose2d Drop2;
  Pose2d RedBox;
  Pose2d GreenBox;  
  Pose2d BlueBox;
  Pose2d BlackBox;
  Pose2d YellowBox;
  Pose2d Bin;
  static final Points m_points = RobotContainer.m_points;

  public void getBoxes(Pose2d[] box) {
    for (int i = 0; i < boxes.length; i++) {
      m_points.updatePoint(boxes[i],
      new Pose2d(
        filters.get(i).calculate(SmartDashboard.getNumber(boxes[i]+"x", 0) / 100), 
        filters.get(boxes.length*2-i).calculate(-SmartDashboard.getNumber(boxes[i]+"y", 0) / 100), new Rotation2d(0))

      );

    }


    m_points.updatePoint("Bin",
        new Pose2d(
          filters.get(11).calculate(SmartDashboard.getNumber("Binx", 0) / 100),
          filters.get(12).calculate(-SmartDashboard.getNumber("Biny", 0) / 100), new Rotation2d(0))

    );

    RedBox = m_points.getPoint("RedBox");
    BlueBox = m_points.getPoint("BlueBox");
    BlackBox = m_points.getPoint("BlackBox");
    YellowBox = m_points.getPoint("YellowBox");
    GreenBox = m_points.getPoint("GreenBox");
    Bin = m_points.getPoint("Bin");

  }
  
  public void generatePair() {
    int x = 0;
    for (int i = 0; i < boxes.length; i++) {

      for (int j = i + 1; j < boxes.length; j = i + 1) {

        var x1 = m_points.getPoint(boxes[i]).getTranslation().getX();
        var x2 = m_points.getPoint(boxes[j]).getTranslation().getX();
        var y1 = m_points.getPoint(boxes[i]).getTranslation().getY();
        var y2 = m_points.getPoint(boxes[j]).getTranslation().getY();

        double dist = Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        boxPair[x] = new BoxPair(boxes[i], boxes[j], dist);
        x++;

      }
    }
    Arrays.sort(boxPair, byDistance);
  }

  public void getDropPose() {
    
  }
  

}
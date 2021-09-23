package frc.robot.commands.auto;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.DropPoint;
import frc.robot.Globals;
import frc.robot.PathMap;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class MapPose extends CommandBase {

  Pose2d[] boxes = new Pose2d[5];
  String[] obstacles;
  String[] boxname = { "RedBox", "BlueBox", "YellowBox", "BlackBox", "GreenBox" };

  String end;
  String box1, box2, posename, item1, item2;


  PathMap pathMap = new PathMap();
  static final DropPoint m_drop = new DropPoint();

  private static final Points m_points = RobotContainer.m_points;
  private static final Vision m_vision = RobotContainer.m_vision;



  boolean move;
  boolean endflag;



  public MapPose() {
    move = false;
  }





  @Override
  public void initialize() {
    endflag = false;

    m_drop.getBoxes();
    m_drop.generatePair();
    m_drop.getDropPose();
    endflag = true;

    
    
  }

  int i = 1;

  @Override
  public void execute() {

  }




  
  @Override
  public boolean isFinished() {
    return endflag;
  }
  @Override
  public void end(boolean interrupted) {

    endflag = false;
  }
}
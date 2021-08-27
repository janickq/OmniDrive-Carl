package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class MapPose extends CommandBase{

  private static final Points m_points = RobotContainer.m_points;
  private static final Vision m_vision = RobotContainer.m_vision;
  String box1, box2, posename;
  boolean endflag;
  MedianFilter filter1 = new MedianFilter(10);
  MedianFilter filter2 = new MedianFilter(10);
  MedianFilter filter3 = new MedianFilter(10);
  MedianFilter filter4 = new MedianFilter(10);

  public MapPose(String box1, String box2, String posename) {

    this.box1 = box1;
    this.box2 = box2;
    this.posename = posename;

  }
  
  public void getDropPoint() {
    m_points.updatePoint(
      box1, 
      new Pose2d(
        filter1.calculate(SmartDashboard.getNumber(box1 + "x", 0)/100),
        filter2.calculate(SmartDashboard.getNumber(box1 + "y", 0)/100),
        new Rotation2d(0)
      )
    
    );
    m_points.updatePoint(
      box2, 
      new Pose2d(
        filter3.calculate(SmartDashboard.getNumber(box2 + "x", 0)/100),
        filter4.calculate(SmartDashboard.getNumber(box2 + "y", 0)/100),
        new Rotation2d(0)
      )
    
    );
    m_points.updatePoint(posename, 
      new Pose2d(
        (m_points.getPoint(box1).getTranslation().getX() + 
          m_points.getPoint(box2).getTranslation().getX()
        )/2,

        (-m_points.getPoint(box1).getTranslation().getY() + 
        -m_points.getPoint(box2).getTranslation().getY()
        )/2,
          
        new Rotation2d(  
          Math.atan(
            (
              -m_points.getPoint(box1).getTranslation().getY() - 
              -m_points.getPoint(box2).getTranslation().getY()
            )/(
              m_points.getPoint(box1).getTranslation().getX() - 
              m_points.getPoint(box2).getTranslation().getX()
            )
          )
        )
      ).transformBy(
        new Transform2d(
          new Translation2d(0.1, -0.6),
          new Rotation2d(0)
        )
      )
    );
    

  }

  @Override
  public void initialize() {
    endflag = false;
  }

  int i = 1;
  @Override
  public void execute() {
    getDropPoint();
    i++;
    if (i > 10)
      endflag = true;
  }
  
  @Override
  public boolean isFinished() {
    return endflag;
  }
  @Override
  public void end(boolean interrupted) {
    endflag = true;
  }
}
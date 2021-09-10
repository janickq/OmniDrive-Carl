package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.DropPoint;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class MapPose extends CommandBase {

  static final DropPoint m_drop = new DropPoint();
  private static final Points m_points = RobotContainer.m_points;
  private static final Vision m_vision = RobotContainer.m_vision;
  String[] boxname = new String[] { "RedBox", "BlueBox", "YellowBox", "BlackBox", "GreenBox" };
  Pose2d[] boxes;
  String box1, box2, posename, item1, item2;
  boolean endflag;
  MedianFilter filter1 = new MedianFilter(10);
  MedianFilter filter2 = new MedianFilter(10);
  MedianFilter filter3 = new MedianFilter(10);
  MedianFilter filter4 = new MedianFilter(10);

  public MapPose() {
    
  }

  public MapPose(String box1, String box2, String posename) {

    this.box1 = box1;
    this.box2 = box2;
    this.posename = posename;

  }

  public MapPose(String box1, String box2, String posename, String item1, String item2) {

    this.box1 = box1;
    this.box2 = box2;
    this.posename = posename;
    this.item1 = item1;
    this.item2 = item2;

  }

  @Override
  public void initialize() {

    endflag = false;
    
  }

  int i = 1;
  @Override
  public void execute() {
    getDropPoint();
    setAlignment();
    i++;
    if (i > 10)
      endflag = true;
  }

  public void getBoxes(String[] box) {
    for (int i = 0; i < boxes.length; i++) {
      m_points.updatePoint(boxname[i],
        new Pose2d(
        (SmartDashboard.getNumber(boxes[i]+"x", 0) / 100), 
        (-SmartDashboard.getNumber(boxes[i]+"y", 0) / 100),
         new Rotation2d(0))

      );

    }
    
  }

  public void setAlignment() {

    Transform2d relativePose1 = m_points.getPoint("UnadjustedDrop").minus(m_points.getPoint(box1)
        .transformBy(new Transform2d(new Translation2d(0, 0), m_points.getPoint("UnadjustedDrop").getRotation())));

    Transform2d relativePose2 = m_points.getPoint("UnadjustedDrop").minus(m_points.getPoint(box2)
        .transformBy(new Transform2d(new Translation2d(0, 0), m_points.getPoint("UnadjustedDrop").getRotation())));

    if (relativePose1.getTranslation().getX() < 0) {
      m_points.setAlignment(box1, false);
      m_points.setAlignment(box2, true);
    }

    else {
      m_points.setAlignment(box1, true);
      m_points.setAlignment(box2, false);
    }

    Globals.debug10 = relativePose1.toString();
    Globals.debug9 = relativePose2.toString();

  }

  public double getDropAngle(double ang) {
    double dropAngle = Math.toDegrees(ang);
    if (dropAngle > 75 && dropAngle < 105)
      return Math.PI / 2;
    else if (dropAngle < -75 && dropAngle > -105) return -Math.PI/2;
    else if (dropAngle > -10 && dropAngle < 10) return 0; 
    else return Math.toRadians(dropAngle);
  }
  
  public void getDropPoint() {
    m_points.updatePoint(
      box1, 
      new Pose2d(
        filter1.calculate(SmartDashboard.getNumber(box1 + "x", 0)/100),
        filter2.calculate(-SmartDashboard.getNumber(box1 + "y", 0)/100),
        new Rotation2d(0)
      )
    
    );
    m_points.updatePoint(
      box2, 
      new Pose2d(
        filter3.calculate(SmartDashboard.getNumber(box2 + "x", 0)/100),
        filter4.calculate(-SmartDashboard.getNumber(box2 + "y", 0)/100),
        new Rotation2d(0)
      )
    
    );

    m_points.updatePoint("UnadjustedDrop", 
      new Pose2d(
        new Translation2d(
          (m_points.getPoint(box1).getTranslation().getX() + 
          m_points.getPoint(box2).getTranslation().getX()
          )/2,

          (m_points.getPoint(box1).getTranslation().getY() + 
          m_points.getPoint(box2).getTranslation().getY()
          )/2
        ),
        new Rotation2d( 
          Math.atan(
            (
              m_points.getPoint(box1).getTranslation().getY() - 
              m_points.getPoint(box2).getTranslation().getY()
            )/(
              m_points.getPoint(box1).getTranslation().getX() - 
              m_points.getPoint(box2).getTranslation().getX()
            )
          
        ))

      )
    );

    m_points.updatePoint(posename, Globals.curPose.transformBy(
      new Transform2d(
        new Translation2d(
          (m_points.getPoint(box1).getTranslation().getX() + 
          m_points.getPoint(box2).getTranslation().getX()
          )/2,

          (m_points.getPoint(box1).getTranslation().getY() + 
          m_points.getPoint(box2).getTranslation().getY()
          )/2
        ),
        new Rotation2d( getDropAngle( 
          Math.atan(
            (
              m_points.getPoint(box1).getTranslation().getY() - 
              m_points.getPoint(box2).getTranslation().getY()
            )/(
              m_points.getPoint(box1).getTranslation().getX() - 
              m_points.getPoint(box2).getTranslation().getX()
            )
          )
        ))

      )
    ).transformBy(  
        new Transform2d(
          new Translation2d(0.1, -0.7),
          new Rotation2d(0)
        )
    )
      
    );
    

  }


  
  @Override
  public boolean isFinished() {
    return endflag;
  }
  @Override
  public void end(boolean interrupted) {
    m_points.updatePoint(item1, m_points.getPoint(posename));
    m_points.updatePoint(item2, m_points.getPoint(posename));
    endflag = false;
  }
}
package frc.robot.subsystems;

import com.studica.frc.Servo;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;

public class Vision extends SubsystemBase
{
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table = inst.getTable("Image");
    private NetworkTableEntry data;
    private final Servo servo3;
    private final Points m_points = RobotContainer.m_points;
    

    double cameraAngle;
    boolean pickedflag;

    private boolean getNewBarcode;
    private double convert = 0.0013;

    // private final GetTrajectory m_trajectory = RobotContainer.m_trajectory;
    
    public Vision()
    {
        SmartDashboard.putBoolean("Get New Barcode", false);

        servo3 = new Servo(Constants.SERVO4);
        boxLook();
        servo3.setAngle(120);
        
    }

    //true = boxes false = items
    public void boxLook() {

      pickedflag = true;
      cameraAngle = 120;
    }
    
    public void itemLook() {
      pickedflag = false;
      cameraAngle = 25 ;
    }

    public void readBarcode()
    {
        table.getEntry("readBarcode").setBoolean(true);
   
    }
    
    public void printBarcode()
    {
        data = table.getEntry("barcodeData");
        SmartDashboard.putString("Barcode Data", data.getString("Nothing was read"));
    }

    public double getChips(int xy){

      double[] chips = new double[2];

      chips[0] = (SmartDashboard.getNumber("Chipsx",0));
      chips[1] = (SmartDashboard.getNumber("Chipsy",0));

      return chips[xy]*convert ;
    }

    public double getBall(int xy){

      double[] ball = new double[2];

      ball[0] = (SmartDashboard.getNumber("Ballx",0));
      ball[1] = (SmartDashboard.getNumber("Bally",0));

      return ball[xy]*convert;
    }

    public double getNissin(int xy){

      double[] nissin = new double[2];

      nissin[0] = (SmartDashboard.getNumber("Nissinx",0));
      nissin[1] = (SmartDashboard.getNumber("Nissiny",0));

      return nissin[xy]*convert;
    }

    public double getKitkat(int xy){

      double[] kitkat = new double[2];

      kitkat[0] = (SmartDashboard.getNumber("KitKatx",0));
      kitkat[1] = (SmartDashboard.getNumber("KitKaty",0));

      return kitkat[xy]*convert;
    }

    public void getItem(){

      Globals.start = true;
        if (getChips(0) != 0 && getChips(1) != 0)
            Globals.curItem = 0;
        else if (getNissin(0) != 0 && getNissin(1) != 0)
            Globals.curItem = 3;
        else if (getKitkat(0) != 0 && getKitkat(1) != 0)
            Globals.curItem = 2;
        else if (getBall(0) != 0 && getBall(1) != 0)
            Globals.curItem = 1;
        else{
            Globals.curItem = 4;
            Globals.start = false;
        }
      
    }

    public void getItemBool(){
      

      if (Globals.curItem == 4)
        Globals.checkItem =true;
      else
        Globals.checkItem = false;
        

    }
    public double getDistance(String xbox, String ybox, int i){

      double[] boxDist = new double[2];

      boxDist[1] = 24424/SmartDashboard.getNumber(ybox, 0);
      boxDist[0] = Math.tan((SmartDashboard.getNumber(xbox, 0)/320)*35)*boxDist[1];


      return boxDist[i];


    }
    
    public Pose2d getDropPose(String point1, String point2, String posename){

      m_points.updatePoint(
        point1, 
        new Pose2d(
          SmartDashboard.getNumber(point1 + "x", 0)/100,
          SmartDashboard.getNumber(point1 + "y", 0)/100,
          new Rotation2d(0)
        )
      
      );
      m_points.updatePoint(
        point2, 
        new Pose2d(
          SmartDashboard.getNumber(point2 + "x", 0)/100,
          SmartDashboard.getNumber(point2 + "y", 0)/100,
          new Rotation2d(0)
        )
      
      );
      m_points.updatePoint(posename, new Pose2d(
        (m_points.getPoint(point1).getTranslation().getX() + 
          m_points.getPoint(point2).getTranslation().getX()
        )/2,

        (-m_points.getPoint(point1).getTranslation().getY() + 
         -m_points.getPoint(point2).getTranslation().getY()
        )/2,
          
        new Rotation2d(  
          Math.atan(
            (
              -m_points.getPoint(point1).getTranslation().getY() - 
              -m_points.getPoint(point2).getTranslation().getY()
            )/(
              m_points.getPoint(point1).getTranslation().getX() - 
              m_points.getPoint(point2).getTranslation().getX()
            )
          )
        )

      ).transformBy(
        new Transform2d(
          new Translation2d(-0.2, -0.4),
          new Rotation2d(0)
        )
      )
    );

      return new Pose2d(
        (m_points.getPoint(point1).getTranslation().getX() + 
          m_points.getPoint(point2).getTranslation().getX()
        )/2,

        (-m_points.getPoint(point1).getTranslation().getY() + 
         -m_points.getPoint(point2).getTranslation().getY()
        )/2,
          
        new Rotation2d(  
          Math.atan(
            (
              -m_points.getPoint(point1).getTranslation().getY() - 
              -m_points.getPoint(point2).getTranslation().getY()
            )/(
              m_points.getPoint(point1).getTranslation().getX() - 
              m_points.getPoint(point2).getTranslation().getX()
            )
          )
        )

        ).transformBy(
          new Transform2d(
            new Translation2d(-0.2, -0.4),
            new Rotation2d(0)
          )
        );

    }


 

    @Override
    public void periodic()
    {
        printBarcode();
        getNewBarcode = SmartDashboard.getBoolean("Get New Barcode", false);


        Globals.kitkatx = SmartDashboard.getNumber("KitKatx",0);  
        Globals.kitkaty = SmartDashboard.getNumber("KitKaty",0);

        Globals.chipsx = SmartDashboard.getNumber("Chipsx",0);
        Globals.chipsy = SmartDashboard.getNumber("Chipsy",0);

        Globals.nissinx = SmartDashboard.getNumber("Nissinx",0);
        Globals.nissiny = SmartDashboard.getNumber("Nissiny",0);
        
        String redbox = "RedBox";
        SmartDashboard.putNumber("RedBoxx", SmartDashboard.getNumber("RedBoxx", 0));
        SmartDashboard.putNumber("RedBoxy", SmartDashboard.getNumber(redbox+"y", 0));
        SmartDashboard.putString("DropPose1", m_points.getPoint("Drop1").toString());
        SmartDashboard.putString("DropPose2", m_points.getPoint("Drop2").toString());


        // getDropPose("YellowBox", "GreenBox", "Drop2"); 
        
        

        SmartDashboard.putNumber("curItem", Globals.curItem);
        SmartDashboard.putNumber("chipy", getChips(1));
        
        SmartDashboard.putNumber("nissinx", getNissin(0));
        SmartDashboard.putNumber("nissiny", getNissin(1));  

        SmartDashboard.putBoolean("checkItem", Globals.checkItem);
        SmartDashboard.putBoolean("Start", Globals.start);
        SmartDashboard.putBoolean("runFlag", Globals.runFlag);
        SmartDashboard.putBoolean("flag", Globals.debug1);
        SmartDashboard.putNumber("state", Globals.debug2);  
        SmartDashboard.putBoolean("m_endflag", Globals.debug4);
        // SmartDashboard.putStringArray("trajectorylist", getArray() );
        if (getNewBarcode)
        {
            readBarcode();
            SmartDashboard.putBoolean("Get New Barcode", false);
        }
        SmartDashboard.putNumber("debug8", Globals.debug8);
        SmartDashboard.putString("relativePose2", Globals.debug9);
        SmartDashboard.putString("relativePose1", Globals.debug10);
        SmartDashboard.putString("box1", m_points.getPoint("BlueBox").toString());
        SmartDashboard.putString("box2", m_points.getPoint("YellowBox").toString());


        SmartDashboard.putBoolean("mapping", pickedflag);
        servo3.setAngle(cameraAngle);


    }

       
    
}
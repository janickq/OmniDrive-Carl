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

import frc.robot.RobotContainer;

public class Vision extends SubsystemBase
{
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table = inst.getTable("Image");
    private NetworkTableEntry data;
    private final Servo servo3;

    

    double cameraAngle;
    boolean pickedflag;

    private boolean getNewBarcode;
    private double convert = 0.0012;

    // private final GetTrajectory m_trajectory = RobotContainer.m_trajectory;
    
    public Vision()
    {
        SmartDashboard.putBoolean("Get New Barcode", false);

        servo3 = new Servo(Constants.SERVO4);
        boxLook();
        servo3.setAngle(150);
        
    }

    //true = boxes false = items
    public void boxLook() {

      pickedflag = true;
      cameraAngle = 175;
    }
    
    public void itemLook() {
      pickedflag = false;
      cameraAngle = 55 ;
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

    public double getKitkat(int xy) {

      double[] kitkat = new double[2];

      kitkat[0] = (SmartDashboard.getNumber("KitKatx", 0));
      kitkat[1] = (SmartDashboard.getNumber("KitKaty", 0));

      return kitkat[xy] * convert;
    }

    public boolean checkOverlap(double x1, double y1, double x2, double y2) {

      return (Math.sqrt((Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))) < 0.05);
      //   return true;
      // else return false;

    }

    public void resetCount() {
      for (int i = 0; i < Globals.itemCount.length; i++) {
        Globals.itemCount[i] = 0;
      }
    }

    public void getItem(){

      Globals.start = true;
      if (getChips(0) != 0 && getChips(1) != 0 && Globals.itemCount[0] <2) {

        if ((getKitkat(0) != 0 && getKitkat(1) != 0)
            && checkOverlap(getKitkat(0), getKitkat(1), getChips(0), getChips(1))) {
          Globals.curItem = 2;
        } 
        else if ((getBall(0) != 0 && getBall(1) != 0)
            && checkOverlap(getBall(0), getBall(1), getChips(0), getChips(1))) {
          Globals.curItem = 1;

        } 
        else if ((getNissin(0) != 0 && getNissin(1) != 0)
            && checkOverlap(getBall(0), getBall(1), getChips(0), getChips(1))) {
          Globals.curItem = 3;
        } 
        else {
          Globals.curItem = 0;
        }
      }
      
      else if (getNissin(0) != 0 && getNissin(1) != 0 && Globals.itemCount[3] < 3) {
        
        if ((getKitkat(0) != 0 && getKitkat(1) != 0)
            && checkOverlap(getKitkat(0), getKitkat(1), getNissin(0), getNissin(1))) 
          Globals.curItem = 2;

        else if ((getBall(0) != 0 && getBall(1) != 0)
            && checkOverlap(getBall(0), getBall(1), getNissin(0), getNissin(1)))

          Globals.curItem = 1;
        else 
          Globals.curItem = 3;
      }
      else if (getBall(0) != 0 && getBall(1) != 0 && Globals.itemCount[1] <3)
        Globals.curItem = 1;

      else if (getKitkat(0) != 0 && getKitkat(1) != 0 && Globals.itemCount[2] < 3)
          Globals.curItem = 2;

      else{
          Globals.curItem = 4;
          Globals.start = false;
      }
      
    }


    public double getDistance(String xbox, String ybox, int i) {

      double[] boxDist = new double[2];

      boxDist[1] = 24424 / SmartDashboard.getNumber(ybox, 0);
      boxDist[0] = Math.tan((SmartDashboard.getNumber(xbox, 0) / 320) * 35) * boxDist[1];

      return boxDist[i];

    }


    public void pickBall() {
      Globals.curItem = 1;
    }

    public void pickChips() {
      Globals.curItem = 0;
    }

    public void pickNissin() {
      Globals.curItem = 3;
    }

    public void pickKitkat() {
      Globals.curItem = 2;
    }
    
 

    @Override
    public void periodic()
    {

        SmartDashboard.putBoolean("mapping", pickedflag);
        servo3.setAngle(cameraAngle);


    }

       
    
}
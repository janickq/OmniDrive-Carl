package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.TrajectoryProf;
import frc.robot.Globals;
import frc.robot.RobotContainer;

public class Vision extends SubsystemBase
{
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table = inst.getTable("Image");
    private NetworkTableEntry data;
    
    // public NetworkTableEntry kitkatx;
    // public NetworkTableEntry kitkaty;

    private boolean getNewBarcode;
    private double convert = 0.0005;

    // private final GetTrajectory m_trajectory = RobotContainer.m_trajectory;
    
    public Vision()
    {
        SmartDashboard.putBoolean("Get New Barcode", false);
        
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
    // public String[] getArray(){
    //   var trajectorylist = m_trajectory.generateTrajectory();
    //   String[] array = Arrays.stream(trajectorylist).toArray(String[]::new);

    //   return array;
      
    // }

 

    @Override
    public void periodic()
    {
        printBarcode();
        getNewBarcode = SmartDashboard.getBoolean("Get New Barcode", false);
        Globals.debug7 = SmartDashboard.getNumber("dsTime" ,1);

        Globals.kitkatx = SmartDashboard.getNumber("KitKatx",0);
        Globals.kitkatx = Globals.kitkatx*0.0002645833;
        Globals.kitkaty = SmartDashboard.getNumber("KitKaty",0);
        Globals.kitkaty = Globals.kitkaty*0.0002645833;
        Globals.chipsx = SmartDashboard.getNumber("Chipsx",0);
        Globals.chipsy = SmartDashboard.getNumber("Chipsy",0);

        Globals.nissinx = SmartDashboard.getNumber("Nissinx",0);
        Globals.nissiny = SmartDashboard.getNumber("Nissiny",0);

        Globals.ballx = SmartDashboard.getNumber("Ballx",0);
        Globals.ballx = SmartDashboard.getNumber("Bally",0);

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
        SmartDashboard.putNumber("debug9", Globals.debug9);
        SmartDashboard.putNumber("debug10", Globals.debug10);

    }

       
    
}
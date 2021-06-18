package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;

public class Vision extends SubsystemBase
{
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table = inst.getTable("Image");
    private NetworkTableEntry data;
    
    // public NetworkTableEntry kitkatx;
    // public NetworkTableEntry kitkaty;

    private boolean getNewBarcode;
    


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

      return chips[xy]*0.00043160218;
    }

    public double getBall(int xy){

      double[] ball = new double[2];

      ball[0] = (SmartDashboard.getNumber("Ballx",0));
      ball[1] = (SmartDashboard.getNumber("Bally",0));

      return ball[xy]*0.00043160218;
    }

    public double getNissin(int xy){

      double[] nissin = new double[2];

      nissin[0] = (SmartDashboard.getNumber("Nissinx",0));
      nissin[1] = (SmartDashboard.getNumber("Nissiny",0));

      return nissin[xy]*0.00043;
    }

    public double getKitkat(int xy){

      double[] kitkat = new double[2];

      kitkat[0] = (SmartDashboard.getNumber("KitKatx",0));
      kitkat[1] = (SmartDashboard.getNumber("KitKaty",0));

      return kitkat[xy]*0.00043160218;
    }

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

        SmartDashboard.putNumber("chipx", getChips(0));
        SmartDashboard.putNumber("chipy", getChips(1));
        
        SmartDashboard.putNumber("nissinx", getNissin(0));
        SmartDashboard.putNumber("nissiny", getNissin(1));  



        if (getNewBarcode)
        {
            readBarcode();
            SmartDashboard.putBoolean("Get New Barcode", false);
        }

       
    }
}
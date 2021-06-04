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

    @Override
    public void periodic()
    {
        printBarcode();
        getNewBarcode = SmartDashboard.getBoolean("Get New Barcode", false);
        Globals.debug7 = SmartDashboard.getNumber("dsTime" ,1);

        Globals.kitkatx = SmartDashboard.getNumber("KitKatx",1);
        Globals.kitkatx = Globals.kitkatx*0.0002645833;
        Globals.kitkaty = SmartDashboard.getNumber("KitKaty",1);
        Globals.kitkaty = Globals.kitkaty*0.0002645833;
        Globals.chipsx = SmartDashboard.getNumber("Chipsx",1);
        Globals.chipsy = SmartDashboard.getNumber("Chipsy",1);

        Globals.nissinx = SmartDashboard.getNumber("Nissinx",1);
        Globals.nissiny = SmartDashboard.getNumber("Nissiny",1);

        Globals.ballx = SmartDashboard.getNumber("Ballx",1);
        Globals.ballx = SmartDashboard.getNumber("Bally",1);

        
        
   



        if (getNewBarcode)
        {
            readBarcode();
            SmartDashboard.putBoolean("Get New Barcode", false);
        }

       
    }
}
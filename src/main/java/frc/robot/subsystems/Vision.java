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
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
import frc.robot.Globals;

import frc.robot.RobotContainer;

public class Vision extends SubsystemBase
{

    private final ShuffleboardTab tab = Shuffleboard.getTab("Vision");
    private NetworkTableInstance inst = NetworkTableInstance.getDefault();
    private NetworkTable table = inst.getTable("Image");
    private NetworkTableEntry data;
    private final Servo servo3;

    private final NetworkTableEntry WOB = tab.add("WOB", 0).getEntry();
    private final NetworkTableEntry debug = tab.add("debug", 0).getEntry();
    private final NetworkTableEntry debug2 = tab.add("debug2", 0).getEntry();
    private final NetworkTableEntry pose = tab.add("pose", 0).getEntry();
    private final NetworkTableEntry command = tab.add("command", 0).getEntry();

    double cameraAngle;
    boolean pickedflag;

    String[] DefaultStr = {"0"};
    private double convert = 0.0012;

    // private final GetTrajectory m_trajectory = RobotContainer.m_trajectory;
    
    public Vision()
    {
        servo3 = new Servo(Constants.SERVO4);

    }
 

    @Override
    public void periodic()
    {
        // WOB.setString("test");
        // SmartDashboard.putBoolean("mapping", pickedflag);
        // servo3.setAngle(cameraAngle);
        // SmartDashboard.getStringArray("Deliver", DefaultStr);
        // SmartDashboard.getStringArray("Return", DefaultStr);
        


    }

       
    
}
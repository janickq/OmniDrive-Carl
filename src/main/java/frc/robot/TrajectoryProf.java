package frc.robot;

import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.objdetect.HOGDescriptor;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpi;


public class TrajectoryProf extends SubsystemBase {

  String[] traj;
  String[] string = new String[] { "asd", "sd", "fdsa" };
  String string2;
  private final ShuffleboardTab tab = Shuffleboard.getTab("Trajectory");

  private final NetworkTableEntry D_TrajectoryList = tab.add("Trajectory List", 1).getEntry();

  public TrajectoryProf() {
    Object[] objs = generateTrajectory();
    traj = asStrings(objs);
    string2 = String.join("", string);

  }

  public static String[] asStrings(Object... objArray) {
    String[] strArray = new String[objArray.length];
    for (int i = 0; i < objArray.length; i++)
        strArray[i] = String.valueOf(objArray[i]);
    return strArray;
  }
  public Object[] generateTrajectory() {

    // 2018 cross scale auto waypoints.
    var start = new Pose2d(0, 0,
        Rotation2d.fromDegrees(0));
    var end = new Pose2d(3, 3,
        Rotation2d.fromDegrees(-160));

    var interiorWaypoints = new ArrayList<Translation2d>();
    interiorWaypoints.add(new Translation2d(2, 0));
    interiorWaypoints.add(new Translation2d(0, 2));

    TrajectoryConfig config = new TrajectoryConfig(0.5, 0.5);
    config.setReversed(false);

    var trajectory = TrajectoryGenerator.generateTrajectory(
        start,
        interiorWaypoints,
        end,
        config);
    // System.out.println(Arrays.toString(trajectory.getStates().toArray()));
    var trajectorylist = trajectory.getStates().toArray();
    // String[] array = Arrays.copyOf(trajectorylist, trajectorylist.length, String[].class);

    return  trajectorylist;
    

    // return trajectory.getStates().toArray();
  }

  

  @Override
  public void periodic() {
    // TODO Auto-generated method stub
    SmartDashboard.putStringArray("trajectory", traj);
    D_TrajectoryList.setString("asdasfdsfudghoiajfdks");
    System.out.println("hello");
  }
}



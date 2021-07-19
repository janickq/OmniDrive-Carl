package frc.robot;

import java.util.ArrayList;

import javax.xml.stream.events.DTD;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpiutil.math.MathUtil;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.Trajectory.State;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.HolonomicDriveController;
import frc.robot.subsystems.OmniDrive;

public class DriveTest2 extends CommandBase {

  // private final OmniDrive m_drive = RobotContainer.m_omnidrive;

  public DriveTest2() {
    

  }



  public void DriveTest2(double dT) {

    // double duration = duration+dT;

    // double totalduration = generateTrajectory().getTotalTimeSeconds();

    // double sampleduration;

	  // if(duration<totalduration)
    //   sampleduration = duration;

    // Trajectory.State goal = generateTrajectory().sample(sampleduration);

    // var controller = new HolonomicDriveController(new PIDController(1, 0, 0), new PIDController(1, 0, 0),
    //                 new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(3.14, 3.14)));
                    


    // ChassisSpeeds adjustedSpeeds = controller.calculate(
    //                             , goal, Rotation2d.fromDegrees(70.0));

    
  }

  public Trajectory generateTrajectory() {

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

    return trajectory;
    


    // return trajectory.getStates().toArray();
  }

  

  @Override
  public void execute() {
    // TODO Auto-generated method stub
    super.execute();
  }
}


package frc.robot.commands.auto;
/*----------------------------------------------------------------------------*/

/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */

/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

import java.util.function.Supplier;

/**
 * A command that uses two PID controllers ({@link PIDController}) and a
 * ProfiledPIDController ({@link ProfiledPIDController}) to follow a trajectory
 * {@link Trajectory} with a mecanum drive.
 *
 * <p>The command handles trajectory-following,
 * Velocity PID calculations, and feedforwards internally. This
 * is intended to be a more-or-less "complete solution" that can be used by teams without a great
 * deal of controls expertise.
 *
 * <p>Advanced teams seeking more flexibility (for example, those who wish to use the onboard
 * PID functionality of a "smart" motor controller) may use the secondary constructor that omits
 * the PID and feedforward functionality, returning only the raw wheel speeds from the PID
 * controllers.
 *
 * <p>The robot angle controller does not follow the angle given by
 * the trajectory but rather goes to the angle given in the final state of the trajectory.
 */

@SuppressWarnings({"PMD.TooManyFields", "MemberName"})
public class OmniTrackTrajectoryCommand extends CommandBase {
  private final Timer m_timer = new Timer();
  private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
  private ChassisSpeeds m_prevSpeeds;
  private double m_prevTime;
  private Pose2d m_finalPose;
  private double curTime;

  private final Supplier<Trajectory> m_trajectory;
  private final Supplier<Pose2d> m_pose;
  private final SimpleMotorFeedforward m_feedforward;
  private final PIDController m_xController;
  private final PIDController m_yController;
  private final ProfiledPIDController m_thetaController;
 MecanumControllerCommand m_ram;
 RamseteCommand m_cm;

  /**
   * Constructs a new MecanumControllerCommand that when executed will follow the provided
   * trajectory. The user should implement a velocity PID on the desired output wheel velocities.
   *
   * <p>Note: The controllers will *not* set the outputVolts to zero upon completion of the path -
   * this is left to the user, since it is not appropriate for paths with non-stationary end-states.
   *
   * <p>Note2: The rotation controller will calculate the rotation based on the final pose
   * in the trajectory, not the poses at each time step.
   *
   * @param trajectory                      The trajectory to follow.
   * @param pose                            A function that supplies the robot pose - use one of
   *                                        the odometry classes to provide this.
   * @param xController                     The Trajectory Tracker PID controller
   *                                        for the robot's x position.
   * @param yController                     The Trajectory Tracker PID controller
   *                                        for the robot's y position.
   * @param thetaController                 The Trajectory Tracker PID controller
   *                                        for angle for the robot.
   * @param requirements                    The subsystems to require.
   */

  @SuppressWarnings({"PMD.ExcessiveParameterList", "ParameterName"})
  public OmniTrackTrajectoryCommand(
                                Supplier<Trajectory> trajectory,
                                Supplier<Pose2d> pose,
                                PIDController xController,
                                PIDController yController,
                                ProfiledPIDController thetaController,
                                Subsystem... requirements) {

    m_trajectory = trajectory;
    m_pose = pose;
    m_feedforward = new SimpleMotorFeedforward(0, 0, 0);

    m_xController = xController;
    m_yController = yController;
    m_thetaController = thetaController;
    
    addRequirements(requirements);
  }

  @Override
  public void initialize() {
    var initialState = m_trajectory.get().sample(0);

    // Sample final pose to get robot rotation
    m_finalPose = m_trajectory.get().sample(m_trajectory.get().getTotalTimeSeconds()).poseMeters;

    var initialXVelocity = initialState.velocityMetersPerSecond
        * initialState.poseMeters.getRotation().getCos();
    var initialYVelocity = initialState.velocityMetersPerSecond
        * initialState.poseMeters.getRotation().getSin();


    m_prevSpeeds =  new ChassisSpeeds(initialXVelocity, initialYVelocity, 0.0);

    m_timer.reset();
    m_timer.start();
    m_prevTime = curTime = 0;

  }

  @Override
  @SuppressWarnings("LocalVariableName")
  public void execute() {

    curTime = m_timer.get();

    //Check dt... Maybe just use constant 0.02 ???????????????????????????????
    double dt = curTime - m_prevTime;

    var desiredState = m_trajectory.get().sample(curTime);
    var desiredPose = desiredState.poseMeters;

    var poseError = desiredPose.relativeTo(m_pose.get());

    double targetXVel = m_xController.calculate(
        m_pose.get().getTranslation().getX(),
        desiredPose.getTranslation().getX());

    double targetYVel = m_yController.calculate(
        m_pose.get().getTranslation().getY(),
        desiredPose.getTranslation().getY());

    // The robot will go to the desired rotation of the final pose in the trajectory,
    // not following the poses at individual states.
    double targetAngularVel = m_thetaController.calculate(
        m_pose.get().getRotation().getRadians(),
        m_finalPose.getRotation().getRadians());

    double vRef = desiredState.velocityMetersPerSecond;

    //targetXVel += vRef * poseError.getRotation().getCos();
    //targetYVel += vRef * poseError.getRotation().getSin();
    targetXVel = vRef * poseError.getRotation().getCos();
    targetYVel = vRef * poseError.getRotation().getSin();

    // System.out.printf("t:%5.2f, x:%5.2f, y:%5.2f, v:%5.2f\n", curTime, desiredPose.getTranslation().getX(), 
    // desiredPose.getTranslation().getY(), desiredState.velocityMetersPerSecond);

    m_prevSpeeds = new ChassisSpeeds(targetXVel, targetYVel, 0);//targetAngularVel);
    m_drive.setRobotSpeedXYW(targetXVel, targetYVel, 0);//targetAngularVel);
    m_prevTime = curTime;

  }

  @Override
  public void end(boolean interrupted) {
    m_timer.stop();
    //Currently set Robot to stop.
    //Should be done outide?????????????????????????????
    m_drive.setRobotSpeedXYW(0,0,0);
  }

  @Override
  public boolean isFinished() {
    return m_timer.hasElapsed(m_trajectory.get().getTotalTimeSeconds());
  }
}



/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.auto.CommandSchedule;
import frc.robot.subsystems.OmniDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_teleopCommand;
  private RobotContainer m_robotContainer;
  private Command m_startButton;
  private OmniDrive m_omnidrive;
  private Notifier m_follower;

  // private void generateEnabledDsPacket(byte[] data, short sendCount) {
  //   data[0] = (byte) (sendCount >> 8);
  //   data[1] = (byte) sendCount;
  //   data[2] = 0x01; // general data tag
  //   data[3] = 0x04; // teleop enabled
  //   data[4] = 0x10; // normal data request
  //   data[5] = 0x00; // red 1 station
  // }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.
    m_robotContainer = new RobotContainer();
    m_omnidrive = RobotContainer.m_omnidrive;

    //Run PID in different thread at higher rate
    if (Constants.PID_THREAD) 
    {
      m_follower = new Notifier(() -> { m_omnidrive.doPID(); });
      m_follower.startPeriodic(Constants.PID_DT);
    }
    // dsThread.setDaemon(true);
    // dsThread.start();

    // CameraServer.getInstance().startAutomaticCapture();
    // CvSink cvSink = CameraServer.getInstance().getVideo();
    // CvSource outputStream = CameraServer.getInstance().putVideo("camera", 640, 480);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    System.out.println("Hi");
    CommandScheduler.getInstance().enable();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }


  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    //RobotContainer.m_omnidrive.resetHeading();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

    m_teleopCommand = m_robotContainer.getTeleopCommand();
    RobotContainer.m_omnidrive.resetHeading();

    // schedule the autonomous command (example)
    if (m_teleopCommand != null) {
      m_teleopCommand.schedule();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // if(Globals.start){
    //   m_commandschedule.schedule();
    //   Globals.start = false;
    // }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  /**
   * added here to satisfy the watchdog
   */
  @Override
  public void simulationInit(){
  }
  
  /**
   * added here to satisfy the watchdog
   */ 
  @Override
  public void simulationPeriodic(){
  }
}

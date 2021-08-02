/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.TeleCmd;
import frc.robot.commands.auto.Test;
import frc.robot.commands.gamepad.OI;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Menu;
import frc.robot.subsystems.OmniDrive;
//import frc.robot.commands.auto.TestMove;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class RobotContainer {

  /**
   * Create the subsystems and gamepad objects
   */

  public final static Vision m_vision = new Vision();
  public final static OI m_oi = new OI();
  public final static Sensor m_sensor = new Sensor();
  public final static OmniDrive m_omnidrive = new OmniDrive();
  public final static Arm m_arm = new Arm();
  public final static TeleCmd m_teleCmd = new TeleCmd();// (m_arm, m_omnidrive);
  public final static Menu m_menu = new Menu();

  public RobotContainer() {
    // Create new instances


      //Set the default command for the hardware subsytem

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Test();
  }
  public Command getTeleopCommand() {
    // An ExampleCommand will run in autonomous
    return m_teleCmd;
  }
}

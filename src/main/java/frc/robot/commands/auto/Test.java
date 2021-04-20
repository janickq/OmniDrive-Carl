
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.OmniDrive;;

/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class Test extends ParallelCommandGroup
{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    public Test()
    {
       /* addCommands(
            m_drive.setRobotSpeedXYW(0, 0.5, 0),
            m_drive.setRobotSpeedXYW(0, 0, Math.PI/2)
            
            );
            */
    }
}
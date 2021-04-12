package frc.robot.commands.auto.MoveTypes;

import edu.wpi.first.wpilibj.Timer;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.commands.auto.Globals;
//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.commands.auto.MoveRobot;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */

public class MoveType1 extends CommandBase
{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private boolean endFlag = false;
    double speed;
    double distance;

    public MoveType1(double spd, double dist)
    {
        addRequirements(m_drive); 
        speed = spd;
        distance = dist;

    }
    @Override
    public void execute()
    {
        if(m_sensor.getSonicDistance1(true) <= distance)
        {
            Globals.distCount = 0;
            endFlag = true;
        }
        else
        {
            m_drive.setRobotSpeedXYW(0, speed, 0);
        }
        

    }
        /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    
    public void end(boolean interrupted)
    {
        m_drive.setRobotSpeedXYW(0,0,0);
        m_drive.setMotorSpeedAll(0);
    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        //Check if distance reached
        
            return endFlag;
    }

}
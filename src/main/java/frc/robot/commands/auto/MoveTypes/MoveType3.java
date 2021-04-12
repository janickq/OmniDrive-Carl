package frc.robot.commands.auto.MoveTypes;

import edu.wpi.first.wpilibj.Timer;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.commands.auto.Globals;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Sensor;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveType3 extends CommandBase
{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private boolean endFlag = false;
    double speed;
    double distance;
    double sensDist, tgtDist, curDist = 0;
    double dT = 0.02;
    int state = 0;

    public MoveType3(double spd, double dist, double tgtDist)
    {
        addRequirements(m_drive); 
        speed = spd;
        distance = tgtDist;
        sensDist = dist;
    }
    @Override
    public void execute()
    {
        if(state == 0)
        {
            m_drive.setRobotSpeedXYW(0, speed, 0);
        }
        if(m_sensor.getSonicDistance2(true) <= sensDist)
        {
            state = 1;
        }
        if(state == 1 && m_sensor.getSonicDistance2(true) >= sensDist)
        {
            state = 2;
        }
        if(state == 2)
        {
            new MoveRobot(1, -0.8, 0.0, 0.0, 0.5);
            /*m_drive.setRobotSpeedXYW(0, speed, 0);
            curDist += speed*dT;
            if (curDist>=distance)
            {
                endFlag = true;
            }
            */
            endFlag = true;
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
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class RobotTest extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private static final OmniDrive m_drive = RobotContainer.m_omnidrive;
    private static final Sensor m_sensor = RobotContainer.m_sensor;
    private double tgtDist, curDist = 0;
    private double curSpeed;
    private double dT = 0.02;
    private boolean CP1flag = false;

    /**
     * Constructor
     */
    public RobotTest(double dist)
    {
        addRequirements(m_drive); // Adds the subsystem to the command
        tgtDist = dist;
        curDist = 0;
        curSpeed = 0.1;
    }



    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {
        
    }
    
/*
    public void CP1()
    {
        int s1 =  m_sensor.getCobraRawValue(0);
        int s2 =  m_sensor.getCobraRawValue(1);
        int s3 =  m_sensor.getCobraRawValue(2);
        int s4 =  m_sensor.getCobraRawValue(3);
        int sTotal = s1 + s2 + s3 + s4;
        if ((sTotal < 8000) && (!CP1flag))
        {
            m_drive.setRobotSpeedXYW(0, 0.5, 0);
        }
        else if(sTotal > 8000)
        {
            Timer.delay(1);
            m_drive.setRobotSpeedXYW(0, 0, 0);
            CP1flag = true;
        }
    }
*/
    public void followLine()
    {
        double offset = m_sensor.offset();
        int s1 =  m_sensor.getCobraRawValue(0);
        int s2 =  m_sensor.getCobraRawValue(1);
        int s3 =  m_sensor.getCobraRawValue(2);
        int s4 =  m_sensor.getCobraRawValue(3);
        int sTotal = s1 + s2 + s3 + s4;
        if(sTotal > 8000)
        {
            m_drive.setRobotSpeedXYW(0, 0, 0);
        }

        else
        {
            m_drive.setRobotSpeedXYW(0, curSpeed, -offset/20);
        }
    }




    public void testrun1()
    {
        if(m_sensor.getSonicDistance1(true) >= 700)
        {
            m_drive.setRobotSpeedXYW(0, 0.5, 0);
        }

        else if(m_sensor.getSonicDistance1(true) < 700 && m_sensor.getSonicDistance2(true) <= 1600)
        {
            m_drive.setRobotSpeedXYW(0.5, 0, 0);
        }
        else
        {
            m_drive.setRobotSpeedXYW(0, 0, 0);
        }
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {
        m_drive.setRobotSpeedXYW(0, curSpeed, 0);
        curDist += curSpeed*dT;
        
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
        if (curDist>=tgtDist)
            return true;
        else
            return false;
    }

}
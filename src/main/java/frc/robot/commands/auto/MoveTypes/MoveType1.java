package frc.robot.commands.auto.MoveTypes;


//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;

//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.Globals;
//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */

public class MoveType1 extends CommandBase
{
    private static final OmniDrive m_drive = RobotContainer.m_omnidrive;
    private static final Sensor m_sensor = RobotContainer.m_sensor;
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
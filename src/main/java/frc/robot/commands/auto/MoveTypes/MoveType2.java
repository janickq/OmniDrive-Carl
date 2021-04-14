package frc.robot.commands.auto.MoveTypes;

//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.Globals;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Sensor;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveType2 extends CommandBase
{
    private static final OmniDrive m_drive = RobotContainer.m_omnidrive;
    private static final Sensor m_sensor = RobotContainer.m_sensor;
    private boolean endFlag = false;
    double speed;
    double distance;
    double sensDist;
    double tgtDist;
    double curDist = 0;
    double dT = 0.02;
    int state = 0;
    public MoveType2(double spd, double dist, double tgtDist)
    {
        addRequirements(m_drive); 
        speed = spd;
        distance = tgtDist;
        sensDist = dist;
    }

    public MoveType2() {
	}

	@Override
    public void execute()
    {
        if(state == 0)
        {
            m_drive.setRobotSpeedXYW(speed, 0, 0);
        }
        if(m_sensor.getSonicDistance1(true) >= sensDist)
        {
            state = 1;
        }
        if(state == 1)
        {
            new MoveRobot(0, 0.4, 0, 0, 0.5);

            endFlag = true;
        }
        Globals.distCount += speed*dT + 0.4;
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
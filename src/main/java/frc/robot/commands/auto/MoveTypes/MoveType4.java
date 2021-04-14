package frc.robot.commands.auto.MoveTypes;

import frc.robot.Globals;
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.MoveTypes.MoveType2;
import edu.wpi.first.wpilibj.Timer;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
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
public class MoveType4 extends CommandBase
{
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    //private final static MoveType2 M_TYPE2 = RobotContainer.M_TYPE2;
    private boolean endFlag = false;
    double speed;
    double distance;
    double sensDist, tgtDist, curDist = 0;
    double dT = 0.02;
    int state = 0;

    public MoveType4(double spd)
    {
        addRequirements(m_drive); 
        speed = spd;
    }
    @Override
    public void execute()
    {
        /*m_drive.setRobotSpeedXYW(-speed, 0, 0);
        curDist += speed*dT;
        if(curDist >= Globals.distCount)
        {
            endFlag = true;
        }
        */
        new MoveRobot(0, -Globals.distCount, 0.0, 0.0, 0.5);
        endFlag = true;

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
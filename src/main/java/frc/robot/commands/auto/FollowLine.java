package frc.robot.commands.auto;

import frc.robot.commands.auto.MoveRobot;
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;


public class FollowLine extends CommandBase {

    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private double speed;
    private boolean endFlag = false;


    public FollowLine(double spd)
    {
        //addRequirements(m_drive);
        //addRequirements(m_sensor);
        speed = spd;
    }

    @Override
    public void execute()
    {
        m_drive.setRobotSpeedXYW(0, speed, -(m_sensor.offset()/20));
        if(m_sensor.getSonicDistance1(true) < 300)
        {
            endFlag = true;
        }
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.setRobotSpeedXYW(0, 0, 0);
    }

    @Override
    public boolean isFinished()
    {
        return endFlag;
    }

}
package frc.robot.commands.auto;

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
    private final end_func f_ptr;
    interface end_func {
        public boolean endCondition();
    }

    public FollowLine(double spd, end_func f)
    {
        speed = spd;
        f_ptr = f;
        execute();
    }

    @Override
    public void execute()
    {
        m_drive.setRobotSpeedXYW(0, speed, -(m_sensor.offset()/20));
    }

    @Override
    public void end(boolean interrupted)
    {
        m_drive.setRobotSpeedXYW(0, 0, 0);
    }
    

    @Override
    public boolean isFinished()
    {
        return f_ptr.endCondition();
    }

}
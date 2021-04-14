package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.commands.gamepad.OI;
import frc.robot.subsystems.Menu;
import frc.robot.subsystems.OmniDrive;

public class TeleCmd extends CommandBase
{
    /**
     * Bring in Subsystem and Gamepad code
     */
    private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final OI m_oi = RobotContainer.m_oi;
    private final Menu m_menu = RobotContainer.m_menu;

    /**
     * Joystick inputs
     */

    double x=0;
    double y=0;
    double w=0;

    /**
     * Constructor
     */
    public TeleCmd()
    {

        addRequirements(m_omnidrive); //add the traning subsystem as a requirement 
    }

    /**
     * Code here will run once when the command is called for the first time
     */
    @Override
    public void initialize()
    {

    }


    /**
     * Code here will run continously every robot loop until the command is stopped
     */
    @Override
    public void execute()
    {
        /**
         * Get Joystick data
         */
        //Right stick for X-Y control
        //Left stick for W (rotational) control
        x = m_oi.getRightDriveX();
        y = -m_oi.getRightDriveY();//Down is positive. Need to negate
        w = -m_oi.getLeftDriveX(); //X-positive is CW. Need to negate
        
        
        m_omnidrive.setRobotSpeedXYW(x, y, w*Math.PI);

        //m_oi.buttonTest();

        //hardware.setMotorSpeed012(speed0, speed1, speed2);
        //m_hardware.setPIDSpeed012(speed0, speed1, speed2);
        //hardware.doPID();
    }



    /**
     * When the comamnd is stopped or interrupted this code is run
     * <p>
     * Good place to stop motors in case of an error
     */
    @Override
    public void end(boolean interrupted)
    {
       // m_omnidrive.setMotorSpeedAll(0);
    }

    /**
     * Check to see if command is finished
     */
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
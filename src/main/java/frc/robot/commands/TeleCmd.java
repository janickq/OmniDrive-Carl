package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
//import frc.robot.commands.auto.MoveArm;
import frc.robot.commands.gamepad.OI;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.OmniDrive;

public class TeleCmd extends CommandBase
{
    /**
     * Bring in Subsystem and Gamepad code
     */
    private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final OI m_oi = RobotContainer.m_oi;


    /**
     * Joystick inputs
     */

    double x=0;
    double y=0;
    double w=0;
    double z=0;

    /**
     * Constructor
     */
    public TeleCmd()//(Arm arm, OmniDrive drive)
    {
        //addRequirements(m_omnidrive);

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
        z = -m_oi.getLeftDriveY();
        
        m_omnidrive.setRobotSpeedXYW(x/2, y/2, w*Math.PI);

        Globals.debug3 = y;

    }



    /**
     * When the comamnd is stopped or interrupted this code is run
     * <p>
     * Good place to stop motors in case of an error
     */
    @Override
    public void end(boolean interrupted)
    {

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
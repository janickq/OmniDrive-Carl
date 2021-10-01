package frc.robot.commands;

import org.opencv.video.KalmanFilter;

import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.SlewRateLimiter;
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
    private double rate = 1.5;
    private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final OI m_oi = RobotContainer.m_oi;
    private SlewRateLimiter filter1 = new SlewRateLimiter(rate);
    private SlewRateLimiter filter2 = new SlewRateLimiter(rate);
    private SlewRateLimiter filter3 = new SlewRateLimiter(rate);
    private SlewRateLimiter filter4 = new SlewRateLimiter(rate);


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
        //m_arm = arm;
        //m_omnidrive = drive;
        //addRequirements(m_arm); //add the traning subsystem as a requirement 
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
        x = filter1.calculate(m_oi.getRightDriveX());
        y = filter2.calculate(-m_oi.getRightDriveY());//Down is positive. Need to negate
        w = filter3.calculate(-m_oi.getLeftDriveX()); //X-positive is CW. Need to negate
        z = filter4.calculate(-m_oi.getLeftDriveY());
        
        m_omnidrive.setRobotSpeedXYW(x/2, y/2, w*Math.PI);
        //m_arm.setServo1Angle((Globals.curAngle1+=y*2));
        //m_arm.setServo2Angle((Globals.curAngle2+=z*2));
       // m_arm.setServo3Angle((Globals.curAngle3+=w*2));
        Globals.debug3 = y;
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
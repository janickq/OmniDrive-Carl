package frc.robot.commands.auto.PickCommands;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
//RobotContainer import
import frc.robot.RobotContainer;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;
import frc.robot.Globals;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class RobotPick extends CommandBase
{
    //Grab the subsystem instance from RobotContainer
    private final static OmniDrive m_drive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private int m_profType;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private static TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    private int m_dir;
    public static double distMoved;
    private final double _startSpeed;
    private final double _endSpeed;
    private int item;
    private double dist;

    /**
     * Constructor
     */
    //This move the robot a certain distance following a trapezoidal speed profile.
    public RobotPick(int itemType, double startSpeed, double endSpeed, double maxSpeed)
    {
        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */
        item = itemType;
        _endSpeed = endSpeed;
        _startSpeed = startSpeed;
        m_constraints = new TrapezoidProfile.Constraints(maxSpeed, 0.6);
        m_setpoint = new TrapezoidProfile.State(0, _startSpeed);
        
        //Negative distance don't seem to work with the library function????
        //Easier to make distance positive and use m_dir to keep track of negative speed.
    
        


        addRequirements(m_drive); // Adds the subsystem to the command
        
    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize()
    {
        dist = getItem(item);
        m_dir = (dist>0)?1:-1;
        dist *= m_dir;      
        m_setpoint = new TrapezoidProfile.State(0, _startSpeed);
        m_goal = new TrapezoidProfile.State(dist, _endSpeed);
        m_endFlag = false;
    }

    public double getItem(int item){

        //gets item type to pick and returns item coordinate
        
        double [] itemCo = new double[4];

        itemCo[0] = m_vision.getChips(0);
        itemCo[1] = m_vision.getBall(0);
        itemCo[2] = m_vision.getKitkat(0);
        itemCo[3] = m_vision.getNissin(0);

        // adds offset of arm to camera
        return itemCo[item] + 0.145;
    }

    /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        return false;
    }
    /**
     * Called continously until command is ended
     */
    @Override
    public void execute()
    {

        //Create a new profile to calculate the next setpoint(speed) for the profile
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = profile.calculate(dT);
        m_drive.setRobotSpeedType(m_profType, m_setpoint.velocity*m_dir);

        if ((m_setpoint.position>=m_goal.position) || endCondition()) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            m_drive.setRobotSpeedType(m_profType, m_goal.velocity*m_dir);
            m_endFlag = true;
        }
    }

    /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {
        Globals.distCount += m_setpoint.position;
    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }

    public static double getDistMoved() {
        return m_setpoint.position;
    }

}
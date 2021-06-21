package frc.robot.commands.auto.PickCommands;


import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
//RobotContainer import

//Subsystem imports


public class GripperPick extends CommandBase{

    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    public static double distMoved;
    private final double _startSpeed;
    private final double _maxSpeed;
    private final double _endSpeed;
    private double dist;
    private int item;
    private int m_dir;
    private int dir;
    private double [] itemCo;


    /**
     * Constructor
     */
    // This move the robot a certain distance following a trapezoidal speed profile.
    public GripperPick(int itemType, int direction) {

        //direction, 0 = grip, 1 = letgo
        /*
        item 0 = chips
            1 = ball
            2 = kitkat
            3 = nissin
        */
        dir = direction;
        item = itemType;
        _startSpeed = 0;
        _maxSpeed = 3;
        _endSpeed = 0;
        m_constraints = new TrapezoidProfile.Constraints(_maxSpeed, 2);

        // Negative distance don't seem to work with the libr ary function????
        // Easier to make distance positive and use m_dir to keep track of negative
        // speed.
        // Adds the subsystem to the command
        itemCo = new double[4];

        itemCo[0] = 130 * (Math.PI/180);
        itemCo[1] = 130 * (Math.PI/180);
        itemCo[2] = 130 * (Math.PI/180);
        itemCo[3] = 130 * (Math.PI/180);
    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize() {
        
        dist = getItem(item, dir);
        m_dir = (dist>0)?1:-1;
        dist *= m_dir;      
        m_setpoint = new TrapezoidProfile.State(0, _startSpeed);
        m_goal = new TrapezoidProfile.State(dist, _endSpeed);
        m_endFlag = false;

    }

    public double getItem(int item, int direction){

        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */
        //get item type and returns gripper servo value

        if(direction == 1){

            return itemCo[item]*-1;

        }
        else{

            return itemCo[item];
        }
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
        Globals.curAngle3 += m_setpoint.velocity*m_dir;

        if ((m_setpoint.position>=m_goal.position) || endCondition()) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.
            
            m_endFlag = true;
        }



        

    }

    /**
     * Called when the command is told to end or is interrupted
     */
    @Override
    public void end(boolean interrupted)
    {
        
    }

    /**
     * Creates an isFinished condition if needed
     */
    @Override
    public boolean isFinished()
    {
        return m_endFlag;
    }

}
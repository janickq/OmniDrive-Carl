package frc.robot.commands.auto;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
//RobotContainer import
import frc.robot.RobotContainer;
import frc.robot.subsystems.Arm;

//Subsystem imports
import frc.robot.subsystems.OmniDrive;
import frc.robot.Globals;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor
 */
public class MoveArmXY extends CommandBase {
    // Grab the subsystem instance from RobotContainer
    private final static Arm m_arm = RobotContainer.m_arm;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private int m_profType;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    private int m_dir;
    public static double distMoved;
    private final double _startSpeed;
    private final double _maxSpeed;
    private final double _endSpeed;
    private double dist;
    private final double xgoal;
    private final double ygoal;
    private double[] startCo = new double[2];
    private double trajectoryAngle;
    private double time = 0;

    /**
     * Constructor
     */
    // This move the robot a certain distance following a trapezoidal speed profile.
    public MoveArmXY(double x, double y, double startSpeed, double endSpeed, double maxSpeed) {

        xgoal = x;
        ygoal = y;
        _startSpeed = startSpeed;
        _maxSpeed = maxSpeed;
        _endSpeed = endSpeed;
        m_constraints = new TrapezoidProfile.Constraints(_maxSpeed, 0.2);

        // Negative distance don't seem to work with the libr ary function????
        // Easier to make distance positive and use m_dir to keep track of negative
        // speed.

        addRequirements(m_arm); // Adds the subsystem to the command

    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize() {
        Globals.debug2 = 0;
        startCo = m_arm.getCoordinate(Globals.curAngle1, Globals.curAngle2);
        dist = m_arm.getDistance(startCo[0], xgoal, startCo[1], ygoal);
        trajectoryAngle = m_arm.getAngle(startCo[0], xgoal, startCo[1], ygoal);
        Globals.debug4 = dist;
        Globals.debug5 = startCo[0];
        Globals.debug6 = startCo[1];
        Globals.xCur = startCo[0];
        Globals.yCur = startCo[1];
        m_setpoint = new TrapezoidProfile.State(0, _startSpeed);
        m_goal = new TrapezoidProfile.State(dist, _endSpeed);
        m_endFlag = false;

    }
    /**
     * Condition to end speed profile
     */
    public boolean endCondition()
    {
        if(Math.sqrt(Math.pow(xgoal, 2)+Math.pow(ygoal, 2)) > (0.265+0.265)){
            return true;
        }
        else{
            return false;
        }
        
    }
    /**
     * Called continously until command is ended
     */
        
    @Override
    public void execute()
    {
        
        //Create a new profile to calculate the next setpoint(speed) for the profile
        double[] setAngle = new double[2];
        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = profile.calculate(dT);
        if(xgoal >= startCo[0]){
            Globals.xCur += m_setpoint.velocity*dT*Math.cos(trajectoryAngle);
        }
        else if(xgoal < startCo[0]){
            Globals.xCur -= m_setpoint.velocity*dT*Math.cos(trajectoryAngle);
        }
        if(ygoal >= startCo[1]){
            Globals.yCur += m_setpoint.velocity*dT*Math.sin(trajectoryAngle);
        }
        else if(ygoal < startCo[1]){
            Globals.yCur -= m_setpoint.velocity*dT*Math.sin(trajectoryAngle);
        }
        setAngle = m_arm.setArmAngle(Globals.xCur, Globals.yCur);
        Globals.curAngle1 = setAngle[0]*(180/Math.PI);
        Globals.curAngle2 = setAngle[1]*(180/Math.PI);



        if ((m_setpoint.position>=m_goal.position) || endCondition()) {
            //distance reached or end condition met. End the command
            //This class should be modified so that the profile can end on other conditions like
            //sensor value etc.

            m_endFlag = true;
        }


        // if (m_setpoint.velocity >= Globals.debug2){
        //     Globals.debug2 = m_setpoint.velocity;
        // }

        

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
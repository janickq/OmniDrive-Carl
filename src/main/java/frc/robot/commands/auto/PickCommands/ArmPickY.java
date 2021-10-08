package frc.robot.commands.auto.PickCommands;

import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
//WPI imports
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Globals;
//RobotContainer import
import frc.robot.RobotContainer;
//Subsystem imports
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

/**
 * SimpleDrive class
 * <p>
 * This class drives a motor
 */
public class ArmPickY extends CommandBase {
    // Grab the subsystem instance from RobotContainer
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Arm m_arm = RobotContainer.m_arm;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private double dT = 0.02;
    private boolean m_endFlag = false;
    private TrapezoidProfile.Constraints m_constraints;
    private TrapezoidProfile.State m_goal = new TrapezoidProfile.State();
    private TrapezoidProfile.State m_setpoint = new TrapezoidProfile.State();
    public static double distMoved;
    private final double _maxSpeed;
    private double dist;
    private double[] startCo = new double[2];
    private double trajectoryAngle;
    private double ygoal;
    private double xgoal;
    boolean manual;


    /**
     * Constructor
     */
    // This move the robot a certain distance following a trapezoidal speed profile.
    public ArmPickY(double maxSpeed) {

        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */

        _maxSpeed = maxSpeed;
        manual = false;
        m_constraints = new TrapezoidProfile.Constraints(_maxSpeed, 1);

        // Negative distance don't seem to work with the libr ary function????
        // Easier to make distance positive and use m_dir to keep track of negative
        // speed.

        //addRequirements(m_arm); // Adds the subsystem to the command

    }
    public ArmPickY(double maxSpeed, double y) {

        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */
        
        ygoal = y;
        manual = true;
        _maxSpeed = maxSpeed;

        m_constraints = new TrapezoidProfile.Constraints(_maxSpeed, 1);

        // Negative distance don't seem to work with the libr ary function????
        // Easier to make distance positive and use m_dir to keep track of negative
        // speed.

        //addRequirements(m_arm); // Adds the subsystem to the command

    }

    /**
     * Runs before execute
     */
    @Override
    public void initialize() {

        // gets parameters for speed profile
        if(!manual)
            ygoal = getItemY(Globals.curItem);
    

        startCo = m_arm.getCoordinate(Globals.curAngle1, Globals.curAngle2);
        dist = m_arm.getDistance(startCo[0], startCo[0], startCo[1], ygoal);
        trajectoryAngle = m_arm.getAngle(startCo[0], startCo[0], startCo[1], ygoal);

        Globals.xArm = startCo[0];
        Globals.yArm = startCo[1];

        m_setpoint = new TrapezoidProfile.State(0, 0);
        m_goal = new TrapezoidProfile.State(dist, 0);

        //checks if target coordinates are within boundaries
        // if(Math.sqrt(Math.pow(startCo[0], 2)+Math.pow(ygoal, 2)) > (Constants.ARM1 + Constants.ARM2)){
        //     m_endFlag = true;
        // }
        // else{
            m_endFlag = false;
        
        
        //debug stuff

    }

    
    public double getItemIR() {
        return (m_sensor.getIRDistance2() / 100) - 0.5;
    }

    public double getItemY(int item){

        //gets item type to pick and returns item coordinate
        double [] itemCo = new double[4];
        /*
        item 0 = chips
             1 = ball
             2 = kitkat
             3 = nissin
        */
        itemCo[0] = Math.max((Globals.yArm - (m_sensor.getIRDistance2() / 100)-0.0), -0.025);
        itemCo[1] = Math.max((Globals.yArm - (m_sensor.getIRDistance2() / 100) + 0.07), -0.05);
        itemCo[2] = Math.max((Globals.yArm - (m_sensor.getIRDistance2() / 100)+0.07),-0.075);
        itemCo[3] = Math.max((Globals.yArm - (m_sensor.getIRDistance2() / 100) + 0.02), -0.05);

        // add offset of arm to camera
        return itemCo[item];
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
        
        //Create a new profile to calculate the next setpoint for the profile
        double[] setAngle = new double[2];

        var profile = new TrapezoidProfile(m_constraints, m_goal, m_setpoint);
        m_setpoint = profile.calculate(dT);

    
        if(xgoal >= startCo[0]){
            Globals.xArm += m_setpoint.velocity*dT*Math.cos(trajectoryAngle);
        }
        else if(xgoal < startCo[0]){
            Globals.xArm -= m_setpoint.velocity*dT*Math.cos(trajectoryAngle);
        }
        if(ygoal >= startCo[1]){
            Globals.yArm += m_setpoint.velocity*dT*Math.sin(trajectoryAngle);
        }
        else if(ygoal < startCo[1]){
            Globals.yArm -= m_setpoint.velocity*dT*Math.sin(trajectoryAngle);
        }

        //gets and assigns individual angles based on current coordinates
        setAngle = m_arm.setArmAngle(Globals.xArm, Globals.yArm);

        Globals.curAngle1 = setAngle[0]*(180/Math.PI);
        Globals.curAngle2 = setAngle[1]*(180/Math.PI);



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
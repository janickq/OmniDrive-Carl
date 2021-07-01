package frc.robot.subsystems;

import com.studica.frc.Servo;
//import edu.wpi.first.wpilibj.Servo;
import com.studica.frc.ServoContinuous;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;


public class Arm extends SubsystemBase
{

    private final Servo servo1;
    private final Servo servo2;
    private final Servo servo3;
    private final ServoContinuous servoC;
    private double[] startCo = new double[2];
    private double a1 = Constants.ARM1; //arm length
    private double a2 = Constants.ARM2;
    private double[] angleInit = new double[2];


    private final ShuffleboardTab tab = Shuffleboard.getTab("Arm");
    private final NetworkTableEntry D_SetPoint = tab.add("SetPoint", 0).getEntry();
    private final NetworkTableEntry D_CurrentAngle = tab.add("CurrentAngle", 0).getEntry();
    private final NetworkTableEntry D_Goal = tab.add("Goal", 0).getEntry();
    private final NetworkTableEntry D_debug1 = tab.add("curAngle1", 0).getEntry();
    private final NetworkTableEntry D_debug2 = tab.add("curAngle2", 0).getEntry();
    private final NetworkTableEntry D_debug3 = tab.add("velocity", 0).getEntry();
    private final NetworkTableEntry D_xCur = tab.add("xCur", 0).getEntry();
    private final NetworkTableEntry D_yCur = tab.add("yCur", 0).getEntry();
    private final NetworkTableEntry D_dist = tab.add("dist", 0).getEntry();
    private final NetworkTableEntry D_startCo1 = tab.add("startCo1", 0).getEntry();
    private final NetworkTableEntry D_startCo2 = tab.add("startCo2", 0).getEntry();

    public Arm(){
        servo2 = new Servo(Constants.SERVO2);
        servo1 = new Servo(Constants.SERVO1);
        servo3 = new Servo(Constants.SERVO3);
        servoC = new ServoContinuous(Constants.SERVO_C);
        angleInit = setArmAngle(Constants.ARM2 - 0.05 , Constants.ARM1 + 0.15 );
        Globals.curAngle1 = angleInit[0]*(180/Math.PI);
        Globals.curAngle2 = angleInit[1]*(180/Math.PI);
        Globals.curAngle3 = 0.0;
        // Globals.servoAngle1 = 90.0;
        // Globals.servoAngle2 = 90.0;
        // setServo1Angle(Globals.curAngle1);
        // setServo2Angle(Globals.curAngle2);
        // setServo3Angle(Globals.curAngle3);

        startCo = getCoordinate(Globals.curAngle1, Globals.curAngle2);
        Globals.xArm = startCo[0];
        Globals.yArm = startCo[1];
        
    }

    public double getServoAngle(int servonum) {

        double[] servoAngle = new double[2];

        servoAngle[0] = (Globals.curAngle1 - 36) * 4;
        servoAngle[1] = (Globals.curAngle2 - 12) * 2;

        return servoAngle[servonum];

    }

        /**
     * Sets the servo angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServo1Angle(final double degrees) {
        servo1.setAngle(degrees); // + 90 + 60 );
    }

    public void setServo2Angle(final double degrees) {
        servo2.setAngle(degrees); //  +7  + 60 );
    }

    public void setServo3Angle(final double degrees) {
        servo3.setAngle(degrees);
    }

    /*
    https://www.youtube.com/watch?v=IKOGwoJ2HLk&t=138s
    Takes x and y coordinate in 2d space and returns 2 angle values in degrees using reverse kinematics
    For Q2 (h(0)), x axis, ccw +ve
    For Q1 (h(1)), 
    */
    public double[] setArmAngle(double x, double y){
        double[] h = new double[2];
        h[1] = (Math.acos((Math.pow(x, 2) + Math.pow(y, 2) - Math.pow(a1, 2) - Math.pow(a2, 2))/(2*a1*a2))); 
        h[0] = (Math.atan(y/x) + Math.atan((a2*Math.sin(h[1])/(a1 + a2*Math.cos(h[1])))));
        return h;
    }

    /*
        takes the current joint angles and returns an xy coordinate value
    */
    public double[] getCoordinate(double a, double b){
        double[] xy = new double [2];
        double angle1 = a*(Math.PI/180);
        double angle2 = b*(Math.PI/180);
        // xy[0] = (a1*Math.cos(angle1 - angle2) + a2*Math.cos(angle1));
        // xy[1] = (a1*Math.sin(angle1 - angle2) + a2*Math.sin(angle2));
        xy[0] = (a2*Math.cos(angle1 - angle2) + a1*Math.cos(angle1));
        xy[1] = (a2*Math.sin(angle1 - angle2) + a1*Math.sin(angle1));
        return xy;
    }

    //takes the current and goal xy coordinates and returns a distance value in meters

    public double getDistance(double x1, double x2, double y1, double y2){

        return Math.sqrt((Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2)));

    }
    /*
    gets angle from start and end coordinates
    */
    public double getAngle(double x1, double x2, double y1, double y2){

        return Math.abs(Math.atan((y2-y1)/(x2-x1)));
    }



        /**
     * Sets the servo speed
     * <p>
     * 
     * @param speed sets the speed of the servo in continous mode, range -1 to 1
     */
    public void setServoSpeed(final double speed) {  
        servoC.set(speed);
    }

    @Override
    public void periodic(){
        //double[] G = setTrajectory(0.3, 0.25);
        //D_CurrentAngle.setDouble(Globals.curAngle1);
        //D_SetPoint.setDouble(Globals.debug1);
        //D_Goal.setDouble(Globals.debug3);
        D_debug1.setDouble(Globals.curAngle1);
        D_debug2.setDouble(Globals.curAngle2);
        D_debug3.setDouble(Globals.debug2);
        D_xCur.setDouble(Globals.xArm);
        D_yCur.setDouble(Globals.yArm);
        D_dist.setDouble(Globals.debug5);
        D_startCo1.setDouble(Globals.servoAngle1);
        D_startCo2.setDouble(Globals.servoAngle2);       
        setServo1Angle(getServoAngle(0));
        setServo2Angle(getServoAngle(1));
        setServo3Angle(Globals.curAngle3);
        
    }

}
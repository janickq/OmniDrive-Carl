package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Globals;
import com.studica.frc.Servo;
//import edu.wpi.first.wpilibj.Servo;
import com.studica.frc.ServoContinuous;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;


public class Arm extends SubsystemBase
{

    private final Servo servo1;
    private final Servo servo2;
    private final Servo servo3;
    private final ServoContinuous servoC;
    private double[] startCo = new double[2];


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

        Globals.curAngle1 = 90.0;
        Globals.curAngle2 = 90.0;
        Globals.curAngle3 = 90.0;

        setServo1Angle(Globals.curAngle1);
        setServo2Angle(Globals.curAngle2);
        setServo3Angle(Globals.curAngle3);

        startCo = getCoordinate(Globals.curAngle1, Globals.curAngle2);
        Globals.xCur = startCo[0];
        Globals.yCur = startCo[1];
        
    }

        /**
     * Sets the servo angle
     * <p>
     * 
     * @param degrees degree to set the servo to, range 0° - 300°
     */
    public void setServo1Angle(final double degrees) {
        servo1.setAngle(degrees + 2);
    }

    public void setServo2Angle(final double degrees) {
        servo2.setAngle(degrees - 5);
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
        h[1] = (Math.acos((Math.pow(x, 2) + Math.pow(y, 2) - 0.14045)/(0.14045))); 
        h[0] = (Math.atan(y/x) + Math.atan((0.265*Math.sin(h[1])/(0.265 + 0.265*Math.cos(h[1])))));
        return h;
    }

    /*
        takes the current joint angles and returns an xy coordinate value
    */
    public double[] getCoordinate(double a, double b){
        double[] xy = new double [2];
        double a1 = a*(Math.PI/180);
        double a2 = b*(Math.PI/180);
        xy[0] = (0.265*Math.cos(a1 - a2) + 0.265*Math.cos(a1));
        xy[1] = (0.265*Math.sin(a1 - a2) + 0.265*Math.sin(a1));
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
        D_xCur.setDouble(Globals.xCur);
        D_yCur.setDouble(Globals.yCur);
        D_dist.setDouble(Globals.debug4);
        D_startCo1.setDouble(Globals.debug5);
        D_startCo2.setDouble(Globals.debug6);       
        setServo1Angle(Globals.curAngle1);
        setServo2Angle(Globals.curAngle2);
        
    }

}
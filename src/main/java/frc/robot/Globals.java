package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;

public class Globals {
    public static double distCount = 0;
    public static int state;
    public static boolean endFlag = true;
    public static int cmdState = 0;
    public static boolean checkFlag = false;
    public static int menuItem;

    public static int curItem = 5;
    public static boolean checkItem = false;

    public static double curAngle1;
    public static double curAngle2;
    public static double curAngle3;

    public static double servoAngle1;
    public static double servoAngle2;

    public static double xTgt = 0.4;
    public static double yTgt = -0.2;
    public static double zTgt = 0.2;

    public static double xCur;
    public static double yCur;
    public static double zCur;

    public static double xArm;
    public static double yArm;
    
    public static boolean debug1;
    public static double debug2;
    public static double debug3;
    public static boolean debug4;
    public static double debug5;
    public static double debug6;
    public static double debug7;
    public static double debug8 = 0;
    public static double debug9 = 0;
    public static String debug10 = "0";
    public static String debug11 = "0";
    public static String debug12 = "0";

    public static double kitkatx;
    public static double kitkaty;

    public static double chipsx;
    public static double chipsy;

    public static double nissinx;
    public static double nissiny;

    public static double ballx;
    public static double bally;
    

    public static boolean start = false;

    public static double compassHeading;
    public static double referenceHeading;
    public static double headingError = 0;
    
    public static boolean poserunFlag = false;
    static public boolean runFlag;
    

    public static Pose2d referencePose;
    public static Pose2d curPose;


}
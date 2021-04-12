/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
    /**
     * Motor Constants
     */
    public static final int TITAN_ID        = 42; 
    public static final int MOTOR_NUM       = 3;
    public static final int SERVO1          = 8;
    public static final int SERVO2          = 9;
    //public static final int SERVO_C         = 12;

    /**
     * Sensors
     */
    public static final int SHARP            = 0;
    public static final int SONIC_TRIGG1     = 7;
    public static final int SONIC_ECHO1      = 6;
    public static final int SONIC_TRIGG2     = 9;
    public static final int SONIC_ECHO2      = 8;

    //Wheels
    public static final double KWHEELDIAMETER = 0.1;  //wheel diameter
    public static final double KENCODERCNTPR = 1440;  //Count per output shaft rev
    public static final double KENCODERDISTPERPULSE = (KWHEELDIAMETER*Math.PI)/KENCODERCNTPR;

    //PIDs
    public static final int PID_NUM = 3;
}

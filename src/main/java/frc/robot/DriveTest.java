package frc.robot;

import java.util.ArrayList;
import java.util.List;

import com.kauailabs.navx.IMUProtocol.GyroUpdate;

//Java imports

//Vendor imports
import com.kauailabs.navx.frc.AHRS;
import com.studica.frc.TitanQuad;
import com.studica.frc.TitanQuadEncoder;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalOutput;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.subsystems.OmniDrive;

public class DriveTest extends SubsystemBase {

    // private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;


    public static final double kMaxSpeed = 3.0; // meters per second
    public static final double kMaxAngularSpeed = 2 * Math.PI; // one rotation per second

    private static final double kTrackWidth = 0.195 * 2; // meters
    private static final double kWheelRadius = Constants.KWHEELDIAMETER / 2; // meters

    private final DifferentialDriveKinematics m_kinematics;
    private final DifferentialDriveOdometry m_odometry;
    private final DifferentialDrive m_drive;

    // Motors and encoders
    private final TitanQuad leftmotor, rightmotor;

    private final TitanQuadEncoder leftencoder, rightencoder;
    // vmx private final Encoder[] encoders; //VMX encoder


    // PID stuff
    private final PIDController m_leftPIDController;
    private final PIDController m_rightPIDController;
    // private PIDController[] pidControllers;
    private double[] pidInputs;
    private double[] pidOutputs;
    private double[] encoderDists;
    private double[] encoderSpeeds;
    private double[] wheelSpeeds;
    private double curHeading, targetHeading;
    private double[] motorOuts;
    private double duration;
    private double sampleduration;

    // For testing. These should be in another subsystem
    private double pid_dT = Constants.PID_DT;
    private final SimpleMotorFeedforward m_feedforward = new SimpleMotorFeedforward(1, 3);

    // Sensors

    private final AHRS gyro;

    // Subsystem for omnidrive
    public DriveTest() {

        // Omni drive motors

        leftmotor = new TitanQuad(Constants.TITAN_ID, 2);
        rightmotor = new TitanQuad(Constants.TITAN_ID, 0);
        leftmotor.setInverted(true); // Positive is CW. Need to reverse
        rightmotor.setInverted(true);

        // vmx encoders = new Encoder[Constants.MOTOR_NUM];
        encoderDists = new double[2];
        encoderSpeeds = new double[2];
        wheelSpeeds = new double[2];
        motorOuts = new double[2];

        leftencoder = new TitanQuadEncoder(leftmotor, 2, Constants.KENCODERDISTPERPULSE * 0.866);
        rightencoder = new TitanQuadEncoder(rightmotor, 0, Constants.KENCODERDISTPERPULSE * 0.866);
        leftencoder.reset();
        rightencoder.reset();

        // Speed control
        m_leftPIDController = new PIDController(1.3, 11.0, 0.04, pid_dT); // x
        m_rightPIDController = new PIDController(1.3, 11.0, 0.04, pid_dT); // y 2.0,32.0,0.02

        // Inputs and Outputs for wheel controller
        pidInputs = new double[Constants.PID_NUM];
        pidOutputs = new double[Constants.PID_NUM];

        // gyro for rotational heading control
        gyro = new AHRS(SPI.Port.kMXP);
        gyro.zeroYaw();

        m_kinematics = new DifferentialDriveKinematics(kTrackWidth);
        m_odometry = new DifferentialDriveOdometry(getRotation2d());
        m_drive = new DifferentialDrive(leftmotor, rightmotor);
        // left encoder = 2 right encoder = 0

    }

    public Rotation2d getRotation2d() {
        return Rotation2d.fromDegrees(gyro.getRawGyroZ());
    }

    public void updateOdometry() {
        m_odometry.update(Rotation2d.fromDegrees(getYaw()), leftencoder.getEncoderDistance(),
                rightencoder.getEncoderDistance());

    }

    public DifferentialDriveKinematics getKinematics() {
        return m_kinematics;
    }
    public Pose2d getPose() {
        return m_odometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        leftencoder.reset();
        rightencoder.reset();
        m_odometry.resetPosition(pose, getRotation2d());
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(leftencoder.getSpeed(), rightencoder.getSpeed());
    }

    public double getYaw() {
        // return gyro.getYaw();
        return gyro.getRawGyroZ();
    }

    /**
     * Resets the yaw angle back to zero
     */
    public void resetGyro() {
        gyro.zeroYaw();
    }

    public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
        final double leftFeedforward = m_feedforward.calculate(speeds.leftMetersPerSecond);
        final double rightFeedforward = m_feedforward.calculate(speeds.rightMetersPerSecond);

        final double leftOutput = m_leftPIDController.calculate(leftencoder.getSpeed(), speeds.leftMetersPerSecond);
        final double rightOutput = m_rightPIDController.calculate(rightencoder.getSpeed(), speeds.rightMetersPerSecond);
        leftmotor.set(leftOutput + leftFeedforward);
        rightmotor.set(rightOutput + rightFeedforward);
    }

    public void setVoltage(double leftVolts, double rightVolts){
        leftmotor.setVoltage(leftVolts);
        rightmotor.setVoltage(rightVolts);
        m_drive.feed();
    }
    public void drive(double dT) {

        duration = duration+dT;
    
        double totalduration = generateTrajectory().getTotalTimeSeconds();

        if(duration<totalduration)
          sampleduration = duration;
    
        Trajectory.State goal = generateTrajectory().sample(sampleduration);
    
        var controller = new HolonomicDriveController(new PIDController(1, 0, 0), new PIDController(1, 0, 0),
                        new ProfiledPIDController(1, 0, 0, new TrapezoidProfile.Constraints(3.14, 3.14)));
                        
    
    
        ChassisSpeeds adjustedSpeeds = controller.calculate(getPose()
                                    , goal, Rotation2d.fromDegrees(70.0));

        
        String str = String.valueOf(adjustedSpeeds);
        Double robotSpeeds = Double.valueOf(str);


        SmartDashboard.putString("adjustedSpeeds str", str);
        SmartDashboard.putNumber("adjustedSpeeds dbl", robotSpeeds);
        
    
        
    }

    
    public Trajectory generateTrajectory() {
    
        // 2018 cross scale auto waypoints.
        var start = new Pose2d(0, 0,
            Rotation2d.fromDegrees(0));
        var end = new Pose2d(3, 3,
            Rotation2d.fromDegrees(-160));
    
        var interiorWaypoints = new ArrayList<Translation2d>();
        interiorWaypoints.add(new Translation2d(2, 0));
        interiorWaypoints.add(new Translation2d(0, 2));
    
        TrajectoryConfig config = new TrajectoryConfig(0.5, 0.5);
        config.setReversed(false);
    
        var trajectory = TrajectoryGenerator.generateTrajectory(
            start,
            interiorWaypoints,
            end,
            config);
        // System.out.println(Arrays.toString(trajectory.getStates().toArray()));
        var trajectorylist = trajectory.getStates().toArray();
        // String[] array = Arrays.copyOf(trajectorylist, trajectorylist.length, String[].class);
    
        return trajectory;
        
    
    
        // return trajectory.getStates().toArray();
      }

    
    // public Command getRamseteCommand(){

    //     RamseteCommand command = new RamseteCommand(getTrajectory(), getPose(), new RamseteController(), m_feedforward, m_kinematics, getWheelSpeeds(), m_leftPIDController, m_rightPIDController, setVoltage(leftVolts, rightVolts);, requirements)
    //     new RamseteCommand(getTrajectory(), getPose(), new RamseteController(), m_kinematics, setSpeeds(speeds));
    // }

    @Override
    public void periodic()
    {
        drive(0.02);
  
    }
}
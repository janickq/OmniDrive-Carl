package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.Astar.Layout;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.subsystems.Comms;
import frc.robot.utils.CommandID;



/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup

{   
    private final static CommandID cmdID = RobotContainer.m_ID;

      @Override
    public void initialize() {
        
        cmdID.updateName(this.getClass().toString());
        SmartDashboard.putString("CurrentCommand", this.getName().toString());
        super.initialize();
    }

	public AutoMainCmd()
    {

        //Go from start to work oder to dispensary to room-0 medCube.
        super (
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.workOrderPos)),
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.dispensaryPos)),
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.medCubeStandPos[0])),
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.medCubeStandPos[1])),
            new MovetoB(Layout.Convert_mm_Pose2d(Layout.startPos))

            );

        // super(
        //     new MoveRobot(2, -Math.PI/4, 0, 0, Math.PI),  
        //     new MoveRobot(2, Math.PI/4, 0, 0, Math.PI),
        //     new LoopCmd(new RotateTest()),
        //     new MoveRobot(2, Math.PI/4, 0, 0, Math.PI)
        //     );
    }
    @Override
    public void end(boolean interrupted) {
        cmdID.incrementID();
    }
}

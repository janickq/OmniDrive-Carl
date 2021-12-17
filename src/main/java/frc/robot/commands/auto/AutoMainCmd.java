package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Astar.Layout;
// import the commands
import frc.robot.commands.auto.MoveRobot;
import frc.robot.commands.auto.RotateTest;


/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class AutoMainCmd extends SequentialCommandGroup
{   

      @Override
    public void initialize() {

                
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
}

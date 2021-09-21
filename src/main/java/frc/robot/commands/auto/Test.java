package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class Test extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;

    

    public Test() {

        super(
                new InstantCommand(m_vision::boxLook),
                new MovePose("Pick"),
                new AlignRight(),
                new WaitCommand(2),
                new InstantCommand(m_omnidrive::setreferencePose),
                new InstantCommand(m_omnidrive::setreferenceHeading),
                new MoveRobot(2, Math.PI/2, 0, 0, 1),

                new WaitCommand(3),
                new MapPose(),
                new WaitCommand(2),
                new MovePose("Pick"),
                new AlignRight(),
                // new MoveRobot(2, -Math.PI/2, 0, 0, 1),
                new MoveTest2()


        );
    }



}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.DropPoint;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Test extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    private final static DropPoint m_drop = new DropPoint();

    

    public Test() {

        super(

            // new MainSequence()
                
                new InstantCommand(m_vision::itemLook),
                new InstantCommand(m_vision::getItem),
                new Pick()
                // new InstantCommand(() -> Globals.curPose = m_points.Map),
                // new InstantCommand(m_vision::boxLook),

        //         new MovePose("Pick"),
        //         new AlignPick(),
        //         new WaitCommand(2),
        //         new InstantCommand(m_omnidrive::setreferencePose),
        //         new InstantCommand(m_omnidrive::setreferenceHeading),
        //         new MoveRobot(2, Math.PI/2, 0, 0, 1),
        //         new WaitCommand(3),
        //         new InstantCommand(m_drop::getBoxes),
                // new InstantCommand(m_drop::getBin),
        //         new WaitCommand(2),
        //         new MoveRobot(1, -0.7, 0, 0, 0.5),
        //         new WaitCommand(3),
                // new InstantCommand(m_drop::getBin),
                // new InstantCommand(m_drop::getBoxes),
                // new InstantCommand(m_drop::generatePair),
                // new InstantCommand(m_drop::getDropPose),
                // new InstantCommand(m_vision::getBin)

        );
    }



}
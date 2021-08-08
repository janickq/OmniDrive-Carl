package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

public class Test extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

    

    public Test() {

        super(
                new MovePose("Pick"),
                new Start(),
                new WaitCommand(1),
                new InstantCommand(m_omnidrive::setreferencePose),
                new InstantCommand(m_omnidrive::setreferenceHeading),
                new MoveTest2(),
                new End()
        );
    }



}
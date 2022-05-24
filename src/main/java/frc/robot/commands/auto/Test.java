package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;
import frc.robot.utils.CommandID;

public class Test extends AutoCommand{

    private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final static Vision m_vision = RobotContainer.m_vision;
    private final static Points m_points = RobotContainer.m_points;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static CommandID m_cmd = RobotContainer.m_ID;
    

    public Test() {

        super(

            // new InstantCommand(m_cmd::incrementID),
            // new InstantCommand(m_cmd::updateID),
            new WaitCommand(1)
            

        );
    }

    // @Override
    // public void end(boolean interrupted) {
    //     m_cmd.incrementID();
    //     m_cmd.updateID();
    // }


}
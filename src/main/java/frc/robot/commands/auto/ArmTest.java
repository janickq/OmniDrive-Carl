package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;

public class ArmTest extends AutoCommand{

    private static double spd1 = 0.5;
    private static double spd2 = 0.1;
    private final static Sensor m_sensor = RobotContainer.m_sensor;

    public ArmTest() {

        super(

                new WaitCommand(3), 
                new MoveRobot(0, 0.5, 0, 0, spd1),
                new MoveRobotSense(1, 20, 0, spd1, spd1, () -> m_sensor.getIRDistance1() < 50),
                new MoveRobot(1, 0.25, spd1, 0, spd1), new MoveRobot(2, -Math.PI / 2, 0, 0, 1),
                new MoveRobotSense(1, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 7000),
                new MoveRobot(1, -0.2, 0, 0, spd2),
                new MoveRobotSense(0, 5, 0, 0, spd2, () -> m_sensor.getCobraTotal() > 6000),
                new MoveRobot(1, 0.2, 0, 0, spd2),
                new WaitCommand(2), 
                new MoveTest()

        );
    }



}
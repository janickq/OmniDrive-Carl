package frc.robot.commands.auto;

import frc.robot.commands.auto.MoveTypes.MoveType1;
import frc.robot.commands.auto.MoveTypes.MoveType2A;
import frc.robot.commands.auto.MoveTypes.MoveType3A;
import frc.robot.commands.auto.MoveTypes.MoveType4;
import frc.robot.commands.auto.MoveTypes.MoveType4A;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.RobotContainer;
import frc.robot.Globals;
public class SequentialMove extends AutoCommand
{
    static double spd = 0.3;

    public SequentialMove() {

        super(new MoveRobotSense(1, 2, 0.0, 0.0, spd, () -> RobotContainer.m_sensor.getSonicDistance1(true) < 300), // forward
              //new MoveRobotSense(0, 2, 0.0, 0.0, 0.5, ()->RobotContainer.m_sensor.getSonicDistance1(true)>400),
              //new MoveRobotSense(0, 0.5, 0.0, 0.0, 0.5, ()->RobotContainer.m_sensor.getSonicDistance1(true)<400),
              new MoveType2A(0, 2, 0.0, spd, spd, 500),
              new MoveRobotSense(0, 0.3, spd, 0.0, spd, ()->RobotContainer.m_sensor.getIRDistance()<70),
              new MoveRobotSense(1, 1, 0.0, spd, spd, ()->RobotContainer.m_sensor.getIRDistance()<70),
              new MoveRobotSense(1, 1, spd, spd, spd, ()->RobotContainer.m_sensor.getIRDistance()>70),
              new MoveRobotSense(1, 0.5, spd, 0.0, spd, ()-> 2 < 1),
              new MoveRobotSense1(0, -1, 0.0, 0.0, spd),
              new FollowLine(0.3)
              //new MoveRobotSense(1, 0.1, 0.3, 0.0, spd, ()->RobotContainer.m_sensor.getSonicDistance1(true)<300)
              
             );
             Globals.distCount = 0;
    }
}


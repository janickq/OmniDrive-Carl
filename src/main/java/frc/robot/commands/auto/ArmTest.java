package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;

public class ArmTest extends AutoCommand {
    private static double spd = 1;
    public ArmTest(){
       
        super(
              new MoveArmXY(0.4, -0.1, 0.0, 0.0, spd),
              new ParallelCommandGroup(new MoveArmXY(0.2, -0.1, 0.0, 0.0, spd),
              new MoveRobot(1, 0.2,  0.0, 0.0, spd)),
              new ParallelCommandGroup(new MoveArmXY(0.4, -0.1, 0.0, 0.0, spd),
              new MoveRobot(1, -0.2,  0.0, 0.0, spd))
            //   new MoveArmXY(0.1, 0.3, 0.0, 0.0, spd),
            //   //new WaitCommand(2),
            //   new MoveArmXY(0.1, 0.1, 0.0, 0.0, spd),
            //   //new WaitCommand(2),
            //   new MoveArmXY(0.3, 0.1, 0.0, 0.0, spd),
            //   //new WaitCommand(2),
            //   new MoveArmXY(0.3, 0.3, 0.0, 0.0, spd),
            //   //new WaitCommand(2),
            //   new MoveArmXY(0.1, 0.3, 0.0, 0.0, spd)


        );
    }

}
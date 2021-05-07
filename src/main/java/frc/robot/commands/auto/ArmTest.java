package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;

public class ArmTest extends AutoCommand {
    private static double spd = 0.5;
    public ArmTest(){
       
        super(
              new ParallelCommandGroup(new MoveArmXY(Globals.xTgt, Globals.yTgt, 0.0, 0.0, 0.5),
              new MoveRobot(0, Globals.zTgt,  0.0, 0.0, 0.5)),
              new ParallelCommandGroup(new MoveArmXY(0.255, 0.255, 0.0, 0.0, 0.5),
              new MoveRobot(0, -Globals.zTgt,  0.0, 0.0, 0.5))
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
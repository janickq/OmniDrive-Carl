package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ArmTest extends AutoCommand {
    private static double spd = 0.5;
    public ArmTest(){
       
        super(
              
              new MoveArmXY(0.1, 0.3, 0.0, 0.0, spd),
              //new WaitCommand(2),
              new MoveArmXY(0.1, 0.1, 0.0, 0.0, spd),
              //new WaitCommand(2),
              new MoveArmXY(0.3, 0.1, 0.0, 0.0, spd),
              //new WaitCommand(2),
              new MoveArmXY(0.3, 0.3, 0.0, 0.0, spd),
              //new WaitCommand(2),
              new MoveArmXY(0.1, 0.3, 0.0, 0.0, spd)


        );
    }

}
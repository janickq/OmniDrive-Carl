package frc.robot.commands.auto;

public class ArmTest extends AutoCommand {

    public ArmTest(){

        super(
              
              new MoveArmXY(0.35, 0.35, 0.0, 0.0, 0.3),
              new MoveArmXY(0.35, 0.01 , 0.0, 0.0, 0.3)


        );
    }

}
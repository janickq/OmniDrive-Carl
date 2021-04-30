package frc.robot.commands.auto;

public class ArmTest extends AutoCommand {

    public ArmTest(){

        super(
              //new MoveArmXY(0.3, 0.3, 0.0, 0.0, 0.005),
              new MoveArmXY(0.5, 0.5, 0.0, 0.0, 0.05)


        );
    }

}
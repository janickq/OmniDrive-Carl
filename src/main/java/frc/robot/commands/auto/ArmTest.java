package frc.robot.commands.auto;

public class ArmTest extends AutoCommand {

    public ArmTest(){

        super(
              new MoveArm(0, 0.0, 0.0, 50),
              new MoveArm(200, 0.0, 0.0, 50),
              new MoveArm(100, 0.0, 0.0, 50)

        );
    }

}
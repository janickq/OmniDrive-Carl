package frc.robot.commands.auto;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.commands.auto.PickCommands.GripperPick;

public class End extends AutoCommand{

  public End(){

    super(

      new MoveRobot(2, Math.PI/4, 0, 0, Math.PI),
      new MoveRobot(2, -Math.PI/2, 0, 0, Math.PI),
      new MoveRobot(2, Math.PI/4, 0, 0, Math.PI)
      
    );
    //  clearGroupedCommands();
  }
  @Override
  public void end(boolean interrupted) {
    Globals.checkItem = false;

  }

}
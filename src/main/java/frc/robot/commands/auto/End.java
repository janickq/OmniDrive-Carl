package frc.robot.commands.auto;

import frc.robot.Globals;

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
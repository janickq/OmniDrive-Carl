package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;

public class Pick extends CommandGroupBase {

  public Pick(){
    new SequentialCommandGroup(
      new ParallelCommandGroup(

        new MoveArmXY(Globals.xTgt, Globals.yTgt, 0.0, 0.0, 0.5),
        new MoveRobot(0, Globals.zTgt,  0.0, 0.0, 0.5)

      )

    );

  }

  @Override
  public void addCommands(Command... commands) {
    // TODO Auto-generated method stub

  }
}
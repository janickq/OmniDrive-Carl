package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Vision;

public class Test2 extends CommandBase{


@Override
public void initialize() {

}

  @Override
  public void execute() {


    if(Globals.start){

      CommandScheduler.getInstance().schedule(new MoveRobot(2, Math.PI*2, 0, 0, 1));
      Globals.start = false;

    }

    CommandScheduler.getInstance().schedule(new CheckItem());
    CommandScheduler.getInstance().cancelAll();
  }
  @Override
  public boolean isFinished() {
    return false;
  }

}
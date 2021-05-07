package frc.robot.commands.auto;

import java.util.Set;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Globals;


public abstract class ComplexAuto extends CommandGroupBase {

  public ComplexAuto(Command... commands) {

    parallel(commands);
  }

  @Override
  public void addCommands(Command... commands) {
    // TODO Auto-generated method stub

  }





}
package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Pathfinder;

public class CreatePath extends CommandBase {

  String pointname;
  
  public CreatePath(String pointname) {

    this.pointname = pointname;

  }
  @Override
  public void initialize() {

  }

}
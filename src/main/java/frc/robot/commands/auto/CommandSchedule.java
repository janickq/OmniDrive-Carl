package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.Vision;

public class CommandSchedule extends AutoCommand{

 private final static Vision m_vision = RobotContainer.m_vision;
  



  
  public CommandSchedule(){

    super(

      new MovePose(Points.Pick),
      new WaitCommand(2), 
      new InstantCommand(m_vision::getItem),
      new Pick(),
      new WaitCommand(2), 
      new MovePose(Points.waypoint1),
      new WaitCommand(1),
      new Deliver(),
      new GripperPick(4),
      new MovePose(Points.Pick),
      new WaitCommand(2),
      new InstantCommand(m_vision::getItem),
      new InstantCommand(() -> Globals.runFlag = false )

        
    
    );



  }




}
package frc.robot.commands.auto;

import java.util.Map;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.AutoCommand;
import frc.robot.subsystems.Vision;

public class CommandSchedule extends AutoCommand{

 private final static Vision m_vision = RobotContainer.m_vision;
  



  
  public CommandSchedule(){

    super(


      new InstantCommand(m_vision::getItem),
      new Pick(),
      new WaitCommand(2), 
      new Deliver(),
      new Reset(),
      new WaitCommand(2),
      // new MoveRobot(1, 0.1, 0, 0, 0.5),
      // new MoveRobot(1, -0.1, 0, 0, 0.5),
      //new Pick(),
      new InstantCommand(m_vision::getItem),
      new InstantCommand(() -> Globals.runFlag = false )

        
    
    );



  }




}
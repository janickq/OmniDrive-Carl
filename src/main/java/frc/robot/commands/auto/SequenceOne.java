package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.OmniDrive;
import frc.robot.subsystems.Vision;

public class SequenceOne extends AutoCommand{

 private final static Vision m_vision = RobotContainer.m_vision;
 private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;



  
  public SequenceOne(){

    super(

        
    
    );



  }




}
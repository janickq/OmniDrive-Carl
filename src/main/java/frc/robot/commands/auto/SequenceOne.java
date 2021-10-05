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
      new WaitCommand(1),
      new InstantCommand(m_vision::itemLook),
      new MoveRobot(0, -0.1, 0, 0, 0.2),
      new WaitCommand(2), 
      new InstantCommand(m_vision::getItem),
      new Pick(),
      new WaitCommand(1), 
      new MoveRobot(2, Math.PI/2, 0, 0, 1),
      new WaitCommand(1),
      new Deliver(),
      new MoveArmXY(Constants.ARM2 - 0.05, Constants.ARM1 - 0.05, 0, 0, 0.2),
      new GripperPick(4),
      new MoveRobot(2, 0.1, 0, 0, 0.5),
      new MoveRobot(2, -0.1, 0, 0, 0.5),
      new MoveArmXY(Constants.ARM2 - 0.1 , Constants.ARM1 + 0.12, 0, 0, 0.5),
      new FollowPath("Pick"),
      new AlignPick(),
      new WaitCommand(1),
      new ZeroPose(),
      new InstantCommand(m_omnidrive::resetPose),
      new WaitCommand(2),
      new InstantCommand(m_vision::getItem),
      new InstantCommand(() -> Globals.runFlag = false )

        
    
    );



  }




}
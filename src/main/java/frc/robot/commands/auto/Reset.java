package frc.robot.commands.auto;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Reset extends AutoCommand {

  private final static OmniDrive m_omnidrive = RobotContainer.m_omnidrive;

  
  public Reset(){
    
    super(

      new MovePose("Pick"),
      new Start(),
      new WaitCommand(1),
      new ZeroPose(),
      new InstantCommand(m_omnidrive::resetPose),
    
    );
    

  }
}
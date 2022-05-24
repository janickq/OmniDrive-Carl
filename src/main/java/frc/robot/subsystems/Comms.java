package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.utils.CommandID;

public class Comms extends SubsystemBase{
    public final static CommandID cmdID = RobotContainer.m_ID;

    public Comms(){
        
    }



    @Override
    public void periodic() {
 
        cmdID.updateID();
        SmartDashboard.putString("Debug for CMDID", "can i see this");
        
    }

}
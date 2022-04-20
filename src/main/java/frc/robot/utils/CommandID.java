package frc.robot.utils;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandID{

    public int CommandID;

    public CommandID(){
        CommandID = 0;
    }
    

    public void updateID(){
        SmartDashboard.putNumber("CommandID", CommandID);
    }

    public void updateName(String name){
        SmartDashboard.putString("CommandName", name);
    }

    public void incrementID(){
        CommandID++;
    }
    
    public void resetID(){
        CommandID = 0;
    }

}
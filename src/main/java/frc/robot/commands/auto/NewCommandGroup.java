package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//WPI imports
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public abstract class NewCommandGroup extends CommandBase
{

    public NewCommandGroup()
    {
        super();
        KimiNawa();
        
    }


    
    public void KimiNawa() {
        SmartDashboard.putString("CurrentCommand", this.getClass().toString());
    }


}

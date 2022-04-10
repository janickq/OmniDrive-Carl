package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MainSequence;
import frc.robot.commands.auto.MoveArmXY;
import frc.robot.commands.auto.Test;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.commands.gamepad.OI;

public class Menu extends SubsystemBase
{

    private final OI m_oi = RobotContainer.m_oi;
    private final Points m_points = RobotContainer.m_points;
    private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final Vision m_vision = RobotContainer.m_vision;


    // Shuffleboard
    private final ShuffleboardTab tab = Shuffleboard.getTab("Menu");
    private final NetworkTableEntry D_button = tab.add("button", "?").getEntry();
    private final NetworkTableEntry D_menu = tab.add("menu", "?").getEntry();
    int menuNum=0;
    
    private final String[] menuName;
    public Menu() {
        m_oi.buttonStart.whenPressed(             
            new SelectCommand(
            Map.ofEntries(
                Map.entry(menuNum++, new Test())
                ),
            ()->Globals.menuItem

            // use clearGroupedCommands() to reuse commands
            )
        ); 
        m_oi.buttonBack.whenPressed(
            new SequentialCommandGroup(
                new InstantCommand(m_points::resetMap),
                new InstantCommand(m_omnidrive::resetGyro),
                new InstantCommand(m_omnidrive::setZeroPose),
                new GripperPick(4), 
                new MoveArmXY(Constants.ARM2 - 0.1 , Constants.ARM1 + 0.1, 0, 0, 0.5)
            )
        );
        m_oi.buttonA.whenPressed( ()->{Globals.menuItem--;Globals.menuItem=(Globals.menuItem+menuNum)%menuNum;});
        m_oi.buttonY.whenPressed( ()->{Globals.menuItem++;Globals.menuItem%=menuNum;});
        menuName = new String[menuNum];
        menuName[0] = "test";

    }


    @Override
    public void periodic()
    {
        D_menu.setString( menuName[Globals.menuItem]);
        if(getCurrentCommand() != null){
            SmartDashboard.putString("current menu command", getCurrentCommand().toString());
        }
    }
}
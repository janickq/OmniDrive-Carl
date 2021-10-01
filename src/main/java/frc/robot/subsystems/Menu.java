package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SelectCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.MoveArmXY;
import frc.robot.commands.auto.Test;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.commands.auto.Tasks.Task1;
import frc.robot.commands.auto.Tasks.Task2;
import frc.robot.commands.auto.Tasks.Task3;
import frc.robot.commands.auto.Tasks.Task4;
import frc.robot.commands.auto.Tasks.Task5;
import frc.robot.commands.auto.Tasks.Task6;
import frc.robot.commands.auto.Tasks.Task7;
import frc.robot.commands.auto.Tasks.Task9Black;
import frc.robot.commands.auto.Tasks.Task9Blue;
import frc.robot.commands.auto.Tasks.Task9Green;
import frc.robot.commands.auto.Tasks.Task9Red;
import frc.robot.commands.auto.Tasks.Task9Yellow;
import frc.robot.commands.auto.Tasks.Task8.Task8b;
import frc.robot.commands.auto.Tasks.Task8.Task8c;
import frc.robot.commands.auto.Tasks.Task8.Task8k;
import frc.robot.commands.auto.Tasks.Task8.Task8n;
import frc.robot.commands.gamepad.OI;

public class Menu extends SubsystemBase
{

    private final OI m_oi = RobotContainer.m_oi;
    private final Points m_points = RobotContainer.m_points;


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
                Map.entry(menuNum++, new Test()),
                Map.entry(menuNum++, new Task1()),
                Map.entry(menuNum++, new Task2()),
                Map.entry(menuNum++, new Task3()),
                Map.entry(menuNum++, new Task4()),
                Map.entry(menuNum++, new Task5()),
                Map.entry(menuNum++, new Task6()),
                Map.entry(menuNum++, new Task7()),
                Map.entry(menuNum++, new Task8c()),
                Map.entry(menuNum++, new Task8n()),
                Map.entry(menuNum++, new Task8b()),
                Map.entry(menuNum++, new Task8k()),
                Map.entry(menuNum++, new Task9Black()),
                Map.entry(menuNum++, new Task9Blue()),
                Map.entry(menuNum++, new Task9Red()),
                Map.entry(menuNum++, new Task9Yellow()),
                Map.entry(menuNum++, new Task9Green()),
                Map.entry(menuNum++, 
                    new SequentialCommandGroup(
                        new InstantCommand(() -> Globals.curPose = m_points.jigOffset),
                        new GripperPick(4), 
                        new MoveArmXY(Constants.ARM2-0.05, Constants.ARM1+0.15, 0, 0, 0.5)
                    )
                ) 
                ),
            ()->Globals.menuItem

            // use clearGroupedCommands() to reuse commands
            )
        ); 
        m_oi.buttonBack.whenPressed(
            new SequentialCommandGroup(
                new InstantCommand(() -> Globals.curPose = m_points.jigOffset),
                new GripperPick(4), 
                new MoveArmXY(Constants.ARM2-0.05, Constants.ARM1+0.15, 0, 0, 0.5)
            )
        );
        m_oi.buttonA.whenPressed( ()->{Globals.menuItem--;Globals.menuItem=(Globals.menuItem+menuNum)%menuNum;});
        m_oi.buttonY.whenPressed( ()->{Globals.menuItem++;Globals.menuItem%=menuNum;});
        menuName = new String[menuNum];
        menuName[0] = "test";
        menuName[1] = "task1";
        menuName[2] = "task2";
        menuName[3] = "task3";
        menuName[4] = "task4";
        menuName[5] = "task5";
        menuName[6] = "task6";
        menuName[7] = "task7";
        menuName[8] = "task8chips";
        menuName[9] = "task8nissin";
        menuName[10] = "task8ball";
        menuName[11] = "task8kitkat";
        menuName[12] = "task9black";
        menuName[13] = "task9blue";
        menuName[14] = "task9red";
        menuName[15] = "task9yellow";
        menuName[16] = "task9green";
        menuName[17] = "reset";

    }


    @Override
    public void periodic()
    {
    
        D_menu.setString( menuName[Globals.menuItem]);

    }
}
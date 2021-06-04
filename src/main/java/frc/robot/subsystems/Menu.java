package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SelectCommand;
//WPI imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Globals;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.SequentialMove;
// import frc.robot.commands.auto.MoveTypes.MoveBack;
// import frc.robot.commands.auto.MoveTypes.MoveCurve;
// import frc.robot.commands.auto.MoveTypes.MoveLeft;
// import frc.robot.commands.auto.MoveTypes.MoveRight;
//import frc.robot.commands.auto.TestMove;
import frc.robot.commands.gamepad.OI;

public class Menu extends SubsystemBase
{

    private final OI m_oi = RobotContainer.m_oi;

    // Shuffleboard
    private final ShuffleboardTab tab = Shuffleboard.getTab("Menu");
    //private final NetworkTableEntry D_servoPos = tab.add("Servo Position", 0).withWidget(BuiltInWidgets.kNumberSlider)
    //       .withProperties(Map.of("min", 0, "max", 300)).getEntry();
    private final NetworkTableEntry D_button = tab.add("button", "?").getEntry();
    private final NetworkTableEntry D_menu = tab.add("menu", "?").getEntry();
    int menuNum=0;
    
    private final String[] menuName;
    public Menu() {
        m_oi.buttonStart.whenPressed(             
            new SelectCommand(
            Map.ofEntries(
                Map.entry(menuNum++, new SequentialMove()),
                Map.entry(menuNum++, new SequentialMove()),
                Map.entry(menuNum++, new SequentialMove()),
                Map.entry(menuNum++, new SequentialMove()),
                Map.entry(menuNum++, new SequentialMove()) 
                ),
            ()->Globals.menuItem

            // use clearGroupedCommands() to reuse commands
            )
        );

        m_oi.buttonA.whenPressed( ()->{Globals.menuItem--;Globals.menuItem=(Globals.menuItem+menuNum)%menuNum;});
        m_oi.buttonY.whenPressed( ()->{Globals.menuItem++;Globals.menuItem%=menuNum;});
        menuName = new String[menuNum];
        menuName[0] = "task0";
        menuName[1] = "task1";
        menuName[2] = "task2";
        menuName[3] = "task3";
        menuName[4] = "task4";
    }


    @Override
    public void periodic()
    {
      
        D_menu.setString( menuName[Globals.menuItem]);

    }
}
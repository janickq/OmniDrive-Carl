package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Globals;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.auto.PickCommands.GripperPick;
import frc.robot.subsystems.Sensor;
import frc.robot.subsystems.Vision;

public class Test extends AutoCommand{

    private static double spd1 = 0.3;
    private static double spd2 = 0.1;
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private final static Vision m_vision = RobotContainer.m_vision;

    

    public Test() {

        super(

                // new Start(),
                // new WaitCommand(2), 
                new InstantCommand(m_vision::getItem),
                
                new Test2(),
                
                new End()
                // new PerpetualCommand(
                //     new Test2()  
                // ).raceWith(new CheckItem()),
                // new End()
                // new ParallelRaceGroup(
                //     new WaitUntilCommand(Globals.checkItem::getAsBoolean), 
                    // new WaitUntilCommand(Globals.checkItem::getAsBoolean),
                
                // new SequentialCommandGroup
                // (
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset(),
                //     new WaitCommand(2), 
                //     new InstantCommand(m_vision::getItem),
                //     new Pick(),
                //     new WaitCommand(2), 
                //     new Deliver(),
                //     new Reset()
                
                // new CommandSchedule().raceWith(new CheckItem()),
                // ),
                


                        

                    
                
            
        );
    }



}
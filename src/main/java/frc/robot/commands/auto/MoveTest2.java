package frc.robot.commands.auto;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;

// import the commands


/**
 * DriveMotor class
 * <p>
 * This class creates the inline auto command to drive the motor
 */
public class MoveTest2 extends CommandBase
{
    private int state=1;
    private boolean flag = false;
    private boolean m_endFlag = false;
    private CommandSchedule cmd = new CommandSchedule();

    //private final ShuffleboardTab tab = Shuffleboard.getTab("Debug");

    //private final NetworkTableEntry D_state = tab.add("State", 0).getEntry();

	  public MoveTest2()
    {
      state=0;
    }

    @Override
    public void initialize()
    {
      //cmd = new CommandSchedule();
      if (Globals.curItem == 4){
        flag = true;
        Globals.runFlag = false;
      }
      else
        flag = false;
      state = 0;
      m_endFlag = false;
    }
    @Override
    public void execute()
    {
        if (flag==false) {
            //launch command group

            cmd.schedule(false);
            flag = true;
            Globals.runFlag = true;
            state++;
        }
        else {
            if (Globals.runFlag == false) {
                //command group finished, reset flag
                state++;
                if (Globals.curItem == 4)
                   m_endFlag = true;
                else
                  flag = false;

            }
        }
        //D_state.setNumber(state);
        Globals.debug1 = flag;
        Globals.debug2 = state;
        Globals.debug4 = m_endFlag;
    }

    @Override
    public boolean isFinished()
    {
        Globals.debug4 = m_endFlag;
        return m_endFlag;
    }
    @Override
    public void end(boolean interrupted)
    {
        //D_state.setNumber(-1);
    }
}
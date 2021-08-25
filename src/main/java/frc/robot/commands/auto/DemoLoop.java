package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Globals;
import frc.robot.Points;
import frc.robot.RobotContainer;
import frc.robot.subsystems.OmniDrive;

// import the commands
public class DemoLoop extends CommandBase
{

    private boolean flag = false;
    private boolean m_endFlag = false;
    private Demo cmd = new Demo();
    private final OmniDrive m_omnidrive = RobotContainer.m_omnidrive;
    private final Points m_points = RobotContainer.m_points;

	  public DemoLoop()
    {

    }

    @Override
    public void initialize()
    {
      m_omnidrive.setPose(m_points.getPoint("Zero"));
      m_points.updatePoint("Pick", m_points.getPoint("Zero"));



      if (Globals.curItem == 4){
        flag = true;
        Globals.runFlag = false;
      }
      else
        flag = false;

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

        }
        else {
            if (Globals.runFlag == false) {
                //command group finished, reset flag

                if (Globals.curItem == 4)
                   m_endFlag = true;
                else
                  flag = false;

            }
        }

    }

    @Override
    public boolean isFinished()
    {

        return m_endFlag;
    }
    @Override
    public void end(boolean interrupted)
    {

    }
}
package frc.robot.commands.auto.Tasks;
import frc.robot.commands.auto.MoveRobot;


/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveRobotSense2 extends MoveRobot
{
    private final end_func f_ptr;
    interface end_func {
        public boolean endCondition();
    }

    public MoveRobotSense2(int type, double dist, double startSpeed, double endSpeed, double maxSpeed, end_func f)
    {
        super(type, dist, startSpeed, endSpeed, maxSpeed);
        f_ptr = f;
    }



	@Override
    public boolean endCondition()
    {
        return f_ptr.endCondition();
    }


}
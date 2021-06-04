package frc.robot.commands.auto;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Sensor;


/**
 * SimpleDrive class
 * <p>
 * This class drives a motor 
 */
public class MoveRobotSense1 extends MoveRobot
{   
    private final static Sensor m_sensor = RobotContainer.m_sensor;
    private double moveDist;
    private double endDist;
    private double avgCobraValue;
    private double totalCobraValue;
    private boolean m_endFlag = false;
    private double offset;


    interface end_func {
        public boolean endCondition();
    }

    public MoveRobotSense1(int type, double dist, double startSpeed, double endSpeed, double maxSpeed) {
        super(type, dist, startSpeed, endSpeed, maxSpeed);
        //f_ptr = f;
    }

    @Override
    public void initialize() {
        endDist = (distMoved+ 0.5);
    }

    @Override
    public boolean endCondition()
    {
        for(int i=0; i<4; i++) {
			totalCobraValue += m_sensor.getCobraRawValue(i);
        }
        avgCobraValue = totalCobraValue/4;
        moveDist = getDistMoved();
        if(moveDist >= endDist || avgCobraValue > 1400)
        {
            m_endFlag = true;
        }
        totalCobraValue = 0;
        return m_endFlag;
      
    }
    

}
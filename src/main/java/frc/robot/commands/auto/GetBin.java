package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Points;
import frc.robot.RobotContainer;

public class GetBin extends CommandBase {

  Points m_points = RobotContainer.m_points;
  Pose2d Bin;
  boolean endflag;
  public GetBin() {
    endflag = false;
  }


  @Override
  public void initialize() {
    Bin = m_points.getPoint("Bin");
    if (Bin.getTranslation().getY() < 2)
      m_points.updatePoint("binPick", m_points.getPoint("BinRight"));
    else {
      m_points.updatePoint("binPick", m_points.getPoint("BinBack"));
    }
    endflag = true;
  }
  @Override
  public boolean isFinished() {
    return endflag;
  }
}
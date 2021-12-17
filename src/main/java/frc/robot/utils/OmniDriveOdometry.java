package frc.robot.utils;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Twist2d;
 
public class OmniDriveOdometry {
    private Pose2d m_pose;

    public OmniDriveOdometry(Pose2d initialPose) {
    m_pose = initialPose;
  }

  /**
   * Resets the robot's position on the field.
   *
   * @param poseMeters The position on the field that your robot is at.
   */
  public void resetPosition(Pose2d pose) {
    m_pose = pose;
  }
    /**
   * Returns the position of the robot on the field.
   *
   * @return The pose of the robot (x and y are in meters).
   */
  public Pose2d getPose() {
    return m_pose;
  }
  /**
   * Updates the robot position on the field using distance measurements from encoders. This
   * method is more numerically accurate than using velocities to integrate the pose and
   * is also advantageous for teams that are using lower CPR encoders.
   *
   * @param dx  change in robot x pos.
   * @param du  change in robot y pos
   * @param dw  change in robot w pos
   * @return The new pose of the robot.
   */
  public Pose2d update(double dx, double dy, double dw) {

    var newPose = m_pose.exp( new Twist2d(dx, dy, dw) );

    Rotation2d newAangle = m_pose.getRotation().plus(new Rotation2d(dw));
    m_pose = new Pose2d(newPose.getTranslation(), newAangle);

    return m_pose;
  }
}

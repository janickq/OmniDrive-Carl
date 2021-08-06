package frc.robot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public final class Points {

  public static final Pose2d Pick = new Pose2d(1.1-0.1, 1.1, new Rotation2d(-Math.PI/2));
  public static final Pose2d kitKatDrop = new Pose2d(1, 2.6, new Rotation2d(0));
  public static final Pose2d chipsDrop = new Pose2d(0.5, 2.6, new Rotation2d(0));
  public static final Pose2d waypoint1 = new Pose2d(0.35, 1.1, new Rotation2d(0));
  public static final Pose2d nissinDrop = new Pose2d(1, 2.6, new Rotation2d(0));
  public static final Pose2d ballDrop = new Pose2d(0.5, 2.6, new Rotation2d(0));

  // public static Pose2d curPoseTransformed;
  public static final Pose2d jigOffset = new Pose2d(0.22, 0.23, new Rotation2d(0));
}
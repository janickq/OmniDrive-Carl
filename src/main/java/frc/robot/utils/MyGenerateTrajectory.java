package frc.robot.utils;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

public class MyGenerateTrajectory {
    private List<Translation2d> m_IntermediateWP;
    private List<Pose2d> m_TrajectoryWP;
    private Trajectory mTrajectory;
    
    //Create intermediate way points as A* output with right angle path
    // are not suitable for trajectory generator
    //Intermediate way points are R distance from the original waypoint.
    //This will cause the path generated to be a small curve between the two intermediate waypoints
    public List<Translation2d> AddIntermediateWayPoints(List<Translation2d> wp, double R){
        List<Translation2d> wp2 = new ArrayList<Translation2d>();
        Translation2d t1, t2, t12;
        int N = wp.size();
        wp2.add(wp.get(0)); //Copy start point
        if (N>2) {
            for (int i=1; i<N-1; i++){
                double x_1 = wp.get(i-1).getX();
                double y_1 = wp.get(i-1).getY();
                double x0 = wp.get(i).getX();
                double y0 = wp.get(i).getY();
                double x1 = wp.get(i+1).getX();
                double y1 = wp.get(i+1).getY();
                double dx = x0 - x_1;
                double dy = y0 - y_1;
                double a0 = Math.atan2(dy, dx);
                double len = Math.sqrt(dx*dx + dy*dy);
                t1 = new Translation2d(x0-R*Math.cos(a0), y0-R*Math.sin(a0));
                //t1 = new Translation2d(x0-len*Math.cos(a0)/2, y0-len*Math.sin(a0)/2);
                dx = x0 - x1;
                dy = y0 - y1;
                double a1 = Math.atan2(dy, dx);
                len = Math.sqrt(dx*dx + dy*dy);
                t2 = new Translation2d(x0-R*Math.cos(a1), y0-R*Math.sin(a1));
                //t2 = new Translation2d(x0-len*Math.cos(a1)/2, y0-len*Math.sin(a1)/2);

                //Not for quinitc
                {
                    t12 = wp.get(i-1).plus(wp.get(i)).div(2);
                    //if (i!=1)
                        wp2.add(t12);  // Not for quintic
                    wp2.add(t2);
                }

            }
        }

        t12 = wp.get(N-2).plus(wp.get(N-1)).div(2);
        wp2.add(t12);

        wp2.add(wp.get(N-1)); //Copy end point
        m_IntermediateWP = wp2;

        return wp2;
    }   
    
    //Generate trajectory given a list of waypoints
    //use Quintic Hermite spline
    public Trajectory generateTrajectoryQuinticHermite( List<Translation2d> wp, TrajectoryConfig config, double R) {

        List<Pose2d> waypoints = new ArrayList<Pose2d>();
        Pose2d p;
 
        int N = wp.size();

        //Create intermediate way points as A* waypoints with right angle path
        //are not suitable for trajectory generator
        //Intermediate way points are R distance from the original waypoint.
        //This will cause the path generated to be a small curve between the two intermediate waypoints

        for (int i=0; i<N-1; i++){
            double x0 = wp.get(i).getX();
            double y0 = wp.get(i).getY();
            double x1 = wp.get(i+1).getX();
            double y1 = wp.get(i+1).getY();
            double dx = x1 - x0;
            double dy = y1 - y0;
            double len = Math.sqrt(dx*dx + dy*dy);

            Rotation2d angle = new Rotation2d(Math.atan2(dy, dx));
            if (i==0) {
                p = new Pose2d(x0, y0, angle);
                waypoints.add(p);
                //Take care of straight line path. 2 waypoints only
                if ((i+1)==(N-1)) {
                    p = new Pose2d(x1, y1, angle);
                    waypoints.add(p);    
                }
                else {
                    if (len > R) {
                        // Add waypoints only if segment is long enough
                        p = new Pose2d(x1-R*angle.getCos(), y1-R*angle.getSin(), angle);
                        waypoints.add(p);
                    }    
                }
            }
            else {
                if (len > R*2) {
                    p = new Pose2d(x0+R*angle.getCos(), y0+R*angle.getSin(), angle);
                    waypoints.add(p);
                    if ((i+1)==(N-1)) {
                        //Add end point
                        p = new Pose2d(x1, y1, angle);
                        waypoints.add(p);    
                    }
                    else {
                        p = new Pose2d(x1-R*angle.getCos(), y1-R*angle.getSin(), angle);
                        waypoints.add(p);
                    }
                }
                else {
                    if ((i+1)==(N-1)) {
                        //Add end point
                        p = new Pose2d(x1, y1, angle);
                        waypoints.add(p);    
                    }

                }
            }

        }
        m_TrajectoryWP = waypoints;

        //quintic hermite splines
        //Can control waypoints angle
        mTrajectory = TrajectoryGenerator.generateTrajectory(waypoints, config);

        //Debug print out
        // for (double i=0.0; i<=mTrajectory.getTotalTimeSeconds(); i+=0.02) {
        //      p = mTrajectory.sample(i).poseMeters;
        //     System.out.println(p);
        // }

        return mTrajectory;    
    }

    //Generate trajectory given a list of waypoints
    //use Clamped Cubic spline
    //Need to add the right control points in order for the path to be smooth.
    public Trajectory generateTrajectoryClampedCubic( List<Translation2d> wp, TrajectoryConfig config, double R) {

        List<Translation2d> wp2 = new ArrayList<Translation2d>();
        List<Translation2d> waypoints = new ArrayList<Translation2d>();
        Pose2d start, end;

        wp2 = AddIntermediateWayPoints(wp, R);
        //wp2.addAll(wp);
        //m_IntermediateWP = wp2;

        //Calculate start end points angle.
        //This allows us to control the spline curve
        //start
        double x0 = wp2.get(0).getX();
        double y0 = wp2.get(0).getY();
        double x1 = wp2.get(1).getX();
        double y1 = wp2.get(1).getY();
        double dx = x1 - x0;
        double dy = y1 - y0;
        Rotation2d angle = new Rotation2d(Math.atan2(dy, dx));
        start = new Pose2d(x0, y0, angle);
        //System.out.println(start);

        int i = wp2.size()-2;
        x0 = wp2.get(i).getX();
        y0 = wp2.get(i).getY();
        x1 = wp2.get(i+1).getX();
        y1 = wp2.get(i+1).getY();
        dx = x1 - x0;
        dy = y1 - y0;
        angle = new Rotation2d(Math.atan2(dy, dx));
        end = new Pose2d(x1, y1, angle);
        //System.out.println(end);

        for ( i=1; i<(wp2.size()-1); i++){

            waypoints.add(wp2.get(i));
            //System.out.println(wp2.get(i));

        }


        // Clamped cubic spline
        // Generates car-like path
        mTrajectory = TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            start,
            // Pass through these waypoints
            waypoints,
            // End 
            end,
            config);

        //Debug
    //     for (double t=0.0; t<=mTrajectory.getTotalTimeSeconds(); t+=0.02) {
    //         Pose2d p = mTrajectory.sample(t).poseMeters;
    //         System.out.println(p);
    //    }
        return mTrajectory;    
    }

    public Trajectory getTrajectory() {
        return mTrajectory;
    }

    public List<Translation2d> getIntermediateWP() {
        return m_IntermediateWP;
    }

    public List<Pose2d> getTrajectoryWP() {
        return m_TrajectoryWP;
    }
}

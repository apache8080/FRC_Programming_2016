package org.usfirst.frc.team3256.robot;


public class DriveTest {
	
	public static void print(Segment[] segment, String str) {
	      for (int i = 0; i < segment.length; i++) {
	         System.out.print(str + segment[i] + "\n");
	      }
	   }
	public static void main(String[] args) {
		
		TrajectoryGenerator leftTrajectoryGenerator;
		TrajectoryGenerator rightTrajectoryGenerator;
		Segment[] leftTrajectory;
		Segment[] rightTrajectory;
		double accel_max = 15;
		double error = 120;
		double total_distance = ((error)/12);
		double vel_max_l = 12.0;
		double time_accel_l = 12.0/accel_max;
		double distance_accel_l = time_accel_l*12*0.5;
		leftTrajectoryGenerator = new TrajectoryGenerator(total_distance, vel_max_l, accel_max, time_accel_l, distance_accel_l, 50);
		//Right Side needs to accelerate faster since the right side moves mechanically slower.
		double time_accel_r = 11.5/accel_max;
		System.out.println("L" + distance_accel_l);
		double distance_accel_r = time_accel_r*11.5*0.5;
		System.out.println("R" + distance_accel_r);
		rightTrajectoryGenerator = new TrajectoryGenerator(total_distance, vel_max_l, accel_max, time_accel_r, distance_accel_r, 50);
		leftTrajectory = leftTrajectoryGenerator.getTrajectory();
		rightTrajectory = rightTrajectoryGenerator.getTrajectory();
		print(leftTrajectoryGenerator.getTrajectory(), "left");
		System.out.println("----------------------------------------------");
		print(rightTrajectoryGenerator.getTrajectory(),"right");
		
		
	}

}

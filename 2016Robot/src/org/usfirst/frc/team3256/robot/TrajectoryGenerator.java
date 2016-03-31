package org.usfirst.frc.team3256.robot;

import java.util.List;
import java.util.ArrayList;

import org.usfirst.frc.team3256.robot.Segment;

public class TrajectoryGenerator {

	/**
	* This is the TrajectoryGenerator class that creates the trajectory for a certain distance, velocity, and acceleration.
	* The Trajectory is stored in an array.
	*/


	double max_v;
	double max_accel;
	double time_accel;
	double distance_total;
	double distance_accel;
	double distance_deaccel = 0.0;
	double distance_cruise = 0.0;
	double time_cruise = 0.0;
	double time_total = 0.0;
	double control_loop;

	public TrajectoryGenerator(double distance_total,
	double max_v, double max_accel,
	double time_accel, double distance_accel,
	double control_loop){
		this.distance_total = distance_total;
		this.max_v = max_v;
		this.max_accel = max_accel;
		this.time_accel = time_accel;
		this.distance_accel = distance_accel;
		this.control_loop = control_loop;
		this.distance_deaccel = distance_accel;
		this.distance_cruise = distance_total-(distance_accel+distance_deaccel);
		this.time_cruise = distance_cruise/max_v;
		this.time_total = (time_cruise+(time_accel+time_accel));
	}

	/**
	* This method returns the total time this movement should take
	* @return time_total (total time to move)
	*/
	public double getTimeTotal(){
		return time_total;
	}

	/**
	* This method returns the max velocity of the robot.
	* @return max_v (max velocity of robot)
	*/
	public double getMaxVelocity(){
		return max_v;
	}


	/**
	* This method calculates the velocity in ft/s at a specific time.
	* @param  time (time in seconds based off of FPGA Timestamp)
	* @return velocity (velocity of robot at time in ft/s)
	*/
	public double calculateV(double time){
		double time_deaccel = time_total-time_accel;
		double velocity = 0;

		if (time<time_accel){
			velocity = max_accel*time;
		}
		else if (time > time_accel && time < time_deaccel){
			velocity = max_v;
		}
		else {
			velocity = max_v-(max_accel*(time-time_deaccel));
		}
		return velocity;
	}

	/**
	* This method calculates the acceleration in ft/s^2 at a specific time.
	* @param  time (time in seconds based off of FPGA Timestamp)
	* @return acceleration (acceleration of robot at time in ft/s^2)
	*/
	public double calculateA(double time){
		double acceleration = 0;
		double time_deaccel = time_total-time_accel;
		if (time<time_accel){
			acceleration = max_accel;
		}
		else if (time > time_accel && time < time_deaccel){
			acceleration = 0;
		}
		else {
			acceleration = -max_accel;
		}
		return acceleration;

	}

	/**
	* This method calculates the position of the robot in feet at a specific time.
	* @param  time (time in seconds based off of FPGA Timestamp)
	* @return positon (position of robot at time in feet)
	*/
	public double calculateS(double time){
		double position = 0;
		double time_deaccel = time_total-time_accel;
		if (time > time_accel && time < time_deaccel){
			position = max_v*time;
		}
		else {
			position = (max_accel/2)*time*time;
		}
		return position;
	}

	/**
	* This function returns an array of segments that make up the Trajectory.
	* @return trajectory (array of segments)
	*/
	public Segment[] getTrajectory(){
		double time = 0.0;
		List<Segment> trajectory = new ArrayList<Segment>();
		while(time<=time_total){
			double velocity = calculateV(time);
			double acceleration = calculateA(time);
			double position = calculateS(time);
			//System.out.println(velocity + "//////////" + acceleration + "\n");
			System.out.println("Position" + position);
			//System.out.println("Velocity" + velocity);
			//System.out.println("Acceleration" + acceleration);
			System.out.println("Time" + time);
			Segment s = new Segment(time, velocity, acceleration, position);
			trajectory.add(s);
			time += (1/control_loop);
		}
		Segment[] trajectory_arr = new Segment[trajectory.size()];
		trajectory.toArray(trajectory_arr);
		return trajectory_arr;
	}
}

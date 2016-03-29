package org.usfirst.frc.team3256.robot;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;

public class PIDController {
	
	
	public PIDController(){
		
	}
	static double time_total;
	static double time_cruise;
	static double max_v=6;
	static double max_accel=6;
	static double time_accel = 1.0;
	static double kV = 1/max_v;
	static double kA = 0.01;
	static double distance_accel = 3.0;
	static double distance_deaccel = 3.0;
	static double distance_cruise;
	static double kP = 1;
	static double kI = 0;
	static double kD = 0;
	static double P;
	static double I;
	static double D;
	static double error;
	static double time_initial = 0;
	static double sumError;
	static double prevError;
	static double changeError;
	static double PID;
	
	public static double getTimeTotal(){
		return time_total;
	}
	public static double calculateV(double time){
		double time_deaccel = time_total-time_accel;
		double returntime = 0;
		
		if (time<time_accel){
			returntime = 6*time;
		}
		else if (time > time_accel && time < time_deaccel){
			returntime = 6;
		}
		else {
			returntime = 6-(6*(time-time_deaccel));
		}
		return returntime;
	}
	
	public static double calculateA(double time){
		double returntime = 0;
		double time_deaccel = time_total-time_accel;
		if (time<time_accel){
			returntime = 6;
		}
		else if (time > time_accel && time < time_deaccel){
			returntime = 0;
		}
		else {
			returntime = -6;
		}
		return returntime;
		
	}
	
	public static double calculateS(double time){
		double returntime = 0;
		double time_deaccel = time_total-time_accel;
		if (time > time_accel && time < time_deaccel){
			returntime = 6*time;
		}
		else {
			returntime = 4.5*time*time;
		}
		return returntime;
	}
	public static double calculatePID(double setpoint, double current){
		error = setpoint-current;
		P = kP * error;
		sumError = sumError + error;
		I = sumError * kI;
		changeError = prevError = error;
		D = changeError * kD;
		PID = P+I+D;
		prevError = error;
		return PID;
		
		
	}
	public static double driveStraight(double feet, double time, double current){
		distance_cruise = feet-(distance_accel+distance_deaccel);
		time_cruise = distance_cruise/6;
		time_total = (time_cruise + time_accel + time_accel);
		//System.out.println("Time: "+time_total+"\n");
		//System.out.println("Velocity: "+calculateV(time)+ "////// Acceleration:" +calculateA(time)+"\n");
		
		double output = (kV * calculateV(time))+(kA*calculateA(time)+calculatePID(calculateS(time), current));
		//double output = 0.0;
		return output;
	}
}

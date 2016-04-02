package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PID;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class TurnTest extends Command{
	
	boolean aligned = false;
	double times = 0;

	public TurnTest(){
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {
		DriveTrain.shiftDown();
		aligned = false;
		times = 0;
	}

	
	protected void execute() {
		aligned = DriveTrain.turnToGoal(RobotMap.CamAngle, RobotMap.CamDirection);
		if (aligned){
			times++;
			aligned = false;
		}
	}

	
	protected boolean isFinished() {
		return times >= 5;
	}

	
	protected void end() {
		DriveTrain.setLeftMotorSpeed(0);
		DriveTrain.setRightMotorSpeed(0);
	}

	
	protected void interrupted() {
		
	}

}

package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.*;

public class DriveTrain {
	
	private Talon leftFront, leftRear, rightFront, rightRear;
	
	public DriveTrain(){
		leftFront = new Talon (RobotMap.leftFrontMotor);
		rightFront = new Talon (RobotMap.rightFrontMotor);
		leftRear = new Talon (RobotMap.leftRearMotor);
		rightRear = new Talon (RobotMap.rightRearMotor);
	}
	
	public void tankDrive(int left, int right){
		leftFront.set(left);
		leftRear.set(left);
		rightFront.set(right);
		rightRear.set(right);
	}
	}
	
	
}

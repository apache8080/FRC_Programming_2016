package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem{
	static VictorSP winchMotors = new VictorSP(RobotMap.winchMotors);
	static VictorSP pivotMotors = new VictorSP(RobotMap.hangerPivotMotors);
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	//brings hanger in
	public static void incrementIn(){
		pivotMotors.set(1);
	}
	
	//brings hanger out
	public static void incrementOut(){
		pivotMotors.set(-1);
	}
	
	//winches hanger motor
	public static void winchMotor(){
		winchMotors.set(1);
	
	}
}
	

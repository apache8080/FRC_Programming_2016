package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem{
	static VictorSP winchMotor = new VictorSP(RobotMap.winchMotor);
	static VictorSP pivotMotor = new VictorSP(RobotMap.pivotMotor);
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	//brings hanger in
	public static void incrementIn(){
		pivotMotor.set(1);
	}
	
	//brings hanger out
	public static void incrementOut(){
		pivotMotor.set(-1);
	}
	
	//winches hanger motor
	public static void winchMotor(){
		winchMotor.set(1);
	}
}
	

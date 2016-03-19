package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hanger extends Subsystem{
	static VictorSP winchMotors = new VictorSP(RobotMap.winchMotors);
	static VictorSP pivotMotors = new VictorSP(RobotMap.hangerPivotMotors);
	static DoubleSolenoid hangerHolder = new DoubleSolenoid(RobotMap.hangerHolderEngage, RobotMap.hangerHolderDisengage);
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
	
	public static void holdHanger(){
		hangerHolder.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void releaseHanger(){
		hangerHolder.set(DoubleSolenoid.Value.kReverse);
	}
	
}
	

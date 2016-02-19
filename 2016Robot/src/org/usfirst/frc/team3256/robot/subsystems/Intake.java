package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	
	static VictorSP intakePivotMotor = new VictorSP(RobotMap.intakePivotMotor);
	static VictorSP intakeRollerMotor = new VictorSP(RobotMap.intakeRollerMotor);
	static Encoder intakePivotEncoder = new Encoder(RobotMap.intakePivotEncoderA, RobotMap.intakePivotEncoderB);;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	//rolls intake in
	public static void intake (){
		intakeRollerMotor.set(1);
	}
	
	//rolls intake out
	public static void outake (){
		intakeRollerMotor.set(-1);
	}
	
	public static void stopIntake(){
		intakeRollerMotor.set(0);
	}
	
	public static void incrementIn(int speed){
		intakePivotMotor.set(speed);
	}
	
	public static void incrementOut(int speed){
		intakePivotMotor.set(-speed);
	}
	
	public static void resetEncoder(){
		intakePivotEncoder.reset();
	}
	
	public static int getEncoderValue(){
		int ticks = intakePivotEncoder.get();
		return ticks;
	}
}

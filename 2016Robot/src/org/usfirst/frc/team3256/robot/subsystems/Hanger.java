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
	public void incrementIn(){
		pivotMotor.setPosition(69);
	}
	
	//brings hanger out
	public void incrementOut(){
		pivotMotor.setPosition(69);
	}
	
	//winches hanger motor
	public static void winchMotor(double pos){
		winchMotor.setPosition(pos);
	}
	
	public static double getMotorPos(double pos){
		return pos;
		
	}
}

package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Intake extends PIDSubsystem{
	static VictorSP intakeRollerMotor = new VictorSP(RobotMap.intakeRollerMotor);
	static VictorSP intakePivotMotors = new VictorSP(RobotMap.intakePivotMotors);
//	static Relay intakeSpike = new Relay(RobotMap.SpikePort);
	static AnalogPotentiometer intakePivotPot = new AnalogPotentiometer(RobotMap.intakePotPort, RobotMap.intakePotFactor, RobotMap.intakePotOffset);
	
	public static final int intakePos = 0;
	public static final int stowPos = 1;
	private static final double P = 1,
	    	I = 0,
	    	D = 0;
	
	public Intake(){
		super("IntakePivot", P, I, D);
		getPIDController().setContinuous(false);
		
	}
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	//rolls intake in
	public static void intake (){
		intakeRollerMotor.set(.75);
	}
	
	//rolls intake out
	public static void outake(){
		intakeRollerMotor.set(-.75);
	}
	
	public static void stopIntake(){
		intakeRollerMotor.set(0);
	}
		
	public static void incrementIn(double speed){
		intakePivotMotors.set(speed);

	}
	
	public static void incrementOut(double speed){
		intakePivotMotors.set(-speed);
	}
	
	public static void pivotStop(){
		intakePivotMotors.set(0);
	}
	
	public static double getPotValue(){
		return intakePivotPot.get();
	}
	

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Math.abs(intakePivotPot.get());
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if (output<0) output = 0;
		intakePivotMotors.set(output);
		
	}
	
	
	
}

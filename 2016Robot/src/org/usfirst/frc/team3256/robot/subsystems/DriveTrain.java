package org.usfirst.frc.team3256.robot.subsystems;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
/**
 *
 */
public class DriveTrain extends PIDSubsystem {
	//Check port numbers in RobotMap
	static VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor); 
	static VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
	static VictorSP rightFront = new VictorSP(RobotMap.rightFrontMotor);
	static VictorSP rightRear = new VictorSP(RobotMap.rightRearMotor);
	
	static Encoder rightEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	static Encoder leftEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	
    
	// Initialize your subsystem here
    public DriveTrain() {
    	super("DriveTrain", 100, 0, 0);
    	setAbsoluteTolerance(2);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public static void tankDrive(double left, double right){
    	//determine which motor to reverse later. Left is reversed for now
    	leftFront.set(-left);
    	leftRear.set(-left);
    	rightFront.set(right);
    	rightRear.set(right);
    	//clipping values
    	if (Math.abs(right)<0.1) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.1) {
    		left = 0;
    	}
    }
    public static void arcadeDrive(double throttle, double turn){
    	double left = throttle+turn;
    	double right = throttle-turn;
    	leftFront.set(left);
    	leftRear.set(left);
    	rightFront.set(right);
    	rightRear.set(right);
    	if (left > 1){
    		left = 1;
    	}
    	if (left < -1){
    		left = -1;
    	}
    	if (right > 1){
    		right = 1;
    	}
    	if (right < -1){
    		right = -1;
    	}
    	
    	
    }
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return rightEncoder.get();
    	
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	leftFront.pidWrite(output);
    	rightFront.pidWrite(output);
    	
    	
    }
}

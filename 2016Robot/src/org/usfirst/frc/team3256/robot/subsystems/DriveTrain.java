package org.usfirst.frc.team3256.robot.subsystems;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	
	static DoubleSolenoid leftPancake = new DoubleSolenoid (RobotMap.PancakeLeftIn, RobotMap.PancakeLeftOut);
	static DoubleSolenoid rightPancake = new DoubleSolenoid (RobotMap.PancakeRightIn, RobotMap.PancakeRightOut);
	
	static AnalogGyro gyro = new AnalogGyro(0);
	
    private static final double P = 100,
    	I = 0,
    	D = 0;
	
	
	// Initialize your subsystem here
    public DriveTrain() {
    	//(String Name,P value,I value,D value) all in ticks because we're using encoder
    	super("DriveTrain", P, I, D);
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
   
    public static void initGyro(){
    	gyro.initGyro();
    }
    
    public static void resetGyro(){
    	gyro.reset();
    }
    
    public static double getAngle(){
    	double factor = 1;
    	return gyro.getAngle()*(factor);
    }
    
    public static void calibrateGyro(){
    	gyro.calibrate();
    }
    public static void sensitivityGyro(){
    	double sensitivity = 0.25;
    	gyro.setSensitivity(sensitivity);
    }
    
    
    //shift transmissions
    public static void shiftPancake(boolean getRightBumper){
    	//test later which is which
    	if (getRightBumper){
    	rightPancake.set(DoubleSolenoid.Value.kReverse);
    	leftPancake.set(DoubleSolenoid.Value.kReverse);
    	}
    	else{
    	rightPancake.set(DoubleSolenoid.Value.kForward);
    	leftPancake.set(DoubleSolenoid.Value.kForward);
    	}
    }
    
    public static void tankDrive(double left, double right){
    	//clipping values
    	if (Math.abs(right)<0.1) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.1) {
    		left = 0;
    	}
    	
    	//determine which motor to reverse later. Left is reversed for now
    	leftFront.set(left);
    	leftRear.set(left);
    	rightFront.set(-right);
    	rightRear.set(-right);
    }
    public static void arcadeDrive(double throttle, double turn){
    	double left = throttle+turn;
    	double right = throttle-turn;
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
    	leftFront.set(left);
    	leftRear.set(left);
    	rightFront.set(right);
    	rightRear.set(right);
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

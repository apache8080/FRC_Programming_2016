package org.usfirst.frc.team3256.robot.subsystems;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.Button;
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
	
	static DoubleSolenoid shifterPancake = new DoubleSolenoid (RobotMap.ShifterIn,RobotMap.ShifterOut);
	
	static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	
	static double pi = 3.1415926535897932384626;
	static double ticksPerRotation = 1920; //encoder is 256 ticks/rotations
		
    private static final double P = 1,
    	I = 0.000,
    	D = 0.0;
	
	
	// Initialize your subsystem here
    public DriveTrain() {
    	//(String Name,P value,I value,D value) all in ticks because we're using encoder
    	super("DriveTrain", P, I, D);	
    	setAbsoluteTolerance(0.2);
    	getPIDController().setContinuous(false);
    	
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    //initializes gyro
    public static void initGyro(){
    	gyro.initGyro();
    }
    
    //resets gyro value
    public static void resetGyro(){
    	gyro.reset();
    }
    
    //gets gyro angle
    public static double getAngle(){
    	double factor = 360.0/350.0;
    	return gyro.getAngle()*factor;
    }
    
    //calibrates gyro
    public static void calibrateGyro(){
    	gyro.calibrate();
    }
    
    //shift transmissions
    public static void shiftPancake(boolean getRightBumper){
    	//test later which is which
    	if (getRightBumper){
    		shifterPancake.set(DoubleSolenoid.Value.kReverse);
     	}
    	else{
    		shifterPancake.set(DoubleSolenoid.Value.kForward);
    	}
    }
    
    //shifts to low gear
    public void shiftDown(){
    	shifterPancake.set(DoubleSolenoid.Value.kForward);
    }
    
    //shifts to high gear
    public void shiftUp(){
    	shifterPancake.set(DoubleSolenoid.Value.kReverse);
    }
    
    //sets left sides motor power
    public static void setLeftMotorSpeed(double speed){
    	leftFront.set(speed);
    	leftRear.set(speed);
    }

    //sets right sides motor power
    public static void setRightMotorSpeed(double speed){
    	rightFront.set(speed);
    	rightRear.set(speed);
    }
    
    //gets right encoder value
    public static int getRightEncoder(){
		return rightEncoder.get();
    }
    
    //gets left encoder value
    public static int getLeftEncoder(){
    	return leftEncoder.get();
    }
    
    //resets encoders
    public static void resetEncoders(){
    	rightEncoder.reset();
    	leftEncoder.reset();
    }
   public static double inchesToTicks(double distance){
    	return (distance/(6*pi)*ticksPerRotation);
    }
    
    
    //tankdrive
    public static void tankDrive(double left, double right){
    	//clipping values
    	if (Math.abs(right)<0.2) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.2) {
    		left = 0;
    	}
    	leftFront.set(left);
    	leftRear.set(left);
    	rightFront.set(-right);
    	rightRear.set(-right);
    }
    
    public static void tankDriveReversable(double left, double right, boolean rightBumper1){
    	if (Math.abs(right)<0.2) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.2) {
    		left = 0;
    	}
    	if (rightBumper1){
    		leftFront.set(-left);
    		leftRear.set(-left);
    		rightFront.set(right);
    		rightRear.set(right);
    	}
    	else 
    		leftFront.set(left);
    		leftRear.set(left);
    		rightFront.set(-right);
    		rightRear.set(-right);
    }
    //arcadedrive
    public static void arcadeDrive(double throttle, double turn){
    	
    	if (Math.abs(throttle)<0.2) {
    		throttle = 0;
    	}
    	if (Math.abs(turn)<0.2) {
    		turn = 0;
    	}
    	
    	double left = throttle-turn;
    	double right = throttle+turn;
    	
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
    	rightFront.set(-right);
    	rightRear.set(-right);
    }
    
    public static void arcadeDriveReverse(double throttle, double turn){
    	if (Math.abs(throttle)<0.2) {
    		throttle = 0;
    	}
    	if (Math.abs(turn)<0.2) {
    		turn = 0;
    	}
    	throttle = -throttle;
    	turn = -turn;
    	
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
        	rightFront.set(-right);
        	rightRear.set(-right);
    	}
  
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Math.abs(rightEncoder.get());
    	
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if (output < 0) output = 0;
    	leftFront.pidWrite(-output);
    	rightFront.pidWrite(output);
    	leftRear.pidWrite(-output);
    	rightRear.pidWrite(output);
    }
}

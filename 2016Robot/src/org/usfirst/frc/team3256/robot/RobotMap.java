package org.usfirst.frc.team3256.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
	//motors
	public static final int leftFrontMotor = 0,
			leftRearMotor = 1,
			rightFrontMotor = 2,
			rightRearMotor = 3,
			intakePivotMotor = 8,
			intakeRollerMotor = 7,
			winchMotors = 6,
			hangerPivotMotors = 4,
			catapultWinch = 5;
			
	
	//encoders
	public static final int rightDriveEncoderA = 0,
			rightDriveEncoderB = 1,
			leftDriveEncoderA = 2,
			leftDriveEncoderB = 3,
			intakePivotEncoderA = 4,
			intakePivotEncoderB = 5;
	
	//actuators
	public static final int ShifterIn = 3;
	public static final int ShifterOut = 4;
	public static final int winchEngage = 1;
	public static final int winchDisengage = 6;
	public static final int ballHolderEngage = 2;
	public static final int ballHolderDisengage = 5;
	
	//joysticks
	public static final int Joystick1 = 0;
	public static final int Joystick2 = 1;
	
	//Sensors
	public static final int catapultLimitSwitch = 6;
	public static final int ballIR = 7;
	
	//Global Variables
	public static double gyroPos;
	public static double photoCenterOfGravityX;
	public static double photoCenterOfGravityY;
	
	
	public static String cameraIP = "10.32.56.11";
	

	
}

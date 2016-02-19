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
			intakePivotMotor = 4,
			intakeRollerMotor = 5,
			winchMotor = 4,
			pivotMotor = 5;
	
	//encoders
	public static final int rightDriveEncoderA = 0,
			rightDriveEncoderB = 1,
			leftDriveEncoderA = 2,
			leftDriveEncoderB = 3,
			intakePivotEncoderA = 4,
			intakePivotEncoderB = 5;
	
	//actuators
	//tune port values
	public static final int ShifterIn = 0;
	public static final int ShifterOut = 1;
	
	
	//Global Variables
	public static double gyroPos;
	
	
}

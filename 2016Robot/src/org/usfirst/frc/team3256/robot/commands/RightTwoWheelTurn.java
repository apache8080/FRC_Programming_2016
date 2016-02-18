package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightTwoWheelTurn extends Command {
	double robotRadius;
	double pi = 3.1415926535897932384626;
	double robotCircum;
	double arc;
	double turnFactor = robotCircum/arc/2;
	int speed;
    public RightTwoWheelTurn(double arc, int speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.arc=arc;
    	this.speed=speed;
    	DriveTrain.inchesToTicks(turnFactor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	DriveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(-speed);
    	DriveTrain.setRightMotorSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (DriveTrain.getLeftEncoder()<=-turnFactor && DriveTrain.getRightEncoder()>=turnFactor){
    		return true;
    	}
    	else
    		return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

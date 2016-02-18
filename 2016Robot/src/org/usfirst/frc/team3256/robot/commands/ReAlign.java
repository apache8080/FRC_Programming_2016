package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReAlign extends Command {
	int leftMotorFactor;
	int rightMotorFactor;

    public ReAlign(int lMotorFactor, int rMotorFactor) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	leftMotorFactor = lMotorFactor;
    	rightMotorFactor = rMotorFactor;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(leftMotorFactor);
    	DriveTrain.setRightMotorSpeed(rightMotorFactor);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (DriveTrain.getAngle()==0){
    		return true;
    	}
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReAlign extends Command {

	int speed;

    public ReAlign(int speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.speed=speed;
    
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (DriveTrain.getAngle()<0){
    	DriveTrain.setLeftMotorSpeed(speed);
    	DriveTrain.setRightMotorSpeed(-speed);
    	}
   		
    	if (DriveTrain.getAngle()>0){
    		DriveTrain.setLeftMotorSpeed(-speed);
    		DriveTrain.setRightMotorSpeed(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (DriveTrain.getAngle()==0){
    		return true;
    	}
    	else
    		return false;
    }

    // Called once after isfinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

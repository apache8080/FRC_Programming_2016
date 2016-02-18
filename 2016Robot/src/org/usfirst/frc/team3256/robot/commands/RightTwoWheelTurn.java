package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightTwoWheelTurn extends Command {
	
	int rightspeed;
	int leftspeed;
	double leftPosition;
	double rightPosition;
	
    public RightTwoWheelTurn(int rspeed, int lspeed, double lPos, double rPos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	rightspeed = rspeed;
    	leftspeed = lspeed;
    	leftPosition = lPos;
    	rightPosition = rPos;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(leftspeed);
    	DriveTrain.setRightMotorSpeed(rightspeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (DriveTrain.getRightEncoder()<rightPosition && DriveTrain.getLeftEncoder()>leftPosition){
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

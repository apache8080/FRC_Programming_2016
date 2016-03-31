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
	double robotCircum = 2*pi*robotRadius;
	double arc;
	double turnFactorInches;
	double turnFactorTicks;
	double speed;
    public RightTwoWheelTurn(double speed, double degrees) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	turnFactorInches = robotCircum*arc/2;
    	arc = degrees/360;
    	this.speed=speed;
    	
    	turnFactorTicks=DriveTrain.inchesToTicksLG(turnFactorInches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	DriveTrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(speed);
    	DriveTrain.setRightMotorSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (DriveTrain.getLeftEncoder()>=turnFactorTicks && DriveTrain.getRightEncoder()<=-turnFactorTicks){
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

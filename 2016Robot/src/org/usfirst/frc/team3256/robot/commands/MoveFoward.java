package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFoward extends Command {
	double error;
	double time_initial;
	double time_current;
	double speed;
	double pos_current;
	
    public MoveFoward(double error) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.drivetrain);
    	this.error = error;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//DriveTrain.resetEncoders();
    	//DriveTrain.resetGyro();
    	time_initial = Timer.getFPGATimestamp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time_current = Timer.getFPGATimestamp() - time_initial;
    	pos_current = ((Math.abs(DriveTrain.getLeftEncoder())+Math.abs(DriveTrain.getRightEncoder()))/2);
    	speed = PIDController.driveStraight(error/12, time_current, pos_current);
    	
    	if(speed<0.0){
    		speed=0.0;
    	}
    	if (speed > 0.875)
    		DriveTrain.setLeftMotorSpeed(0.875);
    	else
    		DriveTrain.setLeftMotorSpeed(speed);
    	DriveTrain.setRightMotorSpeed(-speed);
    	
    	System.out.print("speed" + speed + "/////////////////////" + time_current + "\n");
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	time_current = Timer.getFPGATimestamp() - time_initial;
    	if (time_current > PIDController.getTimeTotal()){
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

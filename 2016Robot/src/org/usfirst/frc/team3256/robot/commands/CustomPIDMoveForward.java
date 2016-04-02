package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PID;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class CustomPIDMoveForward extends Command {

	double power;
	double inches;
	double ticks;
	
    public CustomPIDMoveForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.drivetrain);
    	this.inches = inches;
    	ticks = DriveTrain.inchesToTicksHG(inches);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	DriveTrain.shiftUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	power = PID.calculatePID(DriveTrain.getLeftEncoder(), ticks);
    	DriveTrain.setLeftMotorSpeed(power);
		DriveTrain.setRightMotorSpeed(-power);
		System.out.println("ticks: " + ticks + "    Current " + DriveTrain.getLeftEncoder());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return ticks - Math.abs(DriveTrain.getLeftEncoder()) < 1000;
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

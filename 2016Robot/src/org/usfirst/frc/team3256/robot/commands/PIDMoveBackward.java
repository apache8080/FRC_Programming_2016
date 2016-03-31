package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class PIDMoveBackward extends Command{

	double Pos;
	
	public PIDMoveBackward(double Pos){
		this.Pos=-DriveTrain.inchesToTicksLG(Pos);
		requires(Robot.drivetrain);
		setInterruptible(false);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		DriveTrain.resetEncoders();
		Robot.drivetrain.setSetpoint(Pos);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
    	return Math.abs(Robot.drivetrain.getSetpoint()-Robot.drivetrain.getPosition())<1000;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		DriveTrain.setLeftMotorSpeed(0);
		DriveTrain.setLeftMotorSpeed(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}

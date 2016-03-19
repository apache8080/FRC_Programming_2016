package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDoNothingCommand extends Command{

	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		requires(Robot.drivetrain);
		setInterruptible(false);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		DriveTrain.setLeftMotorSpeed(0);
		DriveTrain.setRightMotorSpeed(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}

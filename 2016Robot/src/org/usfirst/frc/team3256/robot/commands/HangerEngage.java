package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.Hanger;

import edu.wpi.first.wpilibj.command.Command;

public class HangerEngage extends Command{

	public HangerEngage(){
		requires(Robot.hanger);
		setInterruptible(false);
	}
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		setTimeout(1);
		Hanger.holdHanger();
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
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

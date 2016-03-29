package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoDriveForward extends CommandGroup{

	public AutoDriveForward(){
		setInterruptible(false);
		addSequential (new ShiftDown());
		addSequential(new PIDMoveForward(80));

	}
}

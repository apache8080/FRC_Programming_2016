package org.usfirst.frc.team3256.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	//pulls the catapult back
	public void winchBack(){
	
	}
	
	//releases the winch and shoots the ball via pancake actuator
	public void disEngageWinch(){
		
	}
	
	//engages the winch to be able to pull back and reload via pancake actuator
	public void engageWinch (){

	}
	
	//based on if the catapult is pulled back via limit switch
	public boolean isWinched(){
		return true;
	}
	
	//based on if the ball is detected via IR Breaker
	public boolean isLoaded(){
		return true;
	}
	

}

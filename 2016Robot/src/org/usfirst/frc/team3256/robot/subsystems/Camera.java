package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Camera extends Subsystem{

	USBCamera intakeCam = new USBCamera(RobotMap.intakeCameraName);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//gets distance from camera
	public double getShootingDistance(){
		return 100;
	}
	//gets angle from camera
	public double getShootingAngle(){
		return 100;
	}
	
	
	
	

}

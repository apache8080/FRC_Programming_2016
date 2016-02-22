package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class Camera extends Subsystem{

	AxisCamera cam = new AxisCamera(RobotMap.cameraIP);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	//gets distance from camera
	public double getDistance(){
		return 100;
	}
	//gets angle from camera
	public double getAngle(){
		return 100;
	}
	
	

}

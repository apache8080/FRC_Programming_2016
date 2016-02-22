package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team3256.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	static Joystick joystick = new Joystick(0);
	
	
	public OI(int port) {
        joystick = new Joystick(port);
        
        //SmartDashboard.putData("EngageBallActuators", new EngageBallActuators());
        //SmartDashboard.putData("Shift Up", new ShiftUp());
        //SmartDashboard.putData("Shift Down", new ShiftDown());
    }
	
	static Button buttonA = new JoystickButton(joystick, 1);
	static Button buttonB = new JoystickButton(joystick, 2);
	static Button buttonX = new JoystickButton(joystick, 3);
	static Button buttonY = new JoystickButton(joystick, 4);
	static Button leftBumper = new JoystickButton(joystick,5);
	static Button rightBumper = new JoystickButton(joystick,6);
	static Button buttonLeftStick = new JoystickButton(joystick, 7);
	static Button buttonRightStick = new JoystickButton(joystick, 8);
    
	public static double getLeftY(){return joystick.getRawAxis(1);}
    public static double getLeftX(){return joystick.getRawAxis(2);}
    public static double getRightY(){return joystick.getRawAxis(5);}
    public static double getRightX(){return joystick.getRawAxis(4);}
    public static double getRightTrigger() {return joystick.getRawAxis(3);}
    public static boolean getButtonA(){return joystick.getRawButton(1);}
    public static boolean getButtonB(){return joystick.getRawButton(2);}
    public static boolean getButtonX(){return joystick.getRawButton(100);}
    public static boolean getButtonY(){return joystick.getRawButton(100);}
    public static boolean getLeftBumper(){return joystick.getRawButton(5);}
    public static boolean getRightBumper(){return joystick.getRawButton(6);}
    
    
	
		
}


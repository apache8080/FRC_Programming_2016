package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	static Joystick joystick = new Joystick(0);
	
	/*
	public OI(int port) {
        joystick = new Joystick(port);
    }
	*/
	Button buttonA = new JoystickButton(joystick, 1);
    Button buttonB = new JoystickButton(joystick, 2);
    Button buttonX = new JoystickButton(joystick, 3);
    Button buttonY = new JoystickButton(joystick, 4);
    Button buttonLeftStick = new JoystickButton(joystick, 5);
    Button buttonRightStick = new JoystickButton(joystick, 6);
	
	public static double getLeftY(){return joystick.getRawAxis(1);}
    public static double getLeftX(){return joystick.getRawAxis(2);}
    public static double getRightY(){return joystick.getRawAxis(5);}
    public static double getRightX(){return joystick.getRawAxis(4);}
    public boolean getButtonA(){return joystick.getRawButton(1);}
    public boolean getButtonB(){return joystick.getRawButton(2);}
    
	
		
}



package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3256.robot.subsystems.Hanger;
import org.usfirst.frc.team3256.robot.subsystems.Intake;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import org.usfirst.frc.team3256.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public OI joy1;
	public OI joy2;
	public static DriveTrain drivetrain;
	public static Compressor compressor;
	public static Hanger hanger;
	public static Intake intake;
	public static Shooter shooter;
	
	public static DoubleSolenoid solenoidUno;

    Command autonomousCommand;
	
    //DriveTrain
    Command ShiftUp;
    Command ShiftDown;
    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command IntakeRollers;
    Command OuttakeRollers;
    Command StopRollers;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	drivetrain = new DriveTrain();
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		hanger = new Hanger();
		intake = new Intake();
		shooter = new Shooter();
		ShiftUp = new ShiftUp();
		ShiftDown = new ShiftDown();
		IntakeIncrementIn = new IntakeIncrementIn(100,100);
		IntakeIncrementOut = new IntakeIncrementOut(-100,100);
		IntakeRollers = new IntakeRollers();
		OuttakeRollers = new OuttakeRollers();
		StopRollers = new StopRollers();
		
		solenoidUno = new DoubleSolenoid(0,7);
		
		joy1 = new OI(0);
		joy2 = new OI(1);
		
		DriveTrain.initGyro();
        DriveTrain.calibrateGyro();

        SmartDashboard.putString("DistanceText", "Distance");
        SmartDashboard.putString("AngleText", "Angle");
        SmartDashboard.putString("BallStatusText", "Ball Status");
        
        //SmartDashboard.putData("autonomousCommand", autonomousCommand);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) autonomousCommand.start();
       /* ShiftUp.start();
        ShiftDown.start();
        ShiftUp.start();
        ShiftDown.start();*/
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    	
    	solenoidUno.set(DoubleSolenoid.Value.kReverse);
		Shooter.engageBallActuators();
		Shooter.engageWinch();
        
        DriveTrain.resetGyro();
        
        //DriveTrain.sensitivityGyro();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        DriveTrain.tankDrive(OI.getLeftY(),OI.getRightY());
        
        /*
        if (OI.getRightBumper())
        	ShiftDown.start();
        else 
        	ShiftUp.start();
        */
        joy1.rightBumper.whenPressed(ShiftUp);
        joy1.rightBumper.whenReleased(ShiftDown);
       
        joy2.buttonA.whileHeld(IntakeRollers);
        joy2.buttonY.whileHeld(OuttakeRollers);
        joy2.buttonA.whenReleased(StopRollers);
        joy2.buttonY.whenReleased(StopRollers);
        
    	SmartDashboard.putNumber("Gyro", DriveTrain.getAngle());
    	SmartDashboard.putBoolean("BallIn", true);
    	SmartDashboard.putBoolean("Distance", true);
    	SmartDashboard.putBoolean("Angle", false);
    	
    	SmartDashboard.putNumber("DistanceAway", 12.34);
    	SmartDashboard.putNumber("AngleOff", 2.345);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3256.robot.subsystems.Hanger;
import org.usfirst.frc.team3256.robot.subsystems.Intake;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import org.usfirst.frc.team3256.robot.commands.*;
import org.usfirst.frc.team3256.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain drivetrain;
	public static Compressor compressor;
	public static Hanger hanger;
	public static Intake intake;
	public static Shooter shooter;
	public static NetworkTable networkTable;
	public static double[] roboRealmData;
	
	//Axis Camera
	AxisCamera shooterCam = new AxisCamera(RobotMap.shooterCameraIP);

    Command autonomousCommand;
	
    //DriveTrain
    Command ShiftUp;
    Command ShiftDown;
    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command StopIntakePivot;
    Command IntakeRollers;
    Command OuttakeRollers;
    Command StopRollers;
    //Shooter
    Command ShootBall;
    Command ReEngageWinch;
    Command CatapultWinch;
    Command CatapultWinchStop;
    Command EngageBallActuators;
    Command DisengageBallActuators;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	networkTable = NetworkTable.getTable("Smartdashboard");
    	networkTable.initialize();
    	
    	//subsystems
    	drivetrain = new DriveTrain();
		compressor = new Compressor(0);
		hanger = new Hanger();
		intake = new Intake();
		shooter = new Shooter();
		
	    //commands
		ShiftUp = new ShiftUp();
		ShiftDown = new ShiftDown();
		IntakeIncrementIn = new IntakeIncrementIn();
		IntakeIncrementOut = new IntakeIncrementOut();
		StopIntakePivot = new StopIntakePivot();
		IntakeRollers = new IntakeRollers();
		OuttakeRollers = new OuttakeRollers();
		StopRollers = new StopRollers();
		ShootBall = new ShootBall();
		ReEngageWinch = new ReEngageWinch();
		CatapultWinch = new CatapultWinch();
		CatapultWinchStop = new CatapultWinchStop();
		EngageBallActuators = new EngageBallActuators();
		DisengageBallActuators = new DisengageBallActuators();
		
		//actuators
		ShiftUp.start();
		ReEngageWinch.start();
		
		//compressor
		compressor.setClosedLoopControl(true);
		
		//initialize gyro
		DriveTrain.initGyro();
        DriveTrain.calibrateGyro();

        //initial dashboard info
        SmartDashboard.putString("DistanceText", "Distance");
        SmartDashboard.putString("AngleText", "Angle");
        SmartDashboard.putString("BallStatusText", "Ball Status");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) autonomousCommand.start();
       /* ShiftUp.start();
        * */
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
 
        Intake.stopIntake();
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
        
/*-----------------------------------------Operator Controls-----------------------------------------*/
		
        //Drivetrain
        DriveTrain.arcadeDrive(OI.getLeftY1(), OI.getRightX1());
        //DriveTrain.tankDrive(OI.getLeftY1(),OI.getRightY1());
        OI.rightBumper1.whenPressed(ShiftUp);
        OI.rightBumper1.whenReleased(ShiftDown);
        
        //Shooting
        
        if (OI.getRightTrigger2()/*&& Shooter.isLoaded()*/){
        	Scheduler.getInstance().add(ShootBall);
        } else {
        	Scheduler.getInstance().add(ReEngageWinch);
        }
        
        /*
        if (OI.getLeftTrigger2()/*&& !Shooter.isLoaded()){
        	CatapultWinch.start();
        } else {
        	CatapultWinchStop.start();
        }
        */
        OI.buttonA1.whileHeld(CatapultWinch);
        OI.buttonA1.whenReleased(CatapultWinchStop);
        
        if (shooter.isLoaded())
        	Scheduler.getInstance().add(EngageBallActuators);
        else
        	Scheduler.getInstance().add(DisengageBallActuators);
        
        //Intake
        OI.buttonA2.whileHeld(IntakeRollers);
        OI.buttonY2.whileHeld(OuttakeRollers);
        OI.buttonA2.whenReleased(StopRollers);
        OI.buttonY2.whenReleased(StopRollers);
        
        OI.buttonB2.whileHeld(IntakeIncrementIn);
        OI.buttonX2.whileHeld(IntakeIncrementOut);
        OI.buttonB2.whenReleased(StopIntakePivot);
        OI.buttonX2.whenReleased(StopIntakePivot);
        
/*-----------------------------------------Update Dashboard-----------------------------------------*/
        
    	SmartDashboard.putNumber("Gyro", DriveTrain.getAngle());
    	SmartDashboard.putBoolean("BallIn", true);
    	SmartDashboard.putBoolean("Distance", true);
    	SmartDashboard.putBoolean("Angle", false);
    	SmartDashboard.putBoolean("IR Breaker", Shooter.isLoaded());
    	
    	SmartDashboard.putNumber("DistanceAway", 12.34);
    	SmartDashboard.putNumber("AngleOff", 2.345);
    	
    	SmartDashboard.putNumber("IntakePotValue",Intake.getPotValue());
    	
    	//updates global variables
        RobotMap.photoCenterOfGravityX = networkTable.getNumber("COG_X", 0.0);
		RobotMap.photoCenterOfGravityY = networkTable.getNumber("COG_Y", 0.0);
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables2.type.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	public static SmartDashboard smartdashboard;
	public static double[] roboRealmData;
	
	//Axis Camera
	//AxisCamera shooterCam = new AxisCamera(RobotMap.shooterCameraIP);

    Command autonomousCommand;
	
    //DriveTrain
    Command ShiftUp;
    Command ShiftDown;
    Command MoveForward;
    Command MoveBackward;
    Command PIDMoveForward;

    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command IntakeStopPivot;
    Command IntakeIntakeRollers;
    Command IntakeOuttakeRollers;
    Command IntakeStopRollers;
    //Shooter
    Command ShootBall;
    Command ReEngageWinch;
    Command CatapultWinch;
    Command CatapultWinchStop;
    Command EngageBallActuators;
    Command DisengageBallActuators;
    
    CommandGroup ShootnLoad;
    
    //Autonomous
    Command AutoCommand;
    Command AutoDoNothingCommand;
    SendableChooser AutoChooser;
    
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
		MoveForward = new MoveFoward(0.5, 50);
		PIDMoveForward = new PIDMoveForward(80);
		MoveBackward = new MoveBackward(0.5, 20);
		ShiftUp = new ShiftUp();
		ShiftDown = new ShiftDown();
		IntakeIncrementIn = new IntakeIncrementIn();
		IntakeIncrementOut = new IntakeIncrementOut();
		IntakeStopPivot = new IntakeStopPivot();
		IntakeIntakeRollers = new IntakeIntakeRollers();
		IntakeOuttakeRollers = new IntakeOuttakeRollers();
		IntakeStopRollers = new IntakeStopRollers();
		ShootBall = new ShootBall();
		ReEngageWinch = new ReEngageWinch();
		CatapultWinch = new CatapultWinch();
		CatapultWinchStop = new CatapultWinchStop();
		EngageBallActuators = new EngageBallActuators();
		DisengageBallActuators = new DisengageBallActuators();
		ShootnLoad = new ShootnLoad();
		AutoDoNothingCommand = new AutoDoNothingCommand();
		
		//compressor
		compressor.setClosedLoopControl(true);
		
		AutoChooser = new SendableChooser();
		AutoChooser.addDefault("DoNothing", AutoDoNothingCommand);
		AutoChooser.addObject("MoveForward", PIDMoveForward);
		smartdashboard.putData("Auto Mode Chooser", AutoChooser);
				
		//initialize gyro
		drivetrain.initGyro();
        drivetrain.calibrateGyro();

        //initial dashboard info
        SmartDashboard.putString("DistanceText", "Distance");
        SmartDashboard.putString("AngleText", "Angle");
        SmartDashboard.putString("BallStatusText", "Ball Status");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

   public void autonomousInit() {
	   	AutoCommand = (Command) AutoChooser.getSelected();
	   	AutoCommand.start();
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
        drivetrain.resetGyro();
        drivetrain.resetEncoders();
        drivetrain.disable();
        
        Shooter.disengageBallActuators();
        Shooter.engageWinch();
        
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
        //Arcade drive with reversible toggle
        if (OI.getRightBumper1()){
        	drivetrain.arcadeDriveReverse(OI.getLeftY1(), OI.getRightX1());
        }
        else drivetrain.arcadeDrive(OI.getLeftY1(), OI.getRightX1());
        
        //Tank drive with reversible toggle
        //drivetrain.tankDrive(OI.getLeftY1(),OI.getRightY1());
        //drivetrain.tankDriveReversable(OI.getLeftY1(), OI.getRightY1(), OI.getRightBumper1());
        
        //Shift Gears
        OI.leftBumper1.whenPressed(ShiftDown);
        OI.leftBumper1.whenReleased(ShiftUp);
        
        //test commands
        //OI.buttonY1.whenPressed(EngageBallActuators);
        //OI.buttonY1.whenReleased(DisengageBallActuators);
        
        //Ball Actuators
        if (OI.getY1() && Shooter.isWinched()){
        	Scheduler.getInstance().add(EngageBallActuators);
        }
        OI.buttonA1.whenPressed(DisengageBallActuators);
        
        //Shooting
        OI.leftBumper2.whileHeld(CatapultWinch);
        OI.leftBumper2.whenReleased(CatapultWinchStop);
        OI.rightBumper2.whenPressed(ShootnLoad);
        OI.rightBumper2.whenReleased(ReEngageWinch); 
        
        /*
        if (OI.getRightTrigger2()&&Shooter.isWinched()){
        	Scheduler.getInstance().add(ShootBall);
        } else {
        	Scheduler.getInstance().add(ReEngageWinch);
        }
        
        if (OI.getLeftTrigger2()&&Shooter.isWinched()){
        	Scheduler.getInstance().add(CatapultWinch);
        } else {
        	Scheduler.getInstance().add(CatapultWinchStop);
        }
        */
        
     	if (Shooter.isLoaded() && Shooter.isWinched() && !ShootnLoad.isRunning())
        	Scheduler.getInstance().add(EngageBallActuators);
        else
        	//TODO: Scheduler.getInstance().add(DisengageBallActuators);
        //System.out.println("isLoaded:" + Shooter.isLoaded());
     	//System.out.println("isWinched" + Shooter.isWinched());

        //Intake
        OI.buttonA2.whileHeld(IntakeRollers);
        OI.buttonY2.whileHeld(IntakeOuttakeRollers);
        OI.buttonA2.whenReleased(IntakeStopRollers);
        OI.buttonY2.whenReleased(IntakeStopRollers);
        
        OI.buttonB2.whileHeld(IntakeIncrementIn);
        OI.buttonX2.whileHeld(IntakeIncrementOut);
        OI.buttonB2.whenReleased(IntakeStopPivot);
        OI.buttonX2.whenReleased(IntakeStopPivot);
        
        System.out.println("Intake Potentiometer Value:" + intake.getPotValue());

        
/*-----------------------------------------Update Dashboard-----------------------------------------*/
        
        SmartDashboard.putBoolean("isWinched", shooter.isWinched());
        SmartDashboard.putBoolean("isLoaded", shooter.isLoaded());
        
    	SmartDashboard.putNumber("Gyro", drivetrain.getAngle());
    	
    	SmartDashboard.putBoolean("BallIn", shooter.isLoaded());
    	SmartDashboard.putBoolean("Distance", false);
    	SmartDashboard.putBoolean("Angle", false);
    	
    	SmartDashboard.putNumber("DistanceAway", 12.34);
    	SmartDashboard.putNumber("AngleOff", 2.345);
    	
    	SmartDashboard.putNumber("IntakePotValue",intake.getPotValue());
    	
    	SmartDashboard.putNumber("EncoderLeft", drivetrain.getLeftEncoder());
    	SmartDashboard.putNumber("EncoderRight", drivetrain.getRightEncoder());
    	
    	//updates global variables
        RobotMap.photoCenterOfGravityX = networkTable.getNumber("COG_X", 0.0);
		RobotMap.photoCenterOfGravityY = networkTable.getNumber("COG_Y", 0.0);
    	
		//System.out.println("ShootnLoad Running: " + ShootnLoad.isRunning());
		
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

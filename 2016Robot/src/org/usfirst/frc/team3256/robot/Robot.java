package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.CameraServer;
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
import edu.wpi.first.wpilibj.vision.USBCamera;

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
    Command FullSpeed;
    Command StopDrive;

    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command IntakeStopPivot;
    Command IntakeIntakeRollers;
    Command IntakeOuttakeRollers;
    Command IntakeStopRollers;
    Command IntakePosAuto;
    //Shooter
    Command ShootBall;
    Command ReEngageWinch;
    Command CatapultWinch;
    Command CatapultWinchAutomatic;
    Command CatapultWinchStop;
    Command EngageBallActuators;
    Command DisengageBallActuators;
    
    
    CommandGroup ShootnLoad;
    
    //Autonomous
    Command AutoCommand;
    Command AutoDoNothingCommand;
    Command CustomPIDMoveForward;
    Command TurnTest;
    Command PIDTurnTest;
    SendableChooser AutoChooser;
    CommandGroup AutoDriveForward;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	networkTable = NetworkTable.getTable("Smartdashboard");
    	networkTable.initialize();
		drivetrain.resetEncoders();
		
		CameraServer server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam1");
		//USBCamera cam1 = new USBCamera();
		//cam1.startCapture();
    	
    	//subsystems
    	drivetrain = new DriveTrain();
		compressor = new Compressor(0);
		hanger = new Hanger();
		intake = new Intake();
		shooter = new Shooter();
		
	    //commands
		FullSpeed = new FullSpeed();
		StopDrive = new StopDrive();
		MoveForward = new MoveFoward(140);
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
		IntakePosAuto = new IntakePosAuto();
		ShootBall = new ShootBall();
		ReEngageWinch = new ReEngageWinch();
		CatapultWinch = new CatapultWinch();
		CatapultWinchAutomatic = new CatapultWinchAutomatic();
		CatapultWinchStop = new CatapultWinchStop();
		EngageBallActuators = new EngageBallActuators();
		DisengageBallActuators = new DisengageBallActuators();
		ShootnLoad = new ShootnLoad();
		AutoDoNothingCommand = new AutoDoNothingCommand();
		AutoDriveForward = new AutoDriveForward();
		CustomPIDMoveForward = new CustomPIDMoveForward(120);
		TurnTest = new TurnTest();
		PIDTurnTest = new PIDTurnTest();
		
		
		//compressor
		compressor.setClosedLoopControl(true);
		
		AutoChooser = new SendableChooser();
				
		//initialize gyro
		drivetrain.initGyro();
        drivetrain.calibrateGyro();
    }
	
	public void disabledPeriodic() {
		AutoChooser.addDefault("DoNothing", AutoDoNothingCommand);
		AutoChooser.addObject("MoveForward", AutoDriveForward);
		AutoChooser.addObject("MotionProfilingDriveForward", MoveForward);
		AutoChooser.addObject("CustomPIDMoveForward", CustomPIDMoveForward);
		AutoChooser.addObject("TurnTest", TurnTest);
		AutoChooser.addObject("PIDTurnTest", PIDTurnTest);
		smartdashboard.putData("Auto Mode Chooser", AutoChooser);
		
		Scheduler.getInstance().run();
	}

   public void autonomousInit() {
	   	AutoChooser.initTable(AutoChooser.getTable());
	   	AutoCommand = (Command) AutoChooser.getSelected();
	   	AutoCommand.start();
	   	
	   //	drivetrain.enable();
	   	drivetrain.disable();
	   	intake.enable();
	    drivetrain.resetEncoders();
	   	drivetrain.shiftUp();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	RobotMap.CamAngle = SmartDashboard.getNumber("CameraAngle", 0);
        RobotMap.CamDirection = SmartDashboard.getNumber("Direction", 0);
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
        intake.enable();
        
        //Shooter.disengageBallActuators();
        //Shooter.engageWinch();
        
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
		
        
        //OI.buttonA1.whileHeld(FullSpeed);
        //OI.buttonA1.whenReleased(StopDrive);
        
        //Drivetrain
        //Arcade drive with reversible toggle
        
        if (OI.getRightBumper1()){
        	drivetrain.arcadeDriveReverse(OI.getLeftY1(), OI.getRightX1(), OI.getRightTrigger1());
        }
        else drivetrain.arcadeDrive(OI.getLeftY1(), OI.getRightX1(), OI.getRightTrigger1());
        
        //Tank drive with reversible toggle
        //drivetrain.tankDrive(OI.getLeftY1(),OI.getRightY1());
        //drivetrain.tankDriveReversable(OI.getLeftY1(), OI.getRightY1(), OI.getRightBumper1());
        
        //Shift Gears
        OI.leftBumper1.whenPressed(ShiftDown);
        OI.leftBumper1.whenReleased(ShiftUp);
        
        //OI.buttonX1.whenPressed(new TurnTest(camAngle, camDirection));
        if (OI.getButtonX1()){
        	drivetrain.turnToGoal(RobotMap.CamAngle, RobotMap.CamDirection);
        }
        
        //test commands
        //OI.buttonY1.whenPressed(EngageBallActuators);
        //OI.buttonY1.whenReleased(DisengageBallActuators);
        
        //Ball Actuators
        if (OI.getY1() && Shooter.isWinched()){
        	Scheduler.getInstance().add(EngageBallActuators);
        }
        
        //Shooting
       // OI.leftBumper2.whenPressed(CatapultWinchAutomatic);
        OI.rightBumper2.whenPressed(ShootnLoad);
        
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
        
        	//TODO: Scheduler.getInstance().add(DisengageBallActuators);
        //System.out.println("isLoaded:" + Shooter.isLoaded());
     	//System.out.println("isWinched" + Shooter.isWinched());

        //Intake
        OI.buttonA2.whileHeld(IntakeIntakeRollers);
        OI.buttonY2.whileHeld(IntakeOuttakeRollers);
        OI.buttonA2.whenReleased(IntakeStopRollers);
        OI.buttonY2.whenReleased(IntakeStopRollers);
        
        OI.buttonB2.whenPressed(IntakePosAuto);
        OI.buttonX2.whileHeld(IntakeIncrementIn);
       // OI.buttonB2.whenReleased(IntakeStopPivot);
        OI.buttonX2.whenReleased(IntakeStopPivot);
        
       // System.out.println("Intake Potentiometer Value:" + intake.getPotValue());

        
/*-----------------------------------------Update Dashboard-----------------------------------------*/
        
        RobotMap.CamAngle = SmartDashboard.getNumber("CameraAngle", 0);
        RobotMap.CamDirection = SmartDashboard.getNumber("Direction", 0);
        
        SmartDashboard.putBoolean("isWinched", shooter.isWinched());
        SmartDashboard.putBoolean("isLoaded", shooter.isLoaded());
        //System.out.println("isLoaded" + shooter.isLoaded());
        System.out.println("Left " + DriveTrain.getLeftEncoder() + "Right " + DriveTrain.getRightEncoder());
    	SmartDashboard.putNumber("Gyro", drivetrain.getAngle());
    	
    	//updates global variables
        RobotMap.photoCenterOfGravityX = networkTable.getNumber("COG_X", 0.0);
		RobotMap.photoCenterOfGravityY = networkTable.getNumber("COG_Y", 0.0);
    		
		//System.out.println("ShootnLoad Running: " + ShootnLoad.isRunning());
		//System.out.println(Shooter.isLoaded() + "-----" + Intake.isIntakePosL() + "-----" + Intake.isIntakePosR());
		
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

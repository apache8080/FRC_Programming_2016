package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFoward extends Command {
	double error;
	double time_initial;
	double time_current;
	double speed;
	double pos_current;
	TrajectoryGenerator leftTrajectoryGenerator;
	TrajectoryGenerator rightTrajectoryGenerator;
	Segment[] leftTrajectory;
	Segment[] rightTrajectory;
	MotionProfileController leftDriveStraight;
	MotionProfileController rightDriveStraight;
	double step;

    public MoveFoward(double error) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.drivetrain);
    	double total_distance = error;
    	leftTrajectoryGenerator = new TrajectoryGenerator(total_distance, 6.0, 6.0, 1.0, 3.0, 50);
			//Right Side needs to accelerate faster since the right side moves mechanically slower.
			double time_accel = 6.0/7.0;
			double distance_accel=(time_accel*(6.0)*(0.5));
			rightTragectoryGenerator = new TrajectoryGenerator(total_distance, 6.0, 7.0, time_accel, distance_accel, 50);

			leftTrajectory = leftTrajectoryGenerator.getTrajectory();
			rightTrajectory = rightTragectoryGenerator.getTrajectory();
    	this.error = error;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//DriveTrain.resetEncoders();
    	//DriveTrain.resetGyro();
    	//time_initial = Timer.getFPGATimestamp();
    	leftDriveStraight = new MotionProfileController(leftTrajectory, 6.0, 0.01, 1, 0,0);
			rightDriveStraight = new MotionProfileController(rightTrajectory, 6.0, 0.01, 1, 0,0);
			step=0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
			double left_speed = leftDriveStraight.getSpeed(step, DriveTrain.getLeftEncoder());
			double right_speed = rightDriveStraight.getSpeed(step, DriveTrain.getRightEncoder());
			DriveTrain.setLeftMotorSpeed(left_speed);
			DriveTrain.setRightMotorSpeed(right_speed);
			step++;
    	/*time_current = Timer.getFPGATimestamp() - time_initial;
    	pos_current = ((Math.abs(DriveTrain.getLeftEncoder())+Math.abs(DriveTrain.getRightEncoder()))/2);
    	speed = PIDController.driveStraight(error/12, time_current, pos_current);

    	if(speed<0.0){
    		speed=0.0;
    	}
    	if (speed > 0.875)
    		DriveTrain.setLeftMotorSpeed(0.875);
    	else
    		DriveTrain.setLeftMotorSpeed(speed);
    	DriveTrain.setRightMotorSpeed(-speed);
    	*/

    	System.out.print("Left Speed " + left_speed + "/////////////////////  Right Speed " + right_speed + "\n");

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
			return leftDriveStraight.isFinished() && rightDriveStraight.isFinished();
    	/*time_current = Timer.getFPGATimestamp() - time_initial;
    	if (time_current > PIDController.getTimeTotal()){
    		return true;
    	}
    	else
    		return false;*/
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team3256.robot;

import org.usfirst.frc.team3256.robot.Segment;

public class MotionProfileController {

	/**
	 * This is the MotionProfileController class that follows a pre-calculated trajectory.
	 */


	private Segment[] trajectory;
  private double max_velocity;
  private double kV = 1/max_velocity;
  private double kA;
  private double kP;
  private double kI;
  private double kD;
  private double prev_error;
  private double sum_error;
  private boolean is_finished = false;

	public MotionProfileController(Segment[] trajectory,
                                double max_velocity,
                                double kA,
                                double kP,
                                double kI,
                                double kD){
			this.trajectory = trajectory;
      this.max_velocity = max_velocity;
      this.kA = kA;
      this.kP = kP;
      this.kI = kI;
      this.kD = kD;
      this.prev_error = 0.0;
      this.sum_error = 0.0;

	}

  /**
  * This method takes the velocity and acceleration value at this step and then calculates the motor output.
  * @param   velocity (velocity of robot at this step)
  * @param   acceleration (acceleration of robot at this step)
  * @return  motor output
  */
  public double calculateFeedForward(double velocity, double acceleration){
    return (kV*velocity)+(kA*acceleration);
  }

  /**
   * This method takes what the position should be at this step and what the robot position currently is then using PID calculates the new motor output.
   * @param   setpoint (where the robot should be)
   * @param   current (where the robot currently is)
   * @return  motor output
   */
  public double calculatePID(double setpoint, double current){
    double error = setpoint-current;
    double P = kP*error;
    sum_error += error;
    double I = kI*sum_error;
    double change_error = prev_error-error;
    double D = kD*change_error;
    prev_error = error;

    return (P+I+D);
  }

  /**
   * This returns wether or not the robot is finished traveling the trajectory.
   * @return is_finished (boolean value)
   */
  public boolean isFinished(){
    return is_finished;
  }

  /**
   * This method is called every 20 miliseconds by the MoveForward command to step through the trajectory.
   * @param   step (the spot the robot is at in the trajectory)
   * @param   current (the current position of the robot based off of the encoders)
   * @return  output (the motor value for the robot)
   */
  public double getSpeed(int step, double current_position){
      double output;
      if(step > trajectory.length){
        is_finished=true;
        output = 0.0;
      }else{
        Segment s = trajectory[step];
        velocity = s.getVelocity();
        acceleration = s.getAcceleration();
        position = s.getPosition();
        feed_forward = calculateFeedForward(velocity, acceleration);
        feedback = calculatePID(position, current_position);
        output = feed_forward+feedback;
      }

      return output;
  }

}

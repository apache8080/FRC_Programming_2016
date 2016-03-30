package org.usfirst.frc.team3256.robot;

public class Segment {

	/**
	 * This is the segment class. Each segment has a time value in seconds, velocity, acceleration, and position.
	 */


	private double time;
  private double velocity;
  private double acceleration;
  private double positon;

	public Segment(double time, double velocity, double acceleration, double positon){
			this.time = time;
      this.velocity = velocity;
      this.acceleration = acceleration;
      this.positon = positon;
	}

  /**
   * Returns time that this segment is at.
   * @return time (time of segment in seconds)
   */
  public double getTime(){
    return time;
  }

  /**
   * Returns velocity value in ft/s at this segment.
   * @return velocity (velocity of robot in ft/s)
   */
  public double getVelocity(){
    return velocity;
  }

  /**
   * Returns acceleration value in ft/s^2 at this segment.
   * @return acceleration (acceleration of robot in ft/s^2)
   */
  public double getAcceleration(){
    return acceleration;
  }

  /**
   * Returns position value in feet at this segment.
   * @return positon (position of robot in feet)
   */
  public double getPosition(){
    return positon;
  }
}

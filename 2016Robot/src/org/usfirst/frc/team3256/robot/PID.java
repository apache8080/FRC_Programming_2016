package org.usfirst.frc.team3256.robot;

public class PID {
	static double P;
	static double I;
	static double D;
	static double kP;
	static double kI;
	static double kD; 	
	static double error = 0;
    static double sumError = 0;
    static double changeError = 0;
    static double prevError = 0;
    static double PID;
    static double PIDOutput;
    static double pi = 3.1415926535897932384626;
    
    public PID(double kP, double kI, double kD){
    	this.kP = kP;
    	this.kI = kI;
    	this.kD = kD;
    }
    
    public static double getError(double current, double setpoint){
    	return setpoint - current;
    }
    
    public static double calculatePID(double current, double setpoint) {
    	
        error = setpoint - current;
        sumError = sumError + error;
        changeError = (error-prevError);
        P = kP * error;
        I = sumError * kI;
        D = kD * changeError;
        PID = P + I + D;
        prevError = error;
        System.out.println("Error" + error);
        if (PID > 1)
        	PID = 1;
        else if (PID < -1)
        	PID = -1;
        return PID;
    }

}


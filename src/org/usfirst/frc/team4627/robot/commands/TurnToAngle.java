package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
public class TurnToAngle extends Command {
	
		public double angleWanted, speed, threshold;
		public boolean isDone, isDefaultAuto, isNetworkTraining;
		
	    public TurnToAngle(double wantedAngle, double speed, double i) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.threshold = i;
	    	this.angleWanted = wantedAngle;
	    	this.isDefaultAuto = false;
	    	this.isNetworkTraining = false;
	    	requires(Robot.driveTrain);
	    }
	    
	    
	    /*public TurnToAngle(double wantedAngle, double speed, double threshold, boolean planB) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.isDone = false;
	    	String fmsData = DriverStation.getInstance().getGameSpecificMessage();
	    	this.angleWanted = wantedAngle;
	    	if(planB) {
	    		if(fmsData.charAt(0) == 'R') {
	    			this.angleWanted += 90;
	    		}
	    	} else {
	    		if(fmsData.charAt(0) == 'L') {
	    			this.angleWanted -= 90;
		    	}
	    	}
	    	requires(Robot.driveTrain);
	    }*/
	    
	    public TurnToAngle(boolean isDefaultAuto, double wantedAngle, double speed, double threshold) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	if(!isDefaultAuto) {
	    		new TurnToAngle(wantedAngle, speed, threshold);
	    	}else{
	    	
	    	this.speed = speed;
	    	this.angleWanted = wantedAngle;
	    	this.threshold = threshold;
	    	this.isDefaultAuto = isDefaultAuto;
	    	this.isNetworkTraining = false;
	    		
	    	requires(Robot.driveTrain);
	    	}
	    }
	    
	    public TurnToAngle(double wantedAngle, double speed, double threshold, boolean isNetworkTraining) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	if(!isDefaultAuto) {
	    		new TurnToAngle(wantedAngle, speed, threshold);
	    	}else{
	    	
	    	this.speed = speed;
	    	this.angleWanted = wantedAngle;
	    	this.threshold = threshold;
	    	this.isDefaultAuto = false;
	    	this.isNetworkTraining = isNetworkTraining;
	    		
	    	requires(Robot.driveTrain);
	    	}
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	String fmsData = DriverStation.getInstance().getGameSpecificMessage();
	    	
		    	if(fmsData.charAt(0) == 'L' && this.isDefaultAuto) {
		    		this.angleWanted *= -1;
		    	}
	    
	    	this.isDone  = false;
	    	Robot.driveTrain.gyro.zeroYaw();
	    	while(Robot.driveTrain.getGyroAngle() < -0.02 || Robot.driveTrain.getGyroAngle() > 0.02) {
	    		
	    	}
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double angle = Robot.driveTrain.getGyroAngle();
	    	System.out.println(angle);
	    	double maxAngle = this.angleWanted + this.threshold;
	    	double minAngle = this.angleWanted - this.threshold;
	    	if(angle < minAngle) {
	    		Robot.driveTrain.setLeftMotor(this.speed);
	    		Robot.driveTrain.setRightMotor(-this.speed);
	    	} else if(angle > maxAngle) {
	   			Robot.driveTrain.setLeftMotor(-this.speed);
	   			Robot.driveTrain.setRightMotor(this.speed);
	   		} else {
	   			Robot.driveTrain.setLeftMotor(0);
	   			Robot.driveTrain.setRightMotor(0);
	   			this.isDone = true; 
	    	}
	    	
	    	/*if(this.angleWanted < 0) {
	    		if(angle > (this.angleWanted)) {
	    			Robot.driveTrain.setLeftMotor(this.speed);
	    			Robot.driveTrain.setRightMotor(this.speed + 0.5);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(0);
	    			Robot.driveTrain.setRightMotor(0);
	    			this.isDone = true;
	    		}
	    	} else if(this.angleWanted > 0) {
	    		if(angle < (this.angleWanted)) {
	    			Robot.driveTrain.setLeftMotor(this.speed + 0.5);
	    			Robot.driveTrain.setRightMotor(this.speed);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(0);
	    			Robot.driveTrain.setRightMotor(0);
	    			this.isDone = true;
	    			
	    		}
	    	} else {
	    		this.isDone = true;
	    	}*/
	    	
	    }
	    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return this.isDone;
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.driveTrain.setLeftMotor(0);
	    	Robot.driveTrain.setRightMotor(0);
	    }

	    // Called when another command which requires one or more of the same
	    //whoever finds this code...Good Job! :D
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }
	}

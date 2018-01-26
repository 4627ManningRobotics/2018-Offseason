package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
public class TurnToAngle extends Command {
	
		public double angleWanted, speed, threshold, startAngle;
		public boolean isDone;
		
	    public TurnToAngle(double wantedAngle, double speed, double threshold) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.isDone = false;
	    	this.threshold = threshold;
	    	this.angleWanted = wantedAngle;
	    	requires(Robot.driveTrain);
	    }
	    
	    
	    public TurnToAngle(double wantedAngle, double speed, double threshold, boolean planB) {
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
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	this.startAngle = Robot.driveTrain.gyro.getAngle();
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double angle = Robot.driveTrain.gyro.getAngle();
	    	System.out.println(angle);
	    	if(this.angleWanted > 0) {
	    		if(angle < (this.angleWanted + this.startAngle)) {
	    			Robot.driveTrain.setLeftMotor(this.speed);
	    			Robot.driveTrain.setRightMotor(-this.speed);
	    		} else {
	    			this.isDone = true;
	    		}
	    	} else {
	    		if(angle > (this.angleWanted - this.startAngle)) {
	    			Robot.driveTrain.setLeftMotor(-this.speed);
	    			Robot.driveTrain.setRightMotor(this.speed);
	    		} else {
	    			this.isDone = true;
	    		}
	    	}
	    }
	    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return this.isDone;
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.driveTrain.setLeftMotor(0);
	    	Robot.driveTrain.setRightMotor(0);
	    	this.isDone = false;
	    }

	    // Called when another command which requires one or more of the same
	    //whoever finds this line...Good Job! :D
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	this.end();
	    }
	}




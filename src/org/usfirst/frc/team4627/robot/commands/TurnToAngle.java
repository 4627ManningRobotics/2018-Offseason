package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
public class TurnToAngle extends Command {
	
		public double angleWanted;
		public double speed;
		public boolean isDone;
	    public TurnToAngle(double wantedAngle, double speed) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.isDone = false;
	    	String fmsData = DriverStation.getInstance().getGameSpecificMessage();
	    	this.angleWanted = wantedAngle;
	    	if(fmsData.charAt(0) == 'L') {
	    		this.angleWanted *= -1;
	    	}
	    	requires(Robot.driveTrain);
	    }
	    
	    public TurnToAngle(double wantedAngle, double speed, boolean planB) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.isDone = false;
	    	String fmsData = DriverStation.getInstance().getGameSpecificMessage();
	    	this.angleWanted = wantedAngle;
	    	if(planB) {
	    		if(fmsData.charAt(0) == 'R') {
	    			this.angleWanted += 80;
	    		}
	    	} else {
	    		if(fmsData.charAt(0) == 'L') {
		    		this.angleWanted -= 80;
		    	}
	    	}
	    	requires(Robot.driveTrain);
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	Robot.driveTrain.gyro.reset();
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double angle = Robot.driveTrain.getGyroAngle();
	    	//String gameData = DriverStation.getInstance().getGameSpecificMessage();
	    	System.out.println(angle);
	    	if(this.angleWanted < 0) {
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
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }
	}

package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
    
	private boolean isDone;
	private double speed, startDistance, distance;
	private String setSavePath;
	
    public DriveDistance(double speed, double distance) {
    	this.isDone = false;
    	this.speed = speed;
    	this.distance = distance;
    	requires(Robot.driveTrain);
    }
    
    public DriveDistance(double speed, double distance, String filePath) {
    	this.isDone = false;
    	this.speed = speed;
    	this.distance = distance;
    	this.setSavePath = filePath;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.startDistance = Robot.driveTrain.getDistance();
    	Robot.driveTrain.setLeftMotor(this.speed);
    	Robot.driveTrain.setRightMotor(this.speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.setSavePath == null) {
    		if(this.startDistance + this.startDistance <= Robot.driveTrain.getDistance()) {
    			this.isDone = true;
    		}
    	}else {
    		if(this.startDistance + this.startDistance <= Robot.driveTrain.getDistance()) {
    	    	Robot.driveTrain.setLeftMotor(0);
    	    	Robot.driveTrain.setRightMotor(0);
    			setTimeout(0.75); // wait to stop
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(this.setSavePath == null) {
        	return this.isDone;
        }else {
        	return isTimedOut();
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftMotor(0);
    	Robot.driveTrain.setRightMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

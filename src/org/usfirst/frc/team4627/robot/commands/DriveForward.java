package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	private double leftM_speed, rightM_speed, m_time, distance;
	private double lastDistance, newDistance;
	private boolean isDone;
    /*public DriveForward(double leftSpeed, double rightSpeed, double time) {
    	this.leftM_speed = leftSpeed;
    	this.rightM_speed = rightSpeed;
    	this.m_time=time;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }*/
    
    public DriveForward(double leftSpeed, double rightSpeed, double distance) {
    	this.m_time = distance;
    	this.distance = distance;
    	this.leftM_speed = leftSpeed;
    	this.rightM_speed = rightSpeed;
    	super.requires(Robot.driveTrain);
    }
    
    public DriveForward(double speed, double distance) {
    	this.m_time = distance;
    	this.distance = distance;
    	this.leftM_speed = speed;
    	this.rightM_speed = speed;
    	super.requires(Robot.driveTrain);
    }
    
    /*public DriveForward(double leftSpeed, double rightSpeed, double time, boolean PlanB) {
    	this.m_time=time;
    	String fmsData = DriverStation.getInstance().getGameSpecificMessage();
    	if (PlanB) {
    		if(fmsData.charAt(0) == 'L') {
    			this.leftM_speed = 0;
    			this.rightM_speed = 0;
    		} else if(fmsData.charAt(0) == 'R') {
    			this.leftM_speed = leftSpeed;
    	    	this.rightM_speed = rightSpeed;
    		}
    	}
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }*/
    
    public DriveForward(double leftSpeed, double rightSpeed, double distance, boolean PlanB) {
    	this.isDone = false;
    	this.distance = distance;
    	if (PlanB) {
    		if(RobotMap.FMSData.charAt(0) == 'L') {
    			this.leftM_speed = 0;
    			this.rightM_speed = 0;
    		} else if(RobotMap.FMSData.charAt(0) == 'R') {
    			this.leftM_speed = leftSpeed;
    	    	this.rightM_speed = rightSpeed;
    		}
    	}
        super.requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.isEncoderChassis) {
    		super.setTimeout(1);
    	}else{
    		super.setTimeout(m_time);
    	}
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.initEncoders();
    	Robot.driveTrain.setLeftMotor(this.leftM_speed);
    	Robot.driveTrain.setRightMotor(this.rightM_speed);
    	
    	this.newDistance = Robot.driveTrain.getDistance();
    	this.lastDistance = Robot.driveTrain.getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.isEncoderChassis) {
    		//System.out.println(Robot.driveTrain.getRightPulse());
    		if(Robot.driveTrain.getDistance() > this.distance) {
    			Robot.driveTrain.setLeftMotor(0);
    			Robot.driveTrain.setRightMotor(0);
    			this.isDone = true;
    		}
    		this.lastDistance = this.newDistance;
    		this.newDistance = Robot.driveTrain.getDistance();
    		
    		if(this.lastDistance != this.newDistance) {
    			super.setTimeout(1);
    		}
    		
    	}
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.isEncoderChassis) {
    		return this.isDone || super.isTimedOut();
    	}else{
    		return super.isTimedOut();
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
    	end();
    }
}

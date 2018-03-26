package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForwardFMS extends Command {

	public static final double leftDefaultTimeout = 2.2, rightDefaultTimeout = 2.0; // these are also the timed values for auto if the encoder is broken
	private double leftM_speed, rightM_speed, m_time, distance, offset;
	private double lastDistance, newDistance;
	private boolean isDone;
    /*public DriveForward(double leftSpeed, double rightSpeed, double time) {
    	this.leftM_speed = leftSpeed;
    	this.rightM_speed = rightSpeed;
    	this.m_time=time;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }*/
    
    public DriveForwardFMS(double leftSpeed, double rightSpeed, double distance, double offset) {
    	this.m_time = distance;
    	this.distance = distance;
    	this.leftM_speed = leftSpeed;
    	this.rightM_speed = rightSpeed;
    	this.offset = offset;
    	requires(Robot.driveTrain);
    }
    
    public DriveForwardFMS(double speed, double distance, double offset) {
    	this.m_time = distance;
    	this.distance = distance;
    	this.leftM_speed = speed;
    	this.rightM_speed = speed;
    	this.offset = offset;
    	requires(Robot.driveTrain);
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
    }
    
    public DriveForwardFMS(double leftSpeed, double rightSpeed, double distance, boolean PlanB) {
    	this.isDone = false;
    	this.distance = distance;
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

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.isEncoderChassis) {
    		if(RobotMap.FMSData.charAt(0) == 'L') {
        		super.setTimeout(DriveForwardFMS.leftDefaultTimeout);
        	}else {
        		super.setTimeout(DriveForwardFMS.rightDefaultTimeout);
        	}
    		
    	}else{
    		super.setTimeout(m_time);
    	}
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.initEncoders();
    	Robot.driveTrain.setLeftMotor(this.leftM_speed);
    	Robot.driveTrain.setRightMotor(this.rightM_speed);
    	
    	this.newDistance = Robot.driveTrain.getDistance();
    	this.lastDistance = Robot.driveTrain.getDistance();

    	if(RobotMap.FMSData.charAt(0) == 'L') {
    		this.distance += this.offset;
    	}else {
    		this.distance -= this.offset;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.isEncoderChassis) {
    		System.out.println(Robot.driveTrain.getDistance());
    		if(Robot.driveTrain.getDistance() > this.distance) {
    			Robot.driveTrain.setLeftMotor(0);
    			Robot.driveTrain.setRightMotor(0);
    			this.isDone = true;
    		}
    		this.lastDistance = this.newDistance;
    		this.newDistance = Robot.driveTrain.getDistance();
    		
    		if(this.lastDistance != this.newDistance) {
    			String fmsData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
        		if(fmsData.charAt(0) == 'L') {
            		super.setTimeout(DriveForwardFMS.leftDefaultTimeout);
            	}else {
            		super.setTimeout(DriveForwardFMS.rightDefaultTimeout);
            	}
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

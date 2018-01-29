package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	double leftM_speed, rightM_speed, m_time, distance;
	boolean isDone;
    
    public DriveForward(double leftSpeed, double rightSpeed, double distance) {
    	this.isDone = false;
    	this.leftM_speed = leftSpeed;
    	this.rightM_speed = rightSpeed;
    	this.distance = distance;
    	//this.m_time = time;
    	//setTimeout(time);
    	requires(Robot.driveTrain);
    }
    
    public DriveForward(double leftSpeed, double rightSpeed, double distance, boolean PlanB) {
    	this.isDone = false;
    	//this.m_time = time;
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
    	//setTimeout(this.m_time);
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(m_time);
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.initEncoders();
    	Robot.driveTrain.setLeftMotor(this.leftM_speed);
    	Robot.driveTrain.setRightMotor(this.rightM_speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println(Robot.driveTrain.getDistance());
    	System.out.println(DriveTrain.leftEncoder.getDistance());
    	if(Robot.driveTrain.getDistance() > this.distance) {
    		Robot.driveTrain.setLeftMotor(0);
    		Robot.driveTrain.setRightMotor(0);
    		this.isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isDone;
    	//return isTimedOut();
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

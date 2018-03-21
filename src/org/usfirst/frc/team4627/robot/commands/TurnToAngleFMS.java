package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
public class TurnToAngleFMS extends Command {
	
		public double angleWanted, speed, threshold, offset;
		public boolean isDone, isDefaultAuto, isNetworkTraining;
		
	    public TurnToAngleFMS(double wantedAngle, double speed, double i) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
		   
	    	this.speed = speed;
	    	this.threshold = i;
	    	this.angleWanted = wantedAngle;
	    	this.offset = 0;
	    	this.isDefaultAuto = true;
	    	this.isNetworkTraining = false;
	    	requires(Robot.driveTrain);
	    }
	    
	    public TurnToAngleFMS(double wantedAngle, double speed, double speedSideOffset, double i) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
		   
	    	this.speed = speed;
	    	this.threshold = i;
	    	this.angleWanted = wantedAngle;
	    	this.offset = speedSideOffset; // is only ever used to subtract from one side
	    	this.isDefaultAuto = true;
	    	this.isNetworkTraining = false;
	    	requires(Robot.driveTrain);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {

	    	Robot.driveTrain.setLeftMotor(0);
	    	Robot.driveTrain.setRightMotor(0);
	    	if(RobotMap.FMSData.charAt(0) == 'L') {
	    		this.angleWanted *= -1;
	    	}
	    	this.isDone  = false;
	    	Sensors.gyro.zeroYaw();
	    	while(Robot.sensors.getGyroAngle() < -0.02 || Robot.sensors.getGyroAngle() > 0.02) {
	    		
	    	}
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double angle = Robot.sensors.getGyroAngle();
	    		double maxAngle = this.angleWanted + this.threshold;
	    		double minAngle = this.angleWanted - this.threshold;
	    		if(angle < minAngle) {
	    			Robot.driveTrain.setLeftMotor(this.speed);
	    			Robot.driveTrain.setRightMotor(-this.speed - this.offset);
	    		} else if(angle > maxAngle) {
	   				Robot.driveTrain.setLeftMotor(-this.speed - this.offset);
	   				Robot.driveTrain.setRightMotor(this.speed);
	   			} else {
	   				Robot.driveTrain.setLeftMotor(0);
	   				Robot.driveTrain.setRightMotor(0);
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
	    //whoever finds this code...Good Job! :D
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    	end();
	    }
	}

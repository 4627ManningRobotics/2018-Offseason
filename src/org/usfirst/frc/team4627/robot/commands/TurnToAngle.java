package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Scanner;
public class TurnToAngle extends Command {
	
		public double angleWanted;
		public double threshHold;
		public double speed;
	    public TurnToAngle(double wantedAngle, double speed, double threshHold) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	this.speed = speed;
	    	this.angleWanted = wantedAngle;
	    	this.threshHold = threshHold;
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
	    	double minAngle = this.angleWanted - this.threshHold;
	    	double maxAngle = this.angleWanted + this.threshHold;
	    	System.out.println(angle);
	    	if(this.angleWanted < 0) {
	    		System.out.println("Running Left Code");
	    		if(angle > (maxAngle)) {
	    			Robot.driveTrain.setLeftMotor(-this.speed);
	    			Robot.driveTrain.setRightMotor(this.speed);
	    		} else if(maxAngle > angle  || angle < minAngle) { //Broken Here
	    			Robot.driveTrain.setLeftMotor(0);
	    			Robot.driveTrain.setRightMotor(0);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(this.speed);
	    			Robot.driveTrain.setRightMotor(-this.speed);
	    		}
	    	} else if(this.angleWanted > 0) {
	    		System.out.println("Running Right Code");
	    		if(angle < (maxAngle)) {
	    			Robot.driveTrain.setLeftMotor(this.speed);
	    			Robot.driveTrain.setRightMotor(-this.speed);
	    		} else if(angle < maxAngle || angle > minAngle) { //Broken Here
	    			System.out.println("hi");
	    			Robot.driveTrain.setLeftMotor(0);
	    			Robot.driveTrain.setRightMotor(0);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(-this.speed);
	    			Robot.driveTrain.setRightMotor(this.speed);
	    		}
	    	}
	    	
	    }
	    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return isTimedOut();
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

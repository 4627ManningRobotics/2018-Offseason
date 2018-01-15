package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.Scanner;
public class Autonomous extends Command {
	

	    public Autonomous() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	requires(Robot.driveTrain);
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	Robot.driveTrain.gyro.reset();
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	double angle = Robot.driveTrain.getGyroAngle();
	    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	    	System.out.println(angle);
	    	if(gameData.charAt(0) == 'L') {
	    		if (angle > -40) {
	    			Robot.driveTrain.setLeftMotor(-0.35);
	    			Robot.driveTrain.setRightMotor(0.35);
	    		} else if(angle < -40 && angle > -50) {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setRightMotor(0.4);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(0.35);
	    			Robot.driveTrain.setRightMotor(-0.35);
	    		}
	    	} else {
	    		if (angle < 40) {
	    			Robot.driveTrain.setLeftMotor(0.35);
	    			Robot.driveTrain.setRightMotor(-0.35);
	    		} else if(angle > 40 && angle < 50) {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setLeftMotor(0.4);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(-0.35);
	    			Robot.driveTrain.setRightMotor(0.35);
	    		}
	    	}
	    	
	    }
	    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	if(timeSinceInitialized() >= 4) {
	    		return true;
	    	} else if(Robot.oi.getControllerBButton()) {
	    		return true;
	    	} else {
	    		return false;
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

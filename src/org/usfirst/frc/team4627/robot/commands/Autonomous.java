package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import java.util.Scanner;
public class Autonomous extends Command {
	

	    public Autonomous() {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
	    	requires(Robot.driveTrain);
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	//double angle = Robot.driveTrain.getGyroAngle();
	    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
	    	if(gameData.charAt(0) == 'L') {
	    		Robot.driveTrain.setLeftMotor(-0.4);
    			Robot.driveTrain.setRightMotor(0.4);
	    		/*if (angle < 45) {
	    			Robot.driveTrain.setLeftMotor(-0.4);
	    			Robot.driveTrain.setRightMotor(0.4);
	    		} else if(angle == 45) {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setLeftMotor(0.4);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setRightMotor(-0.4);
	    		}*/
	    	} else {
	    		Robot.driveTrain.setLeftMotor(0.4);
    			Robot.driveTrain.setRightMotor(-0.4);
	    		/*if (angle > 315) {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setRightMotor(-0.4);
	    		} else if(angle == 315) {
	    			Robot.driveTrain.setLeftMotor(0.4);
	    			Robot.driveTrain.setLeftMotor(0.4);
	    		} else {
	    			Robot.driveTrain.setLeftMotor(-0.4);
	    			Robot.driveTrain.setRightMotor(0.4);
	    		}*/
	    	}
	    	
	    }
	    

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	if(timeSinceInitialized() >= 6) {
	    		return true;
	    	} else if(Robot.oi.getControllerBButton()) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems is scheduled to run
	    protected void interrupted() {
	    }
	}

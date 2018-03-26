package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.subsystems.Sensors;

import edu.wpi.first.wpilibj.command.Command;
public class TurnToAngleTimeFMS extends Command {
	
		public double turnTime, speed, offset;
		
	    public TurnToAngleTimeFMS(double turnTime, double speed) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
		   
	    	this.speed = speed;
	    	this.turnTime = turnTime;
	    	this.offset = 0;
	    	requires(Robot.driveTrain);
	    }
	    
	    public TurnToAngleTimeFMS(double turnTime, double speed, double speedSideOffset) {
	        // Use requires() here to declare subsystem dependencies
	        // eg. requires(chassis);
		   
	    	this.speed = speed;
	    	this.turnTime = turnTime;
	    	this.offset = speedSideOffset; // is only ever used to subtract from one side.
	    	requires(Robot.driveTrain);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() {
	    	super.setTimeout(this.turnTime);
	    	
	    	if(RobotMap.FMSData.charAt(0) == 'L') {
	    		Robot.driveTrain.setLeftMotor(-this.speed - this.offset);
	    		Robot.driveTrain.setRightMotor(this.speed);
	    	}else {
	    		Robot.driveTrain.setLeftMotor(this.speed);
	    		Robot.driveTrain.setRightMotor(-this.speed - this.offset);
	    	}
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return super.isTimedOut();
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

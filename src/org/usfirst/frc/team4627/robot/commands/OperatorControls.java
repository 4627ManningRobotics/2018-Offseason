package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorControls extends Command {

    public OperatorControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm.clamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.wrist.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		// buttons
    		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_X)) { // open claw
    			Robot.arm.clamp.openClamp();
    		}
    		
    		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_Y)) { // scale position
    			Command c = (Command) new ArmController(RobotMap.SCALE);
    			c.start();
    		}
    		
    		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_B)) { // switch position
    			Command c = (Command) new ArmController(RobotMap.SWITCH);
    			c.start();
    		}
    	
    		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_A)) { // ground position
    			Command c = (Command) new ArmController(RobotMap.GROUND);
    			c.start();
    		}
    	// intake/clamp controls
    	if(Robot.oi.getOperatorButton(RobotMap.RIGHT_BUMPER)) {
    		Robot.arm.clamp.setLeftMotor(RobotMap.CLAMP_MAX_SPEED);
    		Robot.arm.clamp.setRightMotor(RobotMap.CLAMP_MAX_SPEED);
    	} else if(Robot.oi.getOperatorButton(RobotMap.LEFT_BUMPER)) {
    		Robot.arm.clamp.setLeftMotor(-RobotMap.CLAMP_MAX_SPEED);
    		Robot.arm.clamp.setRightMotor(-RobotMap.CLAMP_MAX_SPEED);
    	} else {
    		double triggerVal = Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER);
    		double stick = Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
        	Robot.arm.clamp.setLeftMotor((triggerVal + stick) * RobotMap.CLAMP_MAX_SPEED);
        	Robot.arm.clamp.setRightMotor((triggerVal - stick) * RobotMap.CLAMP_MAX_SPEED);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

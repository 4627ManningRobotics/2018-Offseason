package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the arm setpoint and ends immediately
 */

public class SetArm extends Command {
	
	double m_setpoint;
	boolean wDead1;
	
    public SetArm(double setpoint) {
    	m_setpoint = setpoint;
        requires(Robot.rightArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.rightArm.setSetpoint(m_setpoint);
    	this.wDead1 = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.wrist.onTarget() && !this.wDead1) {
    		this.wDead1 = true;
    	}
    	if(this.isInDeadzone()) {
    		Robot.rightArm.setSetpointRelative(0); // if in dead zone stop moving
    	}else if(this.wDead1){
    		Robot.rightArm.setSetpoint(this.m_setpoint);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private boolean isInDeadzone() {
    	return Robot.rightArm.getPosition() > RobotMap.ARMS_DEADZONE_MIN && Robot.rightArm.getPosition() < RobotMap.ARMS_DEADZONE_MAX;
    }
}

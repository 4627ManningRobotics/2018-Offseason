package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the belts with the triggers and the left stick X
 * 
 * Is the default command for Clamp
 */
public class BeltControls extends Command {

    public BeltControls() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double triggerVal = Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER);
		double stick = Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
    	Robot.clamp.setLeftMotor((triggerVal + stick) * RobotMap.CLAMP_MAX_SPEED);
    	Robot.clamp.setRightMotor((triggerVal - stick) * RobotMap.CLAMP_MAX_SPEED);
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

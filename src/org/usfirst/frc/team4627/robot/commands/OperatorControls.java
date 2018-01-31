package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorControls extends Command {

    public OperatorControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.clamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.clamp.setLeftMotor(Robot.oi.getOperatorRightTrigger() - Robot.oi.getOperatorLeftTrigger() + Robot.oi.getOperatorLeftStickX());
    	Robot.clamp.setRightMotor(Robot.oi.getOperatorRightTrigger() - Robot.oi.getOperatorLeftTrigger() - Robot.oi.getOperatorLeftStickX());
    	if(Robot.oi.getOperatorRightBumper()) {
    		Robot.clamp.setLiftingMotor(0.5);
    	} else if(Robot.oi.getOperatorLeftBumper()) {
    		Robot.clamp.setLiftingMotor(-0.5);
    	} else {
    		Robot.clamp.setLiftingMotor(0);
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

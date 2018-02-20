package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmHeights extends Command {
	
	private double targetHeight = 0;
	private double startHeight = 0;
	
    public ArmHeights(double height) {
        requires(Robot.arm);
        this.startHeight = Robot.arm.getHeight();
        this.targetHeight = height;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.enable();
    	Robot.arm.setSetpoint(this.targetHeight);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// move the wrist so it can't move outside the boundaries
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.isOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

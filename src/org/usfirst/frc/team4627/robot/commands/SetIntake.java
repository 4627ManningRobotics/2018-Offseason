package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntake extends Command {

    private double inVal;
	
	public SetIntake(double intakeValue) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.clamp);
		this.inVal = intakeValue;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.clamp.setLeftMotor(this.inVal);
    	Robot.clamp.setRightMotor(this.inVal);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
}

package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetWristCurrent extends Command {

	private double C;
	
    public SetWristCurrent(double current) {
        // Use requires() here to declare subsystem dependencies
        super.requires(Robot.wrist);
        this.C = current;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wrist.disable();
    	Robot.wrist.setCurrent(this.C);
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

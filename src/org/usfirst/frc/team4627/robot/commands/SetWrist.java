package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
//Sets the wrist setpoint and ends immediately

public class SetWrist extends Command {
	double m_setpoint;

    public SetWrist(double setpoint) {
    	m_setpoint = setpoint;
        requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wrist.setSetpoint(m_setpoint);
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

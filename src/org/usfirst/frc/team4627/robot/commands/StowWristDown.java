package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/** 
 * Stows the wrist in the down position, ends when stowed
 */
public class StowWristDown extends Command {

    public StowWristDown() {
        requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wrist.setSetpoint(RobotMap.WRIST_DOWN_STOW);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return Robot.wrist.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Print extends Command {

    public Print() { // entirely for all system prints
        // Use requires() here to declare subsystem dependencies
        requires(Robot.sensors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long i = 0;
    	if(i % 100 == 0) { // reduce amount of total prints, easier to read feed
    		//put prints here
    		System.out.println("right: " + Robot.driveTrain.getRightPulse() + " left: " + Robot.driveTrain.getLeftPulse());
    		System.out.println("Angle: " + Robot.wrist.calculateAngle() + " Arm: " + Robot.rightArm.calculatePosition());
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

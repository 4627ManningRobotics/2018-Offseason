package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NNDistanceTraining extends Command {
	
	private double speed;
	
    public NNDistanceTraining(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(!RobotMap.isEncoderChassis) { // we have to make sure DriveForward is time based
    		System.out.println(RobotMap.TIMES[RobotMap.timesIndex]);
    		RobotMap.DISTANCE_NETWORK.addData(new double[]{this.speed, 0.0000000000001}, new double[] {RobotMap.TIMES[RobotMap.timesIndex]});
    		Command c = (Command) new DriveForward(this.speed, RobotMap.TIMES[RobotMap.timesIndex]);
    		c.start();
    	}
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

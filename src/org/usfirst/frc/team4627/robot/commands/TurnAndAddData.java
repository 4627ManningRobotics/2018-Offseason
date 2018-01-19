package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import fullyconnectednetwork.NN;

/**
 *
 */
public class TurnAndAddData extends Command {
	
	private boolean isLeft;
	private int time;
	
    public TurnAndAddData(int time, boolean isLeft) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.isLeft =  isLeft;
    	this.time = time;
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(this.time);
    	if(this.isLeft) {
    		Robot.driveTrain.setLeftMotor(-0.98);
    		Robot.driveTrain.setRightMotor(0.98);
    	}else {
        	Robot.driveTrain.setLeftMotor(0.98);
        	Robot.driveTrain.setRightMotor(-0.98);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	NN.addTrainDataToFile(new double[] {50.0}, new double[] {0.475}, "C:\\Users\\Lucas Brown\\Documents\\GitHub\\2018-Offseason\\src\\NeuralNetworks\\frictionSetSave1.txt");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

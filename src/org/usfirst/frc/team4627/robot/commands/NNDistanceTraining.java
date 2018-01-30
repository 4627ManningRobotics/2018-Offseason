package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import fullyconnectednetwork.NN;

/**
 *
 */
public class NNDistanceTraining extends Command {

	private int startIndex;
	private NN theNet;
	private boolean done;
	
    public NNDistanceTraining(int startIndex) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.startIndex = startIndex;
    	this.done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.theNet = new NN(2,2,1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			this.theNet.saveNet("/home/lvuser/Saves/distanceNetSaveTest.txt"); // create the network save
			this.theNet.saveSet("/home/lvuser/Saves/distanceSetSaveTest.txt"); // and the set file
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(RobotMap.distanceIndex = this.startIndex; RobotMap.distanceIndex < RobotMap.DISTANCES.length; RobotMap.distanceIndex++) {
    		for(double speed = 0.2; speed <= 1; speed += 0.2) {
    			while(!Robot.oi.isButtonPressed(RobotMap.BUTTON_B)) { // wait for human confirmation
    				
    			}
    			new DriveDistance(speed,RobotMap.DISTANCES[RobotMap.distanceIndex], "/home/lvuser/Saves/distanceSetSaveTest.txt");
    		}
    	}
    	this.done = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

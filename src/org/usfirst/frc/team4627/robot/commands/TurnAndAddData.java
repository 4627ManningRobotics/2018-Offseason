package org.usfirst.frc.team4627.robot.commands;

import java.io.File;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import fullyconnectednetwork.NN;

/**
 *
 */
public class TurnAndAddData extends Command {
	
	public NN theNet;
	public boolean isFin;
	
    public TurnAndAddData() {
    	this.isFin = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.theNet = new NN(2,3,1);
    	int timeInterval = 300; // time interval in milliseconds, 3/10 seconds
    	double[][] in = new double[180][2]; // 36-5 degree intervals * 5 speeds
    	double[] out = new double[180];
    	
    	for(int speed = 1; speed <= 5; speed++) {
    		for(int degree = 1; degree <= 36; degree++) {
    			in[speed * 36 - (36 - degree)] = new double[] {degree, speed};
    			new TurnToAngle(degree, speed, 0);
    			long time = System.currentTimeMillis();
    			while(time + timeInterval > System.currentTimeMillis()) {
    				//wait
    			}
    		out[speed * 36 - (36 - degree)] = Robot.driveTrain.getGyroAngle();
    		}
    	}
    		
    	for(int i = 0; i < out.length; i++) {
    		try {
				this.theNet.saveNet("/home/lvuser/Saves/turnNetSaveTest.txt");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//create a network save for later
    		NN.addTrainDataToFile(in[i], new double[] {out[i]}, "/home/lvuser/Saves/turnSetSaveTest.txt");
    	}
    	
    	this.isFin = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isFin;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    
    }
}

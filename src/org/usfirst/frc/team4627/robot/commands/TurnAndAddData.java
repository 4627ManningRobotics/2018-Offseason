package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import fullyconnectednetwork.NN;
import fullyconnectednetwork.Network;

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
    	this.theNet = new NN(Network.ZERO_TO_ONE, 180.0, new int[] {2,3,1});
		try {
			this.theNet.saveNet("/home/lvuser/turnNetSaveTest.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//create a network save for later
    	int timeInterval = 300; // time interval in milliseconds, 3/10 of a seconds
    	double[][] in = new double[180][2]; // 36-5 degree intervals * 5 speeds
    	double[] out = new double[180];
    	
    	for(int speed = 1; speed <= 5; speed++) {
    		for(int degree = 1; degree <= 36; degree++) {
    			
    			int index = (speed - 1) * 36 + (degree - 1);
    			
    			new TurnToAngle(degree * 5, speed / 5, 2.5);
    			long time = System.currentTimeMillis();
    			while(time + timeInterval > System.currentTimeMillis()) {
    				//wait
    			}
    			in[index] = new double[] {Robot.driveTrain.getGyroAngle(), speed / 5};
    			out[index] = degree * 5;
    			NN.addTrainDataToFile(in[index], new double[] {out[index]}, "/home/lvuser/turnSetSaveTest.txt");
    		}
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

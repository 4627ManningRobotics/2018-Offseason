package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {

    public Auto() {
    	addParallel(new GoToSwitch()); // move the arm/wrist as the robot moves
    	
    	addSequential(new DriveForward(0.25, 0.25, 4)); //1s
    	addSequential(new TurnToAngleFMS(43, RobotMap.TURN_SPEED, 2));
    	addSequential(new DriveForward(0.25, 0.25, 76.5)); //1s
    	addSequential(new TurnToAngleFMS(-43, RobotMap.TURN_SPEED, 2));
    	//addSequential(new WaitForArmWrist()); // make sure the GoToSwitch has finished
    	addSequential(new DriveForward(0.3, 0.3, 74)); //1.2s
    	
    	addSequential(new Wait(0.5)); // wait for the robot to stop
    	addSequential(new ReleaseBox());    
    	addSequential(new TurnToAngleFMS(-10, RobotMap.TURN_SPEED, 2));
    	
    }
}
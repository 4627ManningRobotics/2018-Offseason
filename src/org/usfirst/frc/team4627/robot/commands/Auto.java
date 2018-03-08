package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {

    public Auto() {
    	addParallel(new GoToSwitch()); // move the arm/wrist as the robot moves
    	
    	addSequential(new DriveForward(0.2, 0.2, 1));
    	addSequential(new TurnToAngleFMS(40, 0.2, 2));
    	addSequential(new DriveForward(0.4, 0.4, 1));
    	addSequential(new TurnToAngleFMS(-50, 0.2, 2));
    	addSequential(new WaitForArmWrist()); // make sure the GoToSwitch has finished
    	addSequential(new DriveForward(0.3, 0.3, 1.1)); //1.47
    	
    	addSequential(new ReleaseBox());    
    	
    }
}
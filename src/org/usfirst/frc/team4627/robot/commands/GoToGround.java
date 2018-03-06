package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class GoToGround extends CommandGroup {

    public GoToGround() {
    	
    	addParallel(new StowWristUp());
    	addParallel(new SetArm( RobotMap.ARMS_GROUND )); 	//Set the arms to scale position
    	addSequential(new WaitForDZLow());				
    	addSequential(new SetWrist( RobotMap.WRIST_GROUND ));//Only start setting the wrist when we are passed the deadzone
    }
}

package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleOpGoToGround extends CommandGroup {

    public TeleOpGoToGround() {
    	//logic gets evaluated when instance of the command group is made
    	//so this group should only be used on a button press in order for this IF to work
    	
    	if(Robot.rightArm.calculatePosition() > RobotMap.ARMS_DEADZONE_MAX) {   //if we are above the deadzone
    		addParallel(new SetArm( RobotMap.ARMS_DEADZONE_MAX ));           	//get the arms to the top of the deadzone
    		addSequential(new StowWristUp());									//while stowing the wrist
    	}
    	
    	addParallel(new SetArm( RobotMap.ARMS_GROUND )); 	//Set the arms to scale position
    	addSequential(new WaitForDZLow());				
    	addSequential(new SetWrist( RobotMap.WRIST_GROUND ));//Only start setting the wrist when we are passed the deadzone
    }
}

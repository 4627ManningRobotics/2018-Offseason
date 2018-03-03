package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleOpGoToScale extends CommandGroup {

    public TeleOpGoToScale() {
    	//logic gets evaluated when instance of the command group is made
    	//so this group should only be used on a button press in order for this IF to work
    	
    	if(Robot.rightArm.calculatePosition() < RobotMap.ARMS_DEADZONE_MIN) {   //if we are below the deadzone
    		addParallel(new SetArm( RobotMap.ARMS_DEADZONE_MIN ));           	//get the arms to the bottom of the deadzone
    		addSequential(new StowWristUp());									//while stowing the wrist
    	}
    	
    	addParallel(new SetArm( RobotMap.ARMS_SCALE )); 	//Set the arms to scale position
    	addSequential(new WaitForDZHigh());				
    	addSequential(new SetWrist( RobotMap.WRIST_SCALE ));//Only start setting the wrist when we are passed the deadzone
    }
}

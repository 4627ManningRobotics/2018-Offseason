package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * ONLY USE THIS FROM A BUTTON
 */
public class TeleOpGoToSwitch extends CommandGroup {

    public TeleOpGoToSwitch() {
    	//logic gets evaluated when instance of the command group is made
    	//so this group should only be used on a button press in order for this IF to work
    	
    	if(Robot.rightArm.calculatePosition() > RobotMap.ARMS_DEADZONE_MAX) {   //if we are above the deadzone
    		addParallel(new SetArm( RobotMap.ARMS_DEADZONE_MAX ));           	//get the arms to the top of the deadzone
    		addSequential(new StowWristDown());									//while stowing the wrist
    	}
    	
    	addParallel(new SetArm( RobotMap.ARMS_SWITCH )); 	//Set the arms to scale position
    	addSequential(new WaitForDZLow());				
    	addSequential(new SetWrist( RobotMap.WRIST_SWITCH ));//Only start setting the wrist when we are passed the deadzone
    }
}

package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class GoToSwitch extends CommandGroup {

    public GoToSwitch() {
    	addParallel(new StowWristDown());									
    	addParallel(new SetArm( RobotMap.ARMS_SWITCH )); 	//Set the arms to scale position
    	addSequential(new WaitForDZLow());				
    	addSequential(new SetWrist( RobotMap.WRIST_SWITCH ));//Only start setting the wrist when we are passed the deadzone
    }
}

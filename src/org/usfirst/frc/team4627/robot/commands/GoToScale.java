package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoToScale extends CommandGroup {

    public GoToScale() {
    	
  		addSequential(new StowWristUp());
    	addParallel(new SetArm( RobotMap.ARMS_SCALE )); 	//Set the arms to scale position
    	addSequential(new WaitForDZHigh());				
    	addSequential(new SetWrist( RobotMap.WRIST_SCALE ));//Only start setting the wrist when we are passed the deadzone
    }
}
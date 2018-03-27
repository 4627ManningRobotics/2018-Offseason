package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class GoToSwitch extends CommandGroup {

    public GoToSwitch() {
    	super.requires(Robot.wrist);
    	super.requires(Robot.rightArm);
    	//addSequential(new SetArm(Robot.rightArm.calculatePosition() + 10)); // move 10 up
    	addParallel(new StowWristDown());									
    	addSequential(new SetArm( RobotMap.ARMS_SWITCH )); 	//Set the arms to scale position
    	//addSequential(new WaitForDZLow());				
    	addSequential(new SetWrist( RobotMap.WRIST_SWITCH ));//Only start setting the wrist when we are passed the deadzone
    }
}

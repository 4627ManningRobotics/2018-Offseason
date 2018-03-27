package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class GoToGround extends CommandGroup {

    public GoToGround() {
    	super.requires(Robot.wrist);
    	super.requires(Robot.rightArm);
    	
    	addParallel(new StowWristDown());
    	addSequential(new Wait(1));
    	addSequential(new SetArm( RobotMap.ARMS_DEADZONE_MIN - 15)); 	//Set the arms to scale position
    	addSequential(new SetWrist( RobotMap.WRIST_GROUND ));//Only start setting the wrist when we are passed the deadzone
    	addSequential(new SetArm(RobotMap.ARMS_GROUND));
    }
}

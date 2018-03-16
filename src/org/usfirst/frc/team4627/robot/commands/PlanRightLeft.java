package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlanRightLeft extends CommandGroup {

    public PlanRightLeft() {
    	
    	//addParallel(new GoToScale());
    	
    	addSequential(new DriveForward(-0.3, -0.3, 100)); // drive
    	
    	/*
    	addSequential(new TurnToAngle(-90, .9, 3)); // 90 degrees, 90% speed, 3 degree acceptable error 
    	addSequential(new DriveForward(0.9, 0.9, 90)); // drive
    	addSequential(new TurnToAngle(90, .9, 3)); // turn back to forward
    	addSequential(new DriveForward(0.9, 0.9, 20)); // drive
    	addSequential(new TurnToAngle(-90, .9, 3)); // turn away from scale
    	addSequential(new WaitForArmWrist()); // make sure the GoToScale has finished
    	addSequential(new DriveForward(-0.9, -0.9, 5)); // back into scale
    	
    	addSequential(new ReleaseBox());
    	*/
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

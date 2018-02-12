package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlanRight extends CommandGroup {

    public PlanRight() {
<<<<<<< HEAD
    	//addSequential(new DriveForward(0.9, 0.9, 3));
    	addSequential(new DriveForward(0.9, 0.9, 84));
    	addSequential(new TurnToAngle(0, -0.25, 5, false));
    	//addSequential(new DriveForward(0.5, 0.5, 5, false));
    	addSequential(new DriveForward(0.5, 0.5, 72, false));
=======
    	addSequential(new DriveForward(0.9, 0.9, 3));
    	addSequential(new TurnToAngle(0, -0.25, false));
    	addSequential(new DriveForward(0.5, 0.5, 5));
>>>>>>> parent of f33bdb9... Adjusted Plan Left and Right
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

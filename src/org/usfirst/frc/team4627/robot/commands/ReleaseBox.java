package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReleaseBox extends CommandGroup {

    public ReleaseBox() {
    	addSequential(new ClampOpen()); // open clamp
    	addSequential(new SetIntake(1)); // drop box
    	addSequential(new Wait(0.2)); // wait for the box to be released
    	addSequential(new SetIntake(0)); // stop intake
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

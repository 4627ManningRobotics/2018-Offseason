package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {

    public Auto() {
    	addSequential(new DriveForward(0.2, 0.2, 6));
    	addSequential(new TurnToAngle(22, 0.2, 2));
    	addSequential(new DriveForward(0.25, 0.25, 73));
    	addSequential(new TurnToAngle(-22, 0.2, 2));
    	addSequential(new DriveForward(0.25, 0.25, 17));
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

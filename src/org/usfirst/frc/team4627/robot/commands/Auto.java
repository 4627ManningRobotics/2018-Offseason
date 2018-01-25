package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {

    public Auto() {
    	
    	/*addSequential(new DriveForward(0.2, 0.2, 1));
    	//addSequential(new TurnToAngle(35, 0.35, 5));
    	//addSequential(new DriveForward(0.4, 0.4, 1.92));
    	//addSequential(new TurnToAngle(-35, 0.35, 5));
    	//addSequential(new DriveForward(0.4, 0.4, 1));
    	addSequential(new TurnToAngle(35, 0.1));
    	addSequential(new DriveForward(0.4, 0.4, 1.3));
    	addSequential(new TurnToAngle(-36, 0.1));
    	addSequential(new DriveForward(0.17, 0.17, 1));
    	*/
    	addSequential(new DriveForward(0.2, 0.2, 1));
    	addSequential(new TurnToAngle(45, 0.1, 5));
    	addSequential(new DriveForward(0.4, 0.4, 48));
    	addSequential(new TurnToAngle(-45, 0.1, 5));
    	addSequential(new DriveForward(0.17, 0.17, 24));
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

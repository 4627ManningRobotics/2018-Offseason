package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto extends CommandGroup {

    public Auto() {
<<<<<<< HEAD
<<<<<<< HEAD
    	addSequential(new DriveForward(0.2, 0.2, 1));
    	addSequential(new TurnToAngle(45, RobotMap.TURN_SPEED, 2));
    	addSequential(new DriveForward(0.4, 0.4, 1));
    	addSequential(new TurnToAngle(-47, 0.2, 5));
    	addSequential(new DriveForward(0.3, 0.3, 1.47));
=======
=======
>>>>>>> parent of 8bd672d... Works better now
    	//addSequential(new DriveForward(0.2, 0.2, 1));
    	addSequential(new TurnToAngle(45, 0.3, 2));
    	//addSequential(new DriveForward(0.4, 0.4, 1));
    	//addSequential(new TurnToAngle(-45, 0.35, 2));
    	//addSequential(new DriveForward(0.17, 0.17, 1));
<<<<<<< HEAD
>>>>>>> 24cc9becd8d2d692c7be0e0f35654cf4ba31d064
=======
>>>>>>> parent of 8bd672d... Works better now
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

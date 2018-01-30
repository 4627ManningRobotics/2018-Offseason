package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class NNtraining extends CommandGroup {

    public NNtraining() {
    	addSequential(new TurnAndAddData());
    }
}

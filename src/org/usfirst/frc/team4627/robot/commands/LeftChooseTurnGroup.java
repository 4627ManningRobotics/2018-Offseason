package org.usfirst.frc.team4627.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftChooseTurnGroup extends Command {

	private boolean isDone;
	private CommandGroup c;
	
    public LeftChooseTurnGroup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.isDone = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	String fmsData = DriverStation.getInstance().getGameSpecificMessage().toUpperCase();
    	if(!fmsData.equals(null)){
    		if(fmsData.charAt(1) == 'L') {
    			this.c = new PlanLeftLeft();
    		}else if(fmsData.charAt(1) == 'R') {
    			this.c = new PlanLeftRight();
    		}
			this.c.start();
			this.isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

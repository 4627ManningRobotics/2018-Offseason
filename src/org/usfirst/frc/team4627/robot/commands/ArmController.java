package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmController extends Command {

	private short target;
	
	private boolean wasInDeadZone;
	
    public ArmController(short targetPosition) { // Ground, Switch, Scale
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.target = targetPosition;
    	this.wasInDeadZone = false;
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(this.isInDeadzone()) {
    		//System.out.println("dead");
        	this.wasInDeadZone = true;
    		if(this.isWristStowed()) {
    			Robot.arm.wrist.setSetpointRelative(0); // do not move wrist inside the deadzone
    			this.setPosition(this.target);
    		}else {
    			this.chooseWristStore(); // if it is in the deadzone go to the proper wrist storing position
    		}
    		
    	}else{
    		//System.out.println("alive");
    		chooseStartMovment();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(this.isInDeadzone()) {
    		//System.out.println("dead");
        	this.wasInDeadZone = true;
    		Robot.arm.setSetpoint(Robot.arm.getHeight()); // stop moving
    		this.chooseWristStore(); // store wrist
    	}else{
    		if(this.wasInDeadZone) { // only runs once, after leaving the dead zone
    		    this.wasInDeadZone = false;
    		    this.setPosition(this.target);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.isOnTarget() && Robot.arm.wrist.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.setSetpointRelative(0); // stop all movement
    	Robot.arm.wrist.setSetpointRelative(0);
    	RobotMap.CURRENT_POSITION = this.target;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
    
    private boolean isInDeadzone() {
    	return Robot.arm.getHeight() >= RobotMap.ARMS_DEADZONE_MIN && Robot.arm.getHeight() <= RobotMap.ARMS_DEADZONE_MAX;
    }
    
    private boolean isWristStowed() {
    	return (RobotMap.WRIST_DOWN_STOW <= Robot.arm.wrist.getPosition() + RobotMap.WRIST_TOLERANCE_LEVEL &&
    			RobotMap.WRIST_DOWN_STOW >= Robot.arm.wrist.getPosition() - RobotMap.WRIST_TOLERANCE_LEVEL) || 
    		   (RobotMap.WRIST_UP_STOW <= Robot.arm.wrist.getPosition() + RobotMap.WRIST_TOLERANCE_LEVEL && 
    			RobotMap.WRIST_UP_STOW >= Robot.arm.wrist.getPosition() - RobotMap.WRIST_TOLERANCE_LEVEL);
    }
    
    private void chooseWristStore() {
    	switch(RobotMap.CURRENT_POSITION) {  // 0 - ground, 1 - switch, 2 - scale
    		case 0:
    		case 1:
    			switch(this.target) {
    				case 2: 
    					Robot.arm.wrist.setSetpoint(RobotMap.WRIST_UP_STOW);
    					break;
    				default: 
    					Robot.arm.wrist.setSetpoint(RobotMap.WRIST_DOWN_STOW);
    					break;
    			}
    			break;
    		case 2:
    			switch(this.target) {
					case 0: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_UP_STOW);
						break;
					default: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_DOWN_STOW);
						break;
    			}
    			break;
    	}
    }
    
    private void chooseWristPos(short start, short end) {
    	switch(start) {  // 0 - ground, 1 - switch, 2 - scale
    		case 0:
    			switch(end) {
					case 0: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_GROUND);
						break;
					case 1: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_SWITCH);
						break;
    				case 2: 
    					Robot.arm.wrist.setSetpoint(RobotMap.WRIST_UP_STOW);
    					break;
    			}
    			break;
    		case 1:
    			switch(end) {
					case 0: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_GROUND);
						break;
					case 1: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_SWITCH);
						break;
					case 2: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_DOWN_STOW);
						break;
    			}
    			break;
    		case 2:
    			switch(end) {
					case 0: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_UP_STOW);
						break;
					case 1: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_DOWN_STOW);
						break;
					case 2: 
						Robot.arm.wrist.setSetpoint(RobotMap.WRIST_SCALE);
						break;
    			}
    			break;
    	}
    }
    
    private void chooseStartMovment() {
    	this.chooseWristPos(RobotMap.CURRENT_POSITION, this.target);
    	this.setPosition(this.target);
    	/* obsolete
		switch(RobotMap.CURRENT_POSITION){
			case 0: // at ground
				switch(this.target) {
					case 0: // wants to go to ground
						this.chooseWristPos((short)0, (short)0);
						Robot.arm.setSetpoint(RobotMap.ARMS_GROUND);
						break;
					case 1: // wants to go to switch
						this.chooseWristPos((short)0, (short)1);
						Robot.arm.setSetpoint(RobotMap.ARMS_SWITCH);
						break;
					case 2: // wants to go to scale
						this.chooseWristPos((short)0, (short)2);
						Robot.arm.setSetpoint(RobotMap.ARMS_SCALE);
						break;
				}
				break;
			case 1: // at switch
				switch(this.target) {
					case 0: // wants to go to ground
						this.chooseWristPos((short)1, (short)0);
						Robot.arm.setSetpoint(RobotMap.ARMS_GROUND);
						break;
					case 1: // wants to go to switch
						this.chooseWristPos((short)1, (short)1);
						Robot.arm.setSetpoint(RobotMap.ARMS_SWITCH);
						break;
					case 2: // wants to go to scale
						this.chooseWristPos((short)1, (short)2);
						Robot.arm.setSetpoint(RobotMap.ARMS_SCALE);
						break;
				}
				break;
			case 2: // at scale
				switch(this.target) {
					case 0: // wants to go to ground
						this.chooseWristPos((short)2, (short)0);
						Robot.arm.setSetpoint(RobotMap.ARMS_GROUND);
						break;
					case 1: // wants to go to switch
						this.chooseWristPos((short)2, (short)1);
						Robot.arm.setSetpoint(RobotMap.ARMS_SWITCH);
						break;
					case 2: // wants to go to scale
						this.chooseWristPos((short)2, (short)2);
						Robot.arm.setSetpoint(RobotMap.ARMS_SCALE);
						break;
				}
				break;
		}*/
    }
    
    private void setPosition(short target) {
    	switch(target) {
			case 0:
				Robot.arm.wrist.setSetpoint(RobotMap.ARMS_GROUND);
				break;
			case 1:
				Robot.arm.wrist.setSetpoint(RobotMap.ARMS_SWITCH);
				break;
			case 2:
				Robot.arm.wrist.setSetpoint(RobotMap.ARMS_SCALE);
				break;
    	}
    }
}

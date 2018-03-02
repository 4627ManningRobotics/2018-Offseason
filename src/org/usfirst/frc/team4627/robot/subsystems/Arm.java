package org.usfirst.frc.team4627.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	// no modifies on the arms so that its only accessible by the class and subclasses of the package
	final LeftArm leftArm = new LeftArm(0.1, 0.02, 0);
	final RightArm rightArm = new RightArm(0.1, 0.02, 0);

	public final Clamp clamp = new Clamp();
	public final Wrist wrist = new Wrist(0.008, 0.00025, 0);
	
    public void initDefaultCommand() {
    	
    }
    
    public void setSetpoint(double setpoint) {
    	this.rightArm.setSetpoint(setpoint);
    }

    public void setSetpointRelative(double setpoint) {
    	this.rightArm.setSetpointRelative(setpoint);
    }
    
    public void enable() {
    	this.leftArm.enable();
    	this.rightArm.enable();
    	this.wrist.enable();
    }
    
    public void disable() {
    	this.leftArm.disable();
    	this.rightArm.disable();
    	this.wrist.disable();
    }
	
	public boolean isOnTarget() {
		return this.leftArm.onTarget() && this.rightArm.onTarget();
	}
	
	public double getHeight() {
		return (this.leftArm.getPosition() + this.rightArm.getPosition()) / 2;
	}
}


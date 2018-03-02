package org.usfirst.frc.team4627.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	public final LeftArm leftArm = new LeftArm(0.1, 0.02, 0);
	public final RightArm rightArm = new RightArm(0.1, 0.02, 0);

	public final Clamp clamp = new Clamp();
	public final Wrist wrist = new Wrist();
	
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
    }
    
    public void disable() {
    	this.leftArm.disable();
    	this.rightArm.disable();
    }
	
	public boolean isOnTarget() {
		return this.leftArm.onTarget() && this.rightArm.onTarget();
	}
	
	public double getHeight() {
		return (this.leftArm.getPosition() + this.rightArm.getPosition()) / 2;
	}
}


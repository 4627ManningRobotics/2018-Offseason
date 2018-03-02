package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.OperatorControls;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	public final LeftArm leftArm = new LeftArm(0.1, 0.02, 0);
	public final RightArm rightArm = new RightArm(0.1, 0.02, 0);

	public final Clamp clamp = new Clamp();
<<<<<<< HEAD
	public final Wrist wrist = new Wrist(0.005, 0, 0);
=======
	public final Wrist wrist = new Wrist(0, 0, 0);

>>>>>>> parent of 6308b77... PID Testing of the wrist
	
    public void initDefaultCommand() {
    }
    
    public void setSetpoint(double setpoint) { // only to be used in testing
    	this.rightArm.setSetpoint(setpoint);
    }

    public void setSetpointRelative(double setpoint) { // only to be used in testing
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


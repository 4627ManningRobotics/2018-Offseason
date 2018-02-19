package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.OperatorControls;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	protected final LeftArm leftArm = new LeftArm(0.1, 0.02, 0);
	protected final RightArm rightArm = new RightArm(0.1, 0.02, 0);
	public final Clamp clamp = new Clamp();
	
	public final VictorSPX wrist = new VictorSPX(RobotMap.WRIST_MOTOR);
	
    public void initDefaultCommand() {
    	setDefaultCommand(new OperatorControls());
    }
    
    public void setSetpoint(double setpoint) {
    	this.rightArm.setSetpoint(setpoint);
    }
    
    public void enable() {
    	this.leftArm.enable();
    	this.rightArm.enable();
    }
    
    public void disable() {
    	this.leftArm.disable();
    	this.rightArm.disable();
    }

	public void setWrist(double motorSetting) {
		this.wrist.set(this.wrist.getControlMode(), motorSetting * RobotMap.WRIST_MAX_SPEED);
	}
	
	public boolean isOnTarget() {
		return this.leftArm.onTarget() && this.rightArm.onTarget();
	}
	
	public double getHeight() {
		return (this.leftArm.getPosition() + this.rightArm.getPosition()) / 2;
	}
}


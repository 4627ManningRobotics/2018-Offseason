package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	//private static final double P = 0.1;
	//private static final double I = 0.02;
	//private static final double D = 0.0;

	protected final LeftArm leftArm = new LeftArm(0.1, 0.02, 0);
	protected final RightArm rightArm = new RightArm(0.1, 0.02, 0);
	public final Clamp clamp = new Clamp();
	
	public final VictorSPX wrist = new VictorSPX(RobotMap.WRIST_MOTOR);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmHeight(double setpoint) {
    	this.rightArm.setSetpoint(setpoint);
    }

	public void setWrist(double motorSetting) {
		this.wrist.set(this.wrist.getControlMode(), motorSetting * RobotMap.WRIST_MAX_SPEED);
	}
	
	public void setSetpoint(double setpoint) {
		this.rightArm.setSetpoint(setpoint);
	}
}


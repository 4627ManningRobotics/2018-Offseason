package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	private static final double P = 0.1;
	private static final double I = 0.02;
	private static final double D = 0.0;

	public final LeftArm leftArm = new LeftArm(P, I, D);
	public final RightArm rightArm = new RightArm(P, I, D);
	public final Clamp clamp = new Clamp();
	
	public final TalonSRX wrist = new TalonSRX(RobotMap.WRIST_MOTOR);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmHeight(double setpoint) {
    	this.leftArm.setSetpoint(setpoint);
    	this.rightArm.setSetpoint(setpoint);
    }

	public void setWrist(double motorSetting) {
		if(motorSetting > RobotMap.WRIST_MAX_SPEED) {
			motorSetting = RobotMap.WRIST_MAX_SPEED;
		}else if(motorSetting < -RobotMap.WRIST_MAX_SPEED) {
			motorSetting = -RobotMap.WRIST_MAX_SPEED;
		}
		this.wrist.set(this.wrist.getControlMode(), motorSetting);
	}
}


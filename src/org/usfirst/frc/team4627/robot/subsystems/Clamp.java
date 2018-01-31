package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.OperatorControls;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Clamp extends Subsystem {

	private double leftMotorSpeed;
	private double rightMotorSpeed;
	private double liftingSpeed;
    public void initDefaultCommand() {
    	setDefaultCommand(new OperatorControls());
    }
    
    TalonSRX leftMotor3 = new TalonSRX(RobotMap.LEFT_MOTOR_3);
    TalonSRX rightMotor3 = new TalonSRX(RobotMap.RIGHT_MOTOR_3);
    TalonSRX liftingMotor = new TalonSRX(RobotMap.LIFTING_MOTOR);
    
    public void setLeftMotor(double motorSpeed) {
    	this.leftMotorSpeed = motorSpeed;
    			
    }
    public void setRightMotor(double motorSpeed) {
    	this.rightMotorSpeed = -motorSpeed;
    }
    public void setLiftingMotor(double motorSpeed) {
    	this.liftingSpeed = motorSpeed;
    }
}


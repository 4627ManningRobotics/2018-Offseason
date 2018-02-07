package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.OperatorControls;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Clamp extends Subsystem {


    public void initDefaultCommand() {
    	setDefaultCommand(new OperatorControls());
    }
    
    VictorSPX leftMotor = new VictorSPX(RobotMap.LEFT_CLAMP_MOTOR);
    TalonSRX rightMotor = new TalonSRX(RobotMap.RIGHT_CLAMP_MOTOR);
    //TalonSRX liftingMotor = new TalonSRX(RobotMap.LIFTING_MOTOR);
    
    public void setLeftMotor(double motorSpeed) {
    	leftMotor.set(leftMotor.getControlMode(), motorSpeed);
    			
    }
    public void setRightMotor(double motorSpeed) {
    	rightMotor.set(rightMotor.getControlMode(), -motorSpeed);
    }
    public void setLiftingMotor(double motorSpeed) {
    }
}


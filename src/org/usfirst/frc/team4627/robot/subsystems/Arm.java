package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public TalonSRX leftLiftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
    public TalonSRX rightLiftingMotor = new TalonSRX(RobotMap.RIGHT_LIFTING_MOTOR);
    public AnalogInput leftPotentiometer = new AnalogInput(0);
    public AnalogInput rightPotentiometer = new AnalogInput(1);
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmHeight() {
    	
    }
    
    public double getLeftVoltage() {
    	return this.leftPotentiometer.getVoltage();
    }
    
    public double getRightVoltage() {
    	return this.rightPotentiometer.getVoltage();
    }
}


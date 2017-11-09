package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.TankDrive;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	//instantiate motor controllers
	CANTalon leftMotor1 = new CANTalon(RobotMap.LEFT_MOTOR_1);
	CANTalon leftMotor2 = new CANTalon(RobotMap.LEFT_MOTOR_2);
	CANTalon rightMotor1 = new CANTalon(RobotMap.RIGHT_MOTOR_1);
	CANTalon rightMotor2 = new CANTalon(RobotMap.RIGHT_MOTOR_2);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDrive());
    }
        
    public void setLeftMotor(double motorSetting) {
    	leftMotor1.set(motorSetting);
    	leftMotor2.set(motorSetting);
    }
    public void setRightMotor(double motorSetting) {
    	rightMotor1.set(-motorSetting); //reverse setting 
    	rightMotor2.set(-motorSetting);
    }
}


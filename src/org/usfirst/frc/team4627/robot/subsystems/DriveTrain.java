package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.TankDrive;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.TalonSRX;
=======
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI;

>>>>>>> Autonomous
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	//instantiate motor controllers
	TalonSRX leftMotor1 = new TalonSRX(RobotMap.LEFT_MOTOR_1);
	TalonSRX leftMotor2 = new TalonSRX(RobotMap.LEFT_MOTOR_2);
	TalonSRX rightMotor1 = new TalonSRX(RobotMap.RIGHT_MOTOR_1);
	TalonSRX rightMotor2 = new TalonSRX(RobotMap.RIGHT_MOTOR_2);
<<<<<<< HEAD
=======
	
	//public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	/*public double getGyroAngle() {
		return gyro.getAngle();
	}*/
>>>>>>> Autonomous
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TankDrive());
    }
        
    public void setLeftMotor(double motorSetting) {
    	leftMotor1.set(leftMotor1.getControlMode(), motorSetting);
    	leftMotor2.set(leftMotor2.getControlMode(), motorSetting);
    }
    public void setRightMotor(double motorSetting) {
    	rightMotor1.set(rightMotor1.getControlMode(), -motorSetting); //reverse setting 
    	rightMotor2.set(rightMotor2.getControlMode(), -motorSetting);
    }
}


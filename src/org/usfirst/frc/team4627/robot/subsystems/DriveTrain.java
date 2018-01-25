package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.TankDrive;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
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
	Solenoid theSolenoid = new Solenoid(RobotMap.SOLENOID);
	public boolean isInHighGear = false;
	
	public static AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	public static Encoder leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public static Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	private double distancePerPulse = (2*RobotMap.WHEEL_DIAMETER)/(RobotMap.ENCODER_PULSES_PER_REVOLUTION/RobotMap.ENCODER_GEAR_RATIO);
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}
	
	public void initEncoders() {
		leftEncoder.setDistancePerPulse(this.distancePerPulse);
		rightEncoder.setDistancePerPulse(this.distancePerPulse);
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public double getDistance() {
		double distance = (leftEncoder.getDistance() + rightEncoder.getDistance()/2);
		return distance;
	}
	
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
    
    public void setHighGear(boolean isHigh) {
    	this.isInHighGear = isHigh;
    	this.theSolenoid.set(this.isInHighGear);
    }
    //public double getLeftMotorRotation() {
    	
    //}
}


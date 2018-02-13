package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftArm extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public LeftArm(double p, double i, double d) {
		super("LeftArm", p, i, d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		getPIDController().setOutputRange(-1, 1);
	}

	public TalonSRX liftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
    public AnalogInput potentiometer = new AnalogInput(0);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.calculatePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if(this.calculatePosition() < 15 && output < 0) {
			this.liftingMotor.set(this.liftingMotor.getControlMode(), 0);
		}else if(this.calculatePosition() > 150 && output > 0) {
			this.liftingMotor.set(this.liftingMotor.getControlMode(), 0);
		}else {
			this.liftingMotor.set(liftingMotor.getControlMode(), output);
		}
		System.out.println(this.calculatePosition());
	}
	
	public double calculatePosition() {
		return this.potentiometer.getVoltage() * -72.53217414 + 340.55633; // A
	}

/*
	public void Wrist(double motorSetting) {
		double liftSpeed = liftingMotor.getMotorOutputPercent();
		if (liftSpeed >= 0.3) {
			motorSetting = 0.3;
		} else if (liftSpeed < 0.3) {
			motorSetting = liftSpeed;
		}
		*/
		
	}


	




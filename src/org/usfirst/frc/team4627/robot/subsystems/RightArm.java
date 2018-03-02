package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class RightArm extends PIDSubsystem {

	private final TalonSRX liftingMotor = new TalonSRX(RobotMap.RIGHT_LIFTING_MOTOR);
	private final AnalogInput potentiometer = new AnalogInput(1);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public RightArm(double p, double i, double d) {
		super("RightArm", p, i, d);
		super.getPIDController().setAbsoluteTolerance(0.05);
		super.getPIDController().setContinuous(false); // does not wrap
		super.getPIDController().setOutputRange(-1, 1);
		super.setSetpoint(this.calculatePosition());
	}
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		Robot.arm.leftArm.setSetpoint(this.calculatePosition()); // set the left to follow the right
		return this.calculatePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		if((this.calculatePosition() < RobotMap.ARMS_MIN && output < 0) || (this.calculatePosition() > RobotMap.ARMS_MAX && output > 0)) {
			this.liftingMotor.set(this.liftingMotor.getControlMode(), 0);
		}else {
			this.liftingMotor.set(liftingMotor.getControlMode(), output * RobotMap.ARM_SPEED);
			if(output > RobotMap.LIFTING_MAX_SPEED) {
				this.liftingMotor.set(this.liftingMotor.getControlMode(), RobotMap.LIFTING_MAX_SPEED);
			}else if(output < -RobotMap.LIFTING_MAX_SPEED) {
				this.liftingMotor.set(this.liftingMotor.getControlMode(), -RobotMap.LIFTING_MAX_SPEED);
			}else {
				this.liftingMotor.set(this.liftingMotor.getControlMode(), output);
			}
		}
	}
	
	private double calculatePosition() {
		return this.potentiometer.getVoltage() * 72.906 + 3.1875;// A

	}
}


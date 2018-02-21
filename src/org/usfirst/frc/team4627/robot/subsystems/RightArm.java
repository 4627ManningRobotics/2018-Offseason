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

	public TalonSRX liftingMotor = new TalonSRX(RobotMap.RIGHT_LIFTING_MOTOR);
    public AnalogInput potentiometer = new AnalogInput(1);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public RightArm(double p, double i, double d) {
		super("RightArm", p, i, d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		getPIDController().setOutputRange(-1, 1);

		//getPIDController().setSetpoint(7);
	}
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		Robot.arm.leftArm.setSetpoint(this.calculatePosition());
		return this.calculatePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if((this.calculatePosition() < 15 && output < 0) || (this.calculatePosition() > 145 && output > 0)) {
			this.liftingMotor.set(this.liftingMotor.getControlMode(), 0);
		}else {

			this.liftingMotor.set(liftingMotor.getControlMode(), output * RobotMap.ARM_SPEED);
			

			if(output > RobotMap.LIFTING_MAX_SPEED) {
				this.liftingMotor.set(liftingMotor.getControlMode(), RobotMap.LIFTING_MAX_SPEED);
			}else if(output < -RobotMap.LIFTING_MAX_SPEED) {
				this.liftingMotor.set(liftingMotor.getControlMode(), -RobotMap.LIFTING_MAX_SPEED);
			}else {
				this.liftingMotor.set(liftingMotor.getControlMode(), output);
			}

		}
		//System.out.println(this.calculatePosition());
	}
	
	public double calculatePosition() {

		//return this.potentiometer.getVoltage(); //* 159.31 - 278.42; // A

		return this.potentiometer.getVoltage() * 72.906 + 3.1875;//72.353 + 0.4914; // A

	}
}


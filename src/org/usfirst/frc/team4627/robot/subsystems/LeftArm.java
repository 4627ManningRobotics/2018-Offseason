package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.FollowRightArm;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class LeftArm extends PIDSubsystem {

	private final TalonSRX liftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
	private final AnalogInput potentiometer = new AnalogInput(0);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public LeftArm(double p, double i, double d) {
		super("LeftArm", p, i, d);
		super.getPIDController().setAbsoluteTolerance(0.05);
		super.getPIDController().setContinuous(false); // does not wrap
		super.getPIDController().setOutputRange(-1, 1);
	}
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FollowRightArm());
    }

	@Override
	protected double returnPIDInput() {
		return this.calculatePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		//zero the motor if out of bounds
		if((this.calculatePosition() < RobotMap.ARMS_MIN && output < 0) || (this.calculatePosition() > RobotMap.ARMS_MAX && output > 0)) {
			this.liftingMotor.set(this.liftingMotor.getControlMode(), 0); 
		}else {
			this.liftingMotor.set(liftingMotor.getControlMode(), output * RobotMap.ARM_SPEED);
			//TODO this should be handled by the PIDController setOutputRange
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
		return this.potentiometer.getVoltage() * 71.4668 - 11.840;// B
	}

}


	




package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RightArm extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public RightArm(double p, double i, double d) {
		super("RightArm", p, i, d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		getPIDController().setOutputRange(-1, 1);
		getPIDController().setSetpoint(7);
	}

	public TalonSRX liftingMotor = new TalonSRX(RobotMap.RIGHT_LIFTING_MOTOR);
    public AnalogInput potentiometer = new AnalogInput(1);
    

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
		//System.out.println(this.calculatePosition());
	}
	
	public double calculatePosition() {
		return this.potentiometer.getVoltage() * -71.43831687 + 329.1775997; //B
	}
}


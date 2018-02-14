package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Arm extends PIDSubsystem {
	
	public final Clamp clamp = new Clamp();
	
	public final VictorSPX wrist = new VictorSPX(RobotMap.WRIST_MOTOR);
	public TalonSRX leftLiftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
	public TalonSRX rightLiftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
	
    public AnalogInput potentiometer = new AnalogInput(0);

    public Arm(double p, double i, double d) {
		super("Arm", p, i, d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		getPIDController().setOutputRange(-1, 1);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setWrist(double motorSetting) {
		this.wrist.set(this.wrist.getControlMode(), motorSetting * RobotMap.WRIST_MAX_SPEED);
	}

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.calculatePosition();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		if((this.calculatePosition() < 10 && output < 0) || (this.calculatePosition() > 150 && output > 0)) {
			this.leftLiftingMotor.set(this.leftLiftingMotor.getControlMode(), 0);
			this.rightLiftingMotor.set(this.rightLiftingMotor.getControlMode(), 0);
		}else {
			this.leftLiftingMotor.set(leftLiftingMotor.getControlMode(), output);
			this.rightLiftingMotor.set(this.rightLiftingMotor.getControlMode(), output);
		}
		//System.out.println(this.calculatePosition());
	}
	
	public double calculatePosition() {
		return this.potentiometer.getVoltage() * -72.53217414 + 340.55633; // B
	}
}


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
public class LeftArm extends PIDSubsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    public LeftArm(double p, double i, double d) {
		super("LeftArm", p, i, d);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		// TODO Auto-generated constructor stub
	}

	public PWMTalonSRX liftingMotor = new PWMTalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
    public AnalogInput potentiometer = new AnalogInput(0);
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getVoltage() {
    	return this.potentiometer.getVoltage();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.potentiometer.getAverageVoltage();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.liftingMotor.pidWrite(output);
	}
}


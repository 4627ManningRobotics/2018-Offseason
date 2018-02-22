package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Wrist extends PIDSubsystem {

	private final TalonSRX wrist = new TalonSRX(RobotMap.WRIST_MOTOR); //ppr = 1024
	
    public Wrist(double P, double I, double D) {
        super("Wrist", P, I, D);
        this.wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		super.getPIDController().setAbsoluteTolerance(0.05);
		super.getPIDController().setContinuous(false); // does not wrap
		super.getPIDController().setOutputRange(-1, 1);
		super.getPIDController().setInputRange(0, 360);
		
        int absolutePosition = this.wrist.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		
		//if (Constants.kSensorPhase)
			//absolutePosition *= -1;
		if (this.wrist.getInverted()) {
			absolutePosition *= -1;
		}
		/* set the quadrature (relative) sensor to match absolute */
		this.wrist.setSelectedSensorPosition(absolutePosition, 0, 10); // if your getting error look to the 0's <<<<<<<<<<<<<<<<<<<<<<
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
    	// not needed
    }

    protected double returnPIDInput() {
        return (this.wrist.getSelectedSensorPosition(0) / 1024) * 360;
    }

    protected void usePIDOutput(double output) {
    	this.wrist.set(this.wrist.getControlMode(), output);
    }
    
    public void setWrist(double speed) {
    	this.wrist.set(this.wrist.getControlMode(), speed);
    }
}

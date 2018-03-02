package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Wrist extends PIDSubsystem {

	public final TalonSRX wrist = new TalonSRX(RobotMap.WRIST_MOTOR); //ppr = 1024
	private final double OFFSET;
	
    public Wrist(double P, double I, double D) {
        super("Wrist", P, I, D);
        this.wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
		super.getPIDController().setAbsoluteTolerance(3);
		super.getPIDController().setContinuous(false); // does not wrap
		super.getPIDController().setOutputRange(-RobotMap.WRIST_MAX_SPEED, RobotMap.WRIST_MAX_SPEED); // CBW- can use this to set min max motor speed?
		super.getPIDController().setInputRange(-180, 180);
		super.setSetpoint(90);
		
        int absolutePosition = this.wrist.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF; // mask/bitwise operator, filters out unnecessary data
		absolutePosition = -absolutePosition / 10;
		//if (Constants.kSensorPhase)
			//absolutePosition *= -1;
			//absolutePosition *= -1;
		/* set the quadrature (relative) sensor to match absolute */
		this.wrist.setSelectedSensorPosition(absolutePosition, 0, 10); //absolute position is the start position of the potentiometer
		//																 2nd parameter is index (don't change it)
		//																 3rd parameter is time in milliseconds for the calculation to be completed
        this.OFFSET = absolutePosition;
		System.out.println(absolutePosition);
		// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
    	// not needed
    }

    protected double returnPIDInput() {
        return this.calculateAngle();
    }

    protected void usePIDOutput(double output) {
    	System.out.println(this.calculateAngle());
    	this.wrist.set(this.wrist.getControlMode(), -output);
    }
    
    public void setWrist(double speed) {
    	this.wrist.set(this.wrist.getControlMode(), speed);
    }
    
    public double calculateAngle() {
    	return (-this.wrist.getSensorCollection().getPulseWidthPosition() / 10d) - this.OFFSET;
    }
    
    public double wristAmperage() {
    	return this.wrist.getOutputCurrent();
    }
}

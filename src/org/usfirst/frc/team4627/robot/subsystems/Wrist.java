package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Wrist extends PIDSubsystem {

	private final TalonSRX wrist = new TalonSRX(RobotMap.WRIST_MOTOR); //ppr = 1024
	
    public Wrist(double P, double I, double D) {
        super("Wrist", P, I, D);
        this.wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		getPIDController().setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false); // does not wrap
		getPIDController().setOutputRange(-1, 1);
		getPIDController().setInputRange(0, 360);
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
}

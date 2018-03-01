package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public TalonSRX wrist = new TalonSRX(RobotMap.WRIST_MOTOR);
	private double p = 0; // TODO
	public boolean isInTolerance = false;
	public double targetCurrent;
	
	public Wrist() {
		this.wrist.setSelectedSensorPosition(-this.wrist.getSensorCollection().getPulseWidthPosition(), 0, 10);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new WristPID());
    }
    
    public double getCurrent() {
    	return this.wrist.getOutputCurrent();
    }
    
    public double getAngle() {
    	return -this.wrist.getSelectedSensorPosition(0)/10d;
    }
    
    public double calculateError() {
    	return Math.abs((this.wrist.getOutputCurrent() - this.targetCurrent)/this.targetCurrent);
    }
}



package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.OperatorControls;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Clamp extends Subsystem {
	private VictorSPX leftMotor = new VictorSPX(RobotMap.LEFT_CLAMP_MOTOR);
	private VictorSPX rightMotor = new VictorSPX(RobotMap.RIGHT_CLAMP_MOTOR);
	
	private Solenoid muscle = new Solenoid(RobotMap.MUSCLE);
    
	@Override
	protected void initDefaultCommand() {
    	super.setDefaultCommand(new OperatorControls());
	}
	
    public void setLeftMotor(double motorSpeed) {
    	this.leftMotor.set(this.leftMotor.getControlMode(), motorSpeed);
    			
    }
    
    public void setRightMotor(double motorSpeed) {
    	this.rightMotor.set(this.rightMotor.getControlMode(), -motorSpeed);
    }
    
    public void openClamp() {
    	this.muscle.set(true); // naturally releases pressure over time
    }
    
}



package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;
import org.usfirst.frc.team4627.robot.commands.BeltControls;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Clamp extends Subsystem {
	private final VictorSPX leftMotor = new VictorSPX(RobotMap.LEFT_CLAMP_MOTOR);
	private final VictorSPX rightMotor = new VictorSPX(RobotMap.RIGHT_CLAMP_MOTOR);
	
	private Solenoid muscle = new Solenoid(RobotMap.MUSCLE);
    
	@Override
	protected void initDefaultCommand() {
    	super.setDefaultCommand(new BeltControls());
	}
	
    public void setLeftMotor(double motorSpeed) {
    	this.leftMotor.set(this.leftMotor.getControlMode(), motorSpeed);		
    }
    
    public void setRightMotor(double motorSpeed) {
    	this.rightMotor.set(this.rightMotor.getControlMode(), -motorSpeed); // reversed motor to make sure a positive value for both represents the same direction
    }
    
    public void toggleClamp() {
    	this.muscle.set(!this.muscle.get());
    }
    
    public void setClamp(boolean open) {
    	this.muscle.set(open);
    }
    
}


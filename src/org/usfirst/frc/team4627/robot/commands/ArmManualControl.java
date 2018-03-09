package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Allows manual control of arm and wrist
 * DPad Up and Down control arm
 * DPad Left and Right control wrist
 * Both are controlled by incrementing the setpoints, speeds should be changed by adjusting the constants in RobotMap
 * 
 * Pressing a TeleOpGoTo button will cancel manual control due to the requires()
 */
public class ArmManualControl extends Command {

    public ArmManualControl() {
        //requires(Robot.rightArm);
        requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Get the DPadY value, scale it and increment the arm setpoint, constraining to min and max
    	double DPadY = Robot.oi.getOperatorDPadY();
    	double scaledDPadY = DPadY * RobotMap.MANUAL_ARM_SCALING;
    	double newArmSetpoint= Robot.rightArm.getSetpoint() + scaledDPadY;
    	newArmSetpoint=constrain(newArmSetpoint, RobotMap.ARMS_MIN, RobotMap.ARMS_MAX);
    	Robot.rightArm.setSetpoint( newArmSetpoint );
    	
    	//Get the DPadX value, scale it and increment the wrist setpoint, constraining to downStow and upStow
    	double DPadX = Robot.oi.getOperatorDPadX();
    	double scaledDPadX = DPadX * RobotMap.MANUAL_WRIST_SCALING;
    	double newWristSetpoint= Robot.wrist.getSetpoint() + scaledDPadX;
    	newWristSetpoint = constrain(newWristSetpoint, RobotMap.WRIST_DOWN_STOW, RobotMap.WRIST_UP_STOW);
    	Robot.wrist.setSetpoint( newWristSetpoint );
    	System.out.println(newWristSetpoint);
    }

    private double constrain(double value, double min, double max) {
		if (value < min) {
			return min;
		}else if(value > max) {
			return max;
		}else {
			return value;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

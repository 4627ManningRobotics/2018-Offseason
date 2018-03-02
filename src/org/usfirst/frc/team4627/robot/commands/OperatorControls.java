package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OperatorControls extends Command {

    public OperatorControls() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm.clamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// intake/clamp controls
    	if(Robot.oi.getOperatorButton(RobotMap.RIGHT_BUMPER)) {
    		Robot.arm.clamp.setLeftMotor(RobotMap.CLAMP_MAX_SPEED);
    		Robot.arm.clamp.setRightMotor(RobotMap.CLAMP_MAX_SPEED);
    	} else if(Robot.oi.getOperatorButton(RobotMap.LEFT_BUMPER)) {
    		Robot.arm.clamp.setLeftMotor(-RobotMap.CLAMP_MAX_SPEED);
    		Robot.arm.clamp.setRightMotor(-RobotMap.CLAMP_MAX_SPEED);
    	} else {
    		double triggerVal = Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER);
    		double stick = Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
        	Robot.arm.clamp.setLeftMotor((triggerVal + stick) * RobotMap.CLAMP_MAX_SPEED);
        	Robot.arm.clamp.setRightMotor((triggerVal - stick) * RobotMap.CLAMP_MAX_SPEED);
    	}
    	
    	//arms
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') { // control of the arm using the left stick
    		System.out.println(Robot.arm.leftArm.potentiometer.getVoltage());
    		Robot.arm.leftArm.liftingMotor.set(Robot.arm.leftArm.liftingMotor.getControlMode(), -Robot.oi.getDriverRawAxis(RobotMap.LEFT_STICK_Y));
		}else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    		System.out.println(Robot.arm.rightArm.potentiometer.getVoltage());
    		Robot.arm.rightArm.liftingMotor.set(Robot.arm.rightArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
		}else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'B') {
			System.out.println("Right: " + Robot.arm.rightArm.potentiometer.getVoltage() + "\nLeft: " + Robot.arm.leftArm.potentiometer.getVoltage());
    		Robot.arm.rightArm.liftingMotor.set(Robot.arm.rightArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
    		Robot.arm.leftArm.liftingMotor.set(Robot.arm.leftArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
		}
    	
    	//wrist
    	Robot.arm.wrist.setWrist(Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_STICK_Y));
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

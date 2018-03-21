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
    	requires(Robot.clamp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.getOperatorBumper(RobotMap.RIGHT_BUMPER)) { // controls for in-take, full speed out
    		Robot.clamp.setLeftMotor(0.5);
    		Robot.clamp.setRightMotor(0.5);
    	} else if(Robot.oi.getOperatorBumper(RobotMap.LEFT_BUMPER)) { // full out
    		Robot.clamp.setLeftMotor(-0.5);
    		Robot.clamp.setRightMotor(-0.5);
    	} else { // trigger based
    		double triggerVal = Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER);
    		double stick = Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
        	Robot.clamp.setLeftMotor(triggerVal + stick);
        	Robot.clamp.setRightMotor(triggerVal - stick);
    	}
    	
    	if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') { // control of the arm using the left stick
    		System.out.println(Robot.leftArm.calculatePosition());
    		Robot.leftArm.liftingMotor.set(Robot.leftArm.liftingMotor.getControlMode(), -Robot.oi.getDriverRawAxis(RobotMap.LEFT_STICK_Y));
		}else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
    		System.out.println(Robot.rightArm.calculatePosition());
    		Robot.rightArm.liftingMotor.set(Robot.rightArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
		}else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'B') {
    		Robot.rightArm.liftingMotor.set(Robot.rightArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
    		Robot.leftArm.liftingMotor.set(Robot.leftArm.liftingMotor.getControlMode(), -Robot.oi.getOperatorRawAxis(RobotMap.LEFT_STICK_Y));
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

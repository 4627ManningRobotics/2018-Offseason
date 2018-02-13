package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.Robot;
import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDrive extends Command {

    public TankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double triggerVal = Robot.oi.getDriverRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getDriverRawAxis(RobotMap.LEFT_TRIGGER);
    		double stick = Robot.oi.getDriverRawAxis(RobotMap.LEFT_STICK_X) * RobotMap.TURNING_RATE;
    		Robot.driveTrain.setLeftMotor(triggerVal + stick);
    		Robot.driveTrain.setRightMotor(triggerVal - stick);
    		
    		if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'L') {
        		System.out.println(Robot.leftArm.calculatePosition());
        		Robot.leftArm.liftingMotor.set(Robot.leftArm.liftingMotor.getControlMode(), Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER));
    		}else if(DriverStation.getInstance().getGameSpecificMessage().charAt(0) == 'R') {
        		System.out.println(Robot.rightArm.calculatePosition());
        		Robot.rightArm.liftingMotor.set(Robot.rightArm.liftingMotor.getControlMode(), Robot.oi.getOperatorRawAxis(RobotMap.RIGHT_TRIGGER) - Robot.oi.getOperatorRawAxis(RobotMap.LEFT_TRIGGER));
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

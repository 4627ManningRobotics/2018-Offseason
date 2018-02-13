package org.usfirst.frc.team4627.robot;

import org.usfirst.frc.team4627.robot.commands.ChangeGears;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import org.usfirst.frc.team4627.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
	
	/*
	public double getDriverRightTrigger() {
		return driverController.getRawAxis(RobotMap.RIGHT_TRIGGER);
	}
	
	public double getDriverLeftTrigger() {
		return driverController.getRawAxis(RobotMap.LEFT_TRIGGER);
	}
	
	public double getDriverLeftStickX() {
		return driverController.getRawAxis(RobotMap.LEFT_STICK_X);
	}
	
	public boolean getControllerBButton() {
		return driverController.getRawButton(RobotMap.BUTTON_B);
	}
	
	public double getDriverLeftStickY() {
		return -(driverController.getRawAxis(RobotMap.LEFT_STICK_Y));
	}
	public double getDriverRightStickY() {
		return -(driverController.getRawAxis(RobotMap.RIGHT_STICK_Y));
	}
	
	public double getOperatorRightTrigger() {
		return operatorController.getRawAxis(RobotMap.RIGHT_TRIGGER);
	}
	
	public double getOperatorLeftTrigger() {
		return operatorController.getRawAxis(RobotMap.LEFT_TRIGGER);
	}
	
	public double getOperatorLeftStickX() {
		return operatorController.getRawAxis(RobotMap.LEFT_STICK_X);
	}
	*/
	
	public boolean getOperatorBumper(int axis) {
		return this.operatorController.getRawButton(RobotMap.RIGHT_BUMPER);
	}
	
	public boolean getDriverBumper(int axis) {
		return this.driverController.getRawButton(RobotMap.LEFT_BUMPER);
	}
	
	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
	}
	
	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
	}
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	public OI () {
	
	}

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}

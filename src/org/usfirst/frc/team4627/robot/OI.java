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
	Joystick driver = new Joystick(RobotMap.DRIVER_CONTROLLER);
	//Button buttonA = new JoystickButton(driver, RobotMap.BUTTON_A);
	Joystick  rightTrigger = new Joystick(RobotMap.RIGHT_TRIGGER);
	XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
	
	public double getDriverRightTrigger() {
		return driverController.getTriggerAxis(GenericHID.Hand.kRight);
	}
	
	public double getDriverLeftTrigger() {
		return driverController.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	public double getDriverLeftStickX() {
		return driverController.getRawAxis(RobotMap.LEFT_STICK_X);
	}
	
	public boolean getControllerBButton() {
		return driverController.getBButton();
	}
	
	public double getDriverLeftStickY() {
		return -(driver.getRawAxis(RobotMap.LEFT_STICK_Y));
	}
	public double getDriverRightStickY() {
		return -(driver.getRawAxis(RobotMap.RIGHT_STICK_Y));
	}
	
	public double getOperatorRightTrigger() {
		return operatorController.getTriggerAxis(GenericHID.Hand.kRight);
	}
	
	public double getOperatorLeftTrigger() {
		return operatorController.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	public double getOperatorLeftStickX() {
		return operatorController.getRawAxis(RobotMap.LEFT_STICK_X);
	}
	
	public boolean getOperatorRightBumper() {
		return operatorController.getBumper(GenericHID.Hand.kRight);
	}
	
	public boolean getOperatorLeftBumper() {
		return operatorController.getBumper(GenericHID.Hand.kLeft);
	}
	
	public boolean getAButtonPressed() {
		return driverController.getAButton();
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

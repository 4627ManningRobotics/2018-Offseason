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
	XboxController xboxController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	
	public double getRightTrigger() {
		return xboxController.getTriggerAxis(GenericHID.Hand.kRight);
	}
	
	public double getLeftTrigger() {
		return xboxController.getTriggerAxis(GenericHID.Hand.kLeft);
	}
	
	public double getLeftStickX() {
		return xboxController.getRawAxis(RobotMap.LEFT_STICK_X);
	}
	
	public boolean getControllerBButton() {
		return xboxController.getBButton();
	}
	
	public double getLeftStickY() {
		//return driver.getRawAxis(RobotMap.LEFT_TRIGGER);
		return -(driver.getRawAxis(RobotMap.LEFT_STICK_Y));
	}
	public double getRightStickY() {
		//return driver.getRawAxis(RobotMap.RIGHT_TRIGGER);
		return -(driver.getRawAxis(RobotMap.RIGHT_STICK_Y));
	}
	public boolean isButtonPressed(int button) {
		return driver.getRawButtonPressed(button);
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

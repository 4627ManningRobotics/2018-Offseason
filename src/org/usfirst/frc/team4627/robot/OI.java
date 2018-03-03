package org.usfirst.frc.team4627.robot;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


import org.usfirst.frc.team4627.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	
	XboxController driverController = new XboxController(RobotMap.DRIVER_CONTROLLER);
	XboxController operatorController = new XboxController(RobotMap.OPERATOR_CONTROLLER);
	
	Button oButtonA = new JoystickButton(operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(operatorController, RobotMap.BUTTON_X);

	public boolean getOperatorButton(int axis) {
		return this.operatorController.getRawButtonPressed(axis);
	}
	
	public boolean getDriverButton(int axis) {
		return this.driverController.getRawButtonPressed(axis);
	}
	
	public double getOperatorRawAxis(int axis) {
		return this.operatorController.getRawAxis(axis);
	}
	
	public double getDriverRawAxis(int axis) {
		return this.driverController.getRawAxis(axis);
	}
	

	public OI () {
		oButtonA.whenPressed(new TeleOpGoToGround());
		oButtonB.whenPressed(new TeleOpGoToSwitch());
		oButtonY.whenPressed(new TeleOpGoToScale());
		
		oButtonX.whenPressed(new ToggleClamp());

	}


}

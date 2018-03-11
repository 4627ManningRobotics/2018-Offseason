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
	
	Button dButtonA = new JoystickButton(this.driverController, RobotMap.BUTTON_A);
	Button dButtonB = new JoystickButton(this.driverController, RobotMap.BUTTON_B);
	Button dButtonX = new JoystickButton(this.driverController, RobotMap.BUTTON_X);
	Button dButtonY = new JoystickButton(this.driverController, RobotMap.BUTTON_Y);
	
	Button oButtonA = new JoystickButton(this.operatorController, RobotMap.BUTTON_A);
	Button oButtonB = new JoystickButton(this.operatorController, RobotMap.BUTTON_B);
	Button oButtonY = new JoystickButton(this.operatorController, RobotMap.BUTTON_Y);
	Button oButtonX = new JoystickButton(this.operatorController, RobotMap.BUTTON_X);
	Button oButtonBack = new JoystickButton(this.operatorController, RobotMap.BACK_BUTTON);

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
	
	public double getOperatorDPadX() {
		int angle = this.operatorController.getPOV(0);
		if(angle == 90) {
			return 1;
		}else if(angle == 270) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public double getOperatorDPadY() {
		int angle = this.operatorController.getPOV(0);
		if(angle == 0) {
			return 1;
		}else if(angle == 180) {
			return -1;
		}else {
			return 0;
		}
	}

	public OI () {
		this.dButtonA.whenPressed(new SwitchGears());
		
		this.dButtonX.whenPressed(new SetTurningSpeed(0.2)); // low 
		this.dButtonY.whenPressed(new SetTurningSpeed(0.5)); // medium
		this.dButtonB.whenPressed(new SetTurningSpeed(0.9)); // high 
		
		this.oButtonA.whenPressed(new GoToGround());
		this.oButtonB.whenPressed(new GoToSwitch());
		this.oButtonY.whenPressed(new GoToScale());
		
		this.oButtonX.whenPressed(new ToggleClamp());
		this.oButtonBack.whenPressed(new ArmManualControl());

	}


}

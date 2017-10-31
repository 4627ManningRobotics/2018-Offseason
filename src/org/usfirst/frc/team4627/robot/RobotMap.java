package org.usfirst.frc.team4627.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int LEFT_MOTOR_1 = 0;
	public static final int LEFT_MOTOR_2 = 1;
	public static final int RIGHT_MOTOR_1 = 2;
	public static final int RIGHT_MOTOR_2 = 3;
	public static final int DRIVER_CONTROLLER = 0;
	public static final int BUTTON_A = 0;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

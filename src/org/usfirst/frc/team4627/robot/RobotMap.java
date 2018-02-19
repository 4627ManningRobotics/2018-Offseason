package org.usfirst.frc.team4627.robot;

import fullyconnectednetwork.NN;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	//0,7,8 victors
	public static final int LEFT_MOTOR_1 = 5;
	public static final int LEFT_MOTOR_2 = 6;
	public static final int LEFT_CLAMP_MOTOR = 7;
	public static final int RIGHT_MOTOR_1 = 3;
	public static final int RIGHT_MOTOR_2 = 4;
	public static final int RIGHT_CLAMP_MOTOR = 8;
	public static final int LEFT_LIFTING_MOTOR = 2;
	public static final int RIGHT_LIFTING_MOTOR = 1;
	public static final int WRIST_MOTOR = 0;
	
	public static final int DRIVER_CONTROLLER = 0;
	public static final int OPERATOR_CONTROLLER = 1;
	public static final int SOLENOID = 0;
	public static final double WRIST_MAX_SPEED = 0.5;
	public static final double CLAMP_MAX_SPEED = 0.9;
	public static double TURNING_RATE = 0.5;
	public static final double TURNING_SPEED = 0.2;
	
	public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int BACK_BUTTON = 7;
	public static final int START_BUTTON = 8;
	public static final int LEFT_STICK_BUTTON = 9;
	public static final int RIGHT_STICK_BUTTON = 10;
	
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_Y = 5;
	public static final int RIGHT_STICK_X = 4;
	
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
	
	public static final int MAX_TURN_ANGLE = 50;
	public static final int MIN_TURN_ANGLE = 40;
	
	public static final int ENCODER_PULSES_PER_REVOLUTION = 1440;
	public static final int WHEEL_DIAMETER = 6;
	public static final int ENCODER_GEAR_RATIO = 3;

	//public static final NN TURNING_NETWORK = new NN("/home/lvuser/Saves/turnNetSaveTest.txt", "/home/lvuser/Saves/turnSetSaveTest.txt"); 
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

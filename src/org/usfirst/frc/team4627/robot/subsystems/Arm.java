package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    TalonSRX leftLiftingMotor = new TalonSRX(RobotMap.LEFT_LIFTING_MOTOR);
    TalonSRX rightLiftingMotor = new TalonSRX(RobotMap.RIGHT_LIFTING_MOTOR);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setArmHeight() {
    	
    }
}


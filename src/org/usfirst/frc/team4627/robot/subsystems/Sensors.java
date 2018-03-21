package org.usfirst.frc.team4627.robot.subsystems;

import org.usfirst.frc.team4627.robot.commands.Print;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
@SuppressWarnings("deprecation")
public class Sensors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static NetworkTable nw; //soon... ish, I really have no clue but this is how we get camera distance and angles
	
	public static final AHRS gyro = new AHRS(SerialPort.Port.kUSB);
	
	public double getGyroAngle() {
		return gyro.getAngle();
	}

    public void initDefaultCommand() {
        super.setDefaultCommand(new Print());
    }
}


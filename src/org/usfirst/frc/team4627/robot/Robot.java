
package org.usfirst.frc.team4627.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4627.robot.commands.TimedAuto;
import org.usfirst.frc.team4627.robot.commands.LeftChooseTurnGroup;
import org.usfirst.frc.team4627.robot.commands.NNDistanceTraining;
import org.usfirst.frc.team4627.robot.commands.SensorAuto;
import org.usfirst.frc.team4627.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final LeftArm leftArm = new LeftArm(RobotMap.LEFT_ARM_P, RobotMap.LEFT_ARM_I, RobotMap.LEFT_ARM_D);
	public static final RightArm rightArm = new RightArm(RobotMap.RIGHT_ARM_P, RobotMap.RIGHT_ARM_I, RobotMap.RIGHT_ARM_D);
	public static final Clamp clamp = new Clamp();
	public static final Wrist wrist = new Wrist(RobotMap.WRIST_P, RobotMap.WRIST_I, RobotMap.WRIST_D);
	public static final Sensors sensors = new Sensors();
	private static final Compressor comp = new Compressor(0);
	
	public static OI oi;
	Command autonomousCommand;
	SendableChooser<Command> autoChooser;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Robot.comp.setClosedLoopControl(true);
		Robot.oi = new OI();
		Sensors.gyro.reset();
		Sensors.gyro.zeroYaw();
    	Robot.driveTrain.initEncoders();
		Robot.driveTrain.resetEncoders();
		this.autoChooser = new SendableChooser<Command>();
		this.autoChooser.addDefault("Timed Auto: ", new TimedAuto());
		this.autoChooser.addObject("Sensor Auto: ", new SensorAuto());
		this.autoChooser.addObject("Plan Left: ", (Command) new LeftChooseTurnGroup());
		this.autoChooser.addObject("Plan Right: ", (Command) new LeftChooseTurnGroup());
		this.autoChooser.addObject("Auto Distance Training: ", (Command) new NNDistanceTraining(0.2));
		SmartDashboard.putData("Auto Chooser: ", this.autoChooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		PIDdisable(); // make sure all PID systems are off
	}
		
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Sensors.gyro.zeroYaw();
		PIDenable(); // make sure all PID systems are on
		
		Robot.clamp.setClamp(false); // make sure the clamp starts closed

		
		while( DriverStation.getInstance().getGameSpecificMessage() == null) { // wait to get fms
			
		}
		RobotMap.FMSData =  DriverStation.getInstance().getGameSpecificMessage();
		if(this.containsANumber(RobotMap.FMSData)) {
			RobotMap.timesIndex = Integer.parseInt(RobotMap.FMSData);
		}
		autonomousCommand = (Command) autoChooser.getSelected();

		if ( this.autonomousCommand != null) {
			this.autonomousCommand.start();
			
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//System.out.println("Angle: " + Robot.wrist.calculateAngle() + " Arm: " + Robot.rightArm.calculatePosition());

		SmartDashboard.putBoolean("Is in high gear: ", Robot.driveTrain.isInHighGear);
		//SmartDashboard.putNumber("left position", leftArm.calculatePosition());
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Sensors.gyro.zeroYaw();
		//Robot.arm.setSetpoint(Double.parseDouble(DriverStation.getInstance().getGameSpecificMessage()));
		PIDenable(); // make sure all PID systems are on

		Robot.clamp.setClamp(false); // make sure the clamp starts closed
		
		while( DriverStation.getInstance().getGameSpecificMessage() == null) { // wait to get fms
			
		}
		RobotMap.FMSData =  DriverStation.getInstance().getGameSpecificMessage();
		
		if (this.autonomousCommand != null) {
			this.autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//System.out.println("Angle: " + Robot.wrist.calculateAngle() + " Arm: " + Robot.rightArm.calculatePosition());

		SmartDashboard.putBoolean("Is in high gear: ", Robot.driveTrain.isInHighGear);
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		//System.out.println("Angle: " + Robot.wrist.calculateAngle() + " Arm: " + Robot.rightArm.calculatePosition());
	}
	
	public void PIDenable() {
    	leftArm.enable();
    	rightArm.enable();
    	wrist.enable();
    }
    
    public void PIDdisable() {
    	leftArm.disable();
    	rightArm.disable();
    	wrist.disable();
    }
	

 private boolean containsANumber(String s){
	return s.contains("0") || s.contains("1") || s.contains("2") || s.contains("3") || s.contains("4") || s.contains("5") || s.contains("6") || s.contains("7") || s.contains("8") || s.contains("9");
 }
}
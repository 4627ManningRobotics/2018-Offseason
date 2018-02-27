
package org.usfirst.frc.team4627.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4627.robot.commands.ArmController;
import org.usfirst.frc.team4627.robot.commands.Auto;
import org.usfirst.frc.team4627.robot.commands.NNtraining;
import org.usfirst.frc.team4627.robot.commands.PlanLeft;
import org.usfirst.frc.team4627.robot.commands.PlanRight;
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
	public static final Arm arm = new Arm();
	//public static final String RobotMap = null;
	public static OI oi;
	Command autonomousCommand;
	SendableChooser<CommandGroup> autoChooser;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Robot.oi = new OI();
		DriveTrain.gyro.reset();
		DriveTrain.gyro.zeroYaw();
		this.autoChooser = new SendableChooser<CommandGroup>();
		this.autoChooser.addDefault("Default Auto: ", new Auto());
		this.autoChooser.addDefault("Plan Left: ", new PlanLeft());
		this.autoChooser.addDefault("Plan Right: ", new PlanRight());
		this.autoChooser.addDefault("Auto Training: ", new NNtraining());
		SmartDashboard.putData("Auto Chooser: ", this.autoChooser);
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Robot.arm.disable();
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
    	DriveTrain.gyro.zeroYaw();
		Robot.arm.enable();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */
		autonomousCommand = (Command) autoChooser.getSelected();
		// schedule the autonomous command (example)
		if ( this.autonomousCommand != null) {
			this.autonomousCommand.start();
			
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//SmartDashboard.putNumber("left position", leftArm.calculatePosition());
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
    	DriveTrain.gyro.zeroYaw();
		//Robot.arm.setSetpoint(Double.parseDouble(DriverStation.getInstance().getGameSpecificMessage()));
		Robot.arm.enable();

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (this.autonomousCommand != null) {
			this.autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {

		//SmartDashboard.putNumber("left position", leftArm.calculatePosition());
		if(Robot.oi.getDriverButton(RobotMap.BUTTON_A)) { // change gears
			Robot.driveTrain.theSolenoid.set(Robot.driveTrain.isInHighGear);
		}
		
		/*if(Robot.oi.getOperatorButton(RobotMap.BUTTON_X)) { // open claw
			Robot.arm.clamp.openClamp();
		}
		*/
		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_B)) { // someone needs to add the directional pad id's to the RobotMap
			Command c = (Command) new ArmController(RobotMap.SWITCH);
			c.start();
			//Robot.arm.setSetpoint(100);
		}
		
		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_A)) { // someone needs to add the directional pad id's to the RobotMap
			Command c = (Command) new ArmController(RobotMap.GROUND);
			c.start();
			//Robot.arm.setSetpoint(100);
		}
		
		if(Robot.oi.getOperatorButton(RobotMap.BUTTON_X)) {
    		Robot.arm.wrist.setSetpoint(65);
    	}
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		//SmartDashboard.putNumber("left position", leftArm.calculatePosition());
	}
}

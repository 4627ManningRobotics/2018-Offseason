package org.usfirst.frc.team4627.robot.commands;

import org.usfirst.frc.team4627.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SensorAuto extends CommandGroup {

    public SensorAuto() { // currently time based
    	addParallel(new GoToSwitch()); // move the arm/wrist as the robot moves
    	
    	addSequential(new DriveForward(0.5, 0.3));
    	addSequential(new TurnToAngleFMS(35,  0.25,  0.25,  3));
    	//addSequential(new TurnToAngleTimeFMS(0.35, 0.5)); // turn
    	addSequential(new DriveForwardFMS(0.75, 0.6, 0.2));
    	addSequential(new  TurnToAngleFMS(-37,  0.25,  0.25,  3));
    	//addSequential(new TurnToAngleTimeFMS(0.4, -0.5)); // turn
    	addSequential(new  DriveForwardFMS(0.75, .3, .1));
    	/*
    	addSequential(new DriveForward(0.25, 0.25, 4)); //1s
    	addSequential(new TurnToAngleFMS(43, RobotMap.TURN_SPEED, 2));
    	addSequential(new DriveForwardFMS(0.25, 0.25, 74.5, 12)); //1s
    	addSequential(new TurnToAngleFMS(-37, RobotMap.TURN_SPEED, 2));
    	//addSequential(new WaitForArmWrist()); // make sure the GoToSwitch has finished
    	addSequential(new DriveForward(0.3, 0.3, 80)); //1.2s
    	addSequential(new DriveForward(0.3, 0.3, 80)); //1.2s
    	*/
    	addSequential(new Wait(0.25)); // wait for the robot to stop
    	addSequential(new ReleaseBox());    
    	//addSequential(new TurnToAngleFMS(-10, RobotMap.TURN_SPEED, 2));
    	
    	addSequential(new DriveForward(-0.5, 0.5));
    	//addSequential(new TurnToAngleTimeFMS(0.4, 0.5)); // turn
    	addSequential(new TurnToAngleFMS(35,  0.75,  0.25,  3));
    	addSequential(new DriveForwardFMS(-0.75, 0.5, 0.2));
    	//addSequential(new TurnToAngleTimeFMS(0.5, -0.5)); // turn
    	addSequential(new TurnToAngleFMS(-37,  0.75,  0.25,  3));
    	addParallel(new GoToGround());
    	addSequential(new DriveForward(-0.5, 0.3));
    	addSequential(new Wait(0.25)); // wait for the robot to stop
    	addSequential(new DriveForward(0.5, 0.5));
    	addSequential(new IntakeBox());
    	
    }
}
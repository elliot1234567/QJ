// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class Drive extends CommandBase { // define class as child of CommandBase class (subclassing, inheritance)
  private final DriveTrain mDriveTrain = DriveTrain.getInstance(); // get static instance of drivetrain

  private DoubleSupplier mForward; // defining doublesupplier - this will be the indicator for the state of the joystick that we decide will control the forward motion of the drive train
  private DoubleSupplier mTurn; // ^^^

  public Drive(DoubleSupplier f_Axis, DoubleSupplier t_axis) { // constructor with two doublesupplier parameters - these parameters are the buttons that we specify when we construct a new Drive object in the robot container
    mForward = f_Axis; // initializing doublesupplier mForward as parameter f_Axis - this will be a double that gets the state of our button (which will be a joystick so it will have a value between -1 and 1)
    mTurn = t_axis; // ^^^

    addRequirements(mDriveTrain); // required method to call in constructor for CommandBase subclasses
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() { // overridden method
    mDriveTrain.resetEncoders(); // reset encoders 
    mDriveTrain.resetGyro(); // reset gyroscope
  }

  // Called every time the scheduler runs while the command is scheduled. (every 20 ms)
  @Override
  public void execute() { // ^^^
    mDriveTrain.drive(mForward.getAsDouble(), mTurn.getAsDouble()); // calling drive method - this is what makes the robot drive. It calls the drive method every 20 ms
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; // never finishes
  }
}

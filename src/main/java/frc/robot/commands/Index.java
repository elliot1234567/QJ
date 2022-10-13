// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class Index extends CommandBase { // define class as child of CommandBase class (subclassing, inheritance)
  private final Indexer mIndexer = Indexer.getInstance(); // getting static instance of Indexer
  private final Shooter mShooter = Shooter.getInstance();
  
  private BooleanSupplier mIntake; // defining booleansupplier, this will be the indicator for the state of the controller button
  private BooleanSupplier mExhaust; // ^^^

  public Index(BooleanSupplier i, BooleanSupplier e) { // constructor with two booleansupplier parameters - these parameters are the buttons that we specify when we construct a new Index object in the robot container
    mIntake = i; // initializing booleansupplier mIntake as parameter i - this will be a boolean that gets the state of our button (true or false)
    mExhaust = e; // ^^^

    addRequirements(mIndexer);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() { // overridden method
    
  }

  // Called every time the scheduler runs while the command is scheduled. (every 20 ms)
  @Override
  public void execute() { // ^^^
    if (mIntake.getAsBoolean()) { // if the button state for mIntake is true
        mIndexer.setSpeed(-1); // set the speed of the intake to 1, this will intake a ball
        mShooter.antiShooter();
    } else if (mExhaust.getAsBoolean()) { // if the button state for mExhaust is true
        mIndexer.setSpeed(1); // set the speed of the intake to -1 this will exhaust a ball
        mShooter.antiShooter();
    } else { // otherwise
        mIndexer.setSpeed(0); // set the intake speed to be 0 which is off
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {} // ^^^

  // Returns true when the command should end.
  @Override
  public boolean isFinished() { // ^^^
    return false; // never finishes
  }
}

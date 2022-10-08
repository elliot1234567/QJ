// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class Index extends CommandBase {
  private final Indexer indexer = Indexer.getInstance();
  private final BooleanSupplier intake;
  private final BooleanSupplier exhaust;

  public Index(BooleanSupplier i, BooleanSupplier e) {
    intake = i;
    exhaust = e;

    addRequirements(indexer);
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (intake.getAsBoolean()) {
        indexer.setSpeed(1);
    } else if (exhaust.getAsBoolean()) {
        indexer.setSpeed(-1);
    } else {
        indexer.setSpeed(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

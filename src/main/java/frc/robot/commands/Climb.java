package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
    public Climber climber;
    public BooleanSupplier leftBumper;
    public BooleanSupplier rightBumper;
    public boolean finished;

    public Climb(Climber c, BooleanSupplier lB, BooleanSupplier rB) {
        climber = c;
        leftBumper = lB;
        rightBumper = rB;

        addRequirements(climber);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (leftBumper.getAsBoolean()) {
            climber.up();
        } else if (rightBumper.getAsBoolean()) {
            climber.down();
        } else {
            climber.off();
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}

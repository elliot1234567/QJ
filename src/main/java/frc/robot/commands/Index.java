package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class Index extends CommandBase {

    public Indexer indexer;
    public BooleanSupplier aButton;
    public BooleanSupplier bButton;
    public BooleanSupplier xButton;
    public BooleanSupplier yButton;
    public boolean finished;

    public Index(Indexer i, BooleanSupplier a, BooleanSupplier b, BooleanSupplier x, BooleanSupplier y) {
        indexer = i;
        aButton = a;
        bButton = b;
        xButton = x;
        yButton = y;

        addRequirements(indexer);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (aButton.getAsBoolean()) {
            indexer.liftUp();
        } else if (bButton.getAsBoolean()) {
            indexer.liftDown();
        } else if (xButton.getAsBoolean()) {
            indexer.intake();
        } else if (yButton.getAsBoolean()) {
            indexer.outtake();
        } else {
            indexer.off();
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

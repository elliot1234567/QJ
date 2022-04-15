package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class AutoLiftDown extends CommandBase {
    Indexer indexer;
    boolean finished;
    
    public AutoLiftDown(Indexer i) {
        indexer = i;

        addRequirements(indexer);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (indexer.checkLowerLimit()) {
            finished = true;
        }
        indexer.liftDown();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
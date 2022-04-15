package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class AutoLiftUp extends CommandBase {
    Indexer indexer;
    boolean finished;
    
    public AutoLiftUp(Indexer i) {
        indexer = i;

        addRequirements(indexer);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (indexer.checkUpperLimit()) {
            finished = true;
        }
        indexer.liftUp();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
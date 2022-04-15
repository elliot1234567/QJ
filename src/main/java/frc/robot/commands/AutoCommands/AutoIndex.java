package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class AutoIndex extends CommandBase {
    Indexer indexer;
    boolean finished;
    double seconds;

    public final Timer timer;
    
    public AutoIndex(Indexer i, double s) {
        indexer = i;
        seconds = s;
        timer = new Timer();

        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        timer.start();
        indexer.intake();
    }

    @Override
    public void execute() {
        if (timer.get() >= seconds) {
            finished = true;
        }
        indexer.intake();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
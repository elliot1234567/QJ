package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class AutoDump extends CommandBase {
    Indexer indexer;
    boolean finished;
    double seconds;

    public final Timer timer;
    
    public AutoDump(Indexer i, double s) {
        indexer = i;
        seconds = s;
        timer = new Timer();

        addRequirements(indexer);
    }

    @Override
    public void initialize() {
        timer.start();
        indexer.outtake();
    }

    @Override
    public void execute() {
        if (timer.get() >= seconds) {
            finished = true;
        }
        indexer.outtake();
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
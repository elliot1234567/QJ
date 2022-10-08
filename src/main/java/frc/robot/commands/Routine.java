package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.util.Units;

public class Routine extends SequentialCommandGroup {
    private final Indexer mIndexer = Indexer.getInstance();
    private final Shooter mShooter = Shooter.getInstance();

    public Routine() {
        addCommands(new DriveDistance(Units.inchesToMeters(50)).raceWith(new RunCommand(() -> mIndexer.setSpeed(1), mIndexer)));
        addCommands(new RunCommand(() -> mIndexer.setSpeed(0.25), mIndexer).withTimeout(0.5));
        addCommands(new RunCommand(() -> mShooter.autoShoot(Constants.cargoRingSpeed), mShooter).until(mShooter.getFire()));
        addCommands(
            new ParallelCommandGroup(
                new RunCommand(() -> mIndexer.setSpeed(1), mIndexer),
                new RunCommand(() -> mShooter.autoShoot(Constants.cargoRingSpeed), mShooter)
            ).withTimeout(3)
        );
    }
}

package frc.robot.commands.autoroutines;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.autocommands.DriveDistance;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class TwoBall extends SequentialCommandGroup {
    private final Indexer mIndexer = Indexer.getInstance();
    private final Shooter mShooter = Shooter.getInstance();
    
    public TwoBall() {
        addCommands(new DriveDistance(60).raceWith(new RunCommand(() -> mShooter.antiShooter(), mShooter)).raceWith(new RunCommand(() -> mIndexer.setSpeed(-1), mIndexer)));
        addCommands(new RunCommand(() -> mIndexer.setSpeed(-1), mIndexer).raceWith(new RunCommand(() -> mShooter.antiShooter(), mShooter)).withTimeout(1.5));
        addCommands(new InstantCommand(() -> mIndexer.setSpeed(0)));
        addCommands(new RunCommand(() -> mShooter.autoRampUp(Constants.cargoRingSpeed), mShooter).until(mShooter.autoGetFire(Constants.cargoRingSpeed)));
        addCommands(new RunCommand(() -> mShooter.autoShoot(Constants.cargoRingSpeed), mShooter).alongWith(new RunCommand(() -> mIndexer.setSpeed(-1))).withTimeout(3));
    }
}

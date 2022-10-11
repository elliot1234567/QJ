package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.util.Units;


// a sequential command group executes commands one after another
public class Routine extends SequentialCommandGroup { // define class as child of SequentialCommandGroup class (subclassing, inheritance)
    private final Indexer mIndexer = Indexer.getInstance(); // getting static instance of Indexer
    private final Shooter mShooter = Shooter.getInstance(); // getting static instance of Shooter

    public Routine() { // constructor for Routine object
        // add commands to the group
        addCommands(new DriveDistance(Units.inchesToMeters(50)).raceWith(new RunCommand(() -> mIndexer.setSpeed(1), mIndexer))); // drive a certain distance while intaking until the robot reaches the specified distance
        addCommands(new RunCommand(() -> mIndexer.setSpeed(0.25), mIndexer).withTimeout(0.5)); // kick balls back a little so they dont get caught in the shooter while it speeds up
        addCommands(new RunCommand(() -> mShooter.autoRampUp(Constants.cargoRingSpeed), mShooter).until(mShooter.getFire())); // speed up shooter until it reaches specified speed
        addCommands(
            new ParallelCommandGroup( // a parallel command group executes commands at the same time
                new RunCommand(() -> mIndexer.setSpeed(1), mIndexer), // intake
                new RunCommand(() -> mShooter.autoShoot(Constants.cargoRingSpeed), mShooter) // shoot
            ).withTimeout(3) // timeout after 3 seconds
        );
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.util.Units;

public class Routine extends SequentialCommandGroup {
    Indexer indexer = Indexer.getInstance();
    Shooter shooter = Shooter.getInstance();
    public Routine() {
        addCommands(new DriveDistance(Units.inchesToMeters(50)).raceWith(new RunCommand(() -> indexer.setSpeed(1), indexer)));
        addCommands(new RunCommand(() -> indexer.setSpeed(0.25), indexer).withTimeout(0.5));
        addCommands(new RunCommand(() -> shooter.autoShoot(Constants.cargoRingSpeed), shooter).withTimeout(3));
    }
}

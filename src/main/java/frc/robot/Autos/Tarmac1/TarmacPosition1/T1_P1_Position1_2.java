package frc.robot.Autos.Tarmac1.TarmacPosition1;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.AngleCorrect;
import frc.robot.commands.AutoCommands.AutoDump;
import frc.robot.commands.AutoCommands.AutoIndex;
import frc.robot.commands.AutoCommands.AutoLiftDown;
import frc.robot.commands.AutoCommands.AutoLiftUp;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;

public class T1_P1_Position1_2 extends SequentialCommandGroup {
    
    public T1_P1_Position1_2(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new AngleCorrect(driveTrain, 80));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(24)));
        addCommands(new AngleCorrect(driveTrain, 90));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(36)));
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(-36)));
        addCommands(new AngleCorrect(driveTrain, -90));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(-24)));
        addCommands(new AngleCorrect(driveTrain, -80));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(65)),
            new AutoLiftDown(indexer),
            new AutoIndex(indexer, 3)
        ));
        addCommands(new AngleCorrect(driveTrain, 92));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(120)),
            new AutoIndex(indexer, 3)

        ));
        addCommands(new AngleCorrect(driveTrain, 115));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(120)),
            new AutoLiftUp(indexer)
        ));
        addCommands(new AngleCorrect(driveTrain, -45));
        addCommands(new AutoDump(indexer, 0.5));
    }
}
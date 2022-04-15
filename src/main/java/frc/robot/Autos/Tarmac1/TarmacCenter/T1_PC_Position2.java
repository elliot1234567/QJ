package frc.robot.Autos.Tarmac1.TarmacCenter;

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

public class T1_PC_Position2 extends SequentialCommandGroup {

    public T1_PC_Position2(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new AngleCorrect(driveTrain, 180));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(5)));
        addCommands(new AngleCorrect(driveTrain, 45));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(95)),
            new AutoLiftDown(indexer),
            new AutoIndex(indexer, 4)
        ));
        addCommands(new AngleCorrect(driveTrain, 180));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(95)),
            new AutoLiftUp(indexer)
        ));
        addCommands(new AngleCorrect(driveTrain, -45));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(5)));
        addCommands(new AutoDump(indexer, 0.5));
    }
}

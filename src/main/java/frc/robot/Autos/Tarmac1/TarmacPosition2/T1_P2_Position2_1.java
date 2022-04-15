package frc.robot.Autos.Tarmac1.TarmacPosition2;

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

public class T1_P2_Position2_1 extends SequentialCommandGroup {
    
    public T1_P2_Position2_1(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(40)));
        addCommands(new AngleCorrect(driveTrain, -45));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(-20)));
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new AngleCorrect(driveTrain, 180));
        addCommands(new DriveDistance(driveTrain, 20));
        addCommands(new AngleCorrect(driveTrain, 160));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(70)),
            new AutoLiftDown(indexer),
            new AutoIndex(indexer, 4)
        ));
        addCommands(new AngleCorrect(driveTrain, 110));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(110)),
            new AutoIndex(indexer, 4)
        ));
        addCommands(new AngleCorrect(driveTrain, 120));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(110)),
            new AutoLiftUp(indexer)
        ));
        addCommands(new AngleCorrect(driveTrain, -45));
        addCommands(new AutoDump(indexer, 0.5));

    }
}
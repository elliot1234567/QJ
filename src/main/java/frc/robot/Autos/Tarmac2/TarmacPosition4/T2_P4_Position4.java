package frc.robot.Autos.Tarmac2.TarmacPosition4;

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

public class T2_P4_Position4 extends SequentialCommandGroup {
    
    public T2_P4_Position4(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(60)),
            new AutoLiftDown(indexer),
            new AutoIndex(indexer, 5)
        ));
        addCommands(new AngleCorrect(driveTrain, -90));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(40)));
        addCommands(new AngleCorrect(driveTrain, -90));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(80)),
            new AutoLiftUp(indexer)
        ));
        addCommands(new AutoDump(indexer, 0.5));
    }
}

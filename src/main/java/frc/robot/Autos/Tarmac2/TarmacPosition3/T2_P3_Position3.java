package frc.robot.Autos.Tarmac2.TarmacPosition3;

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

public class T2_P3_Position3 extends SequentialCommandGroup {
    
    public T2_P3_Position3(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(75)),
            new AutoLiftDown(indexer),
            new AutoIndex(indexer, 5)
        ));
        addCommands(new AngleCorrect(driveTrain, 180));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(110)),
            new AutoLiftUp(indexer)
        ));
        addCommands(new AngleCorrect(driveTrain, 75));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(20)));
        addCommands(new AutoDump(indexer, 0.5));
    }
}
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

public class T1_PC_Position1_2 extends SequentialCommandGroup {
    
    public T1_PC_Position1_2(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(-5)));
        addCommands(new ParallelCommandGroup(
            new AngleCorrect(driveTrain, 180), // change to 165 after testing if turning -15 degrees later on is redundant.
            new AutoLiftDown(indexer)
        ));
        addCommands(new AngleCorrect(driveTrain, -15));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(90)),
            new AutoIndex(indexer, 4)
        ));
        addCommands(new AngleCorrect(driveTrain, 110));
        addCommands(new ParallelCommandGroup(
            new DriveDistance(driveTrain, Units.inchesToMeters(120)),
            new AutoIndex(indexer, 4)
        ));
        addCommands(new ParallelCommandGroup(
            new AngleCorrect(driveTrain, 115),
            new AutoLiftUp(indexer)
        ));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(120)));
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new AngleCorrect(driveTrain, -45));        
    }
}

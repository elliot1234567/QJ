package frc.robot.Autos.Tarmac2.TarmacCenter;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.AutoDump;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;

public class T2_PC_NoExtra extends SequentialCommandGroup {
    
    public T2_PC_NoExtra(DriveTrain driveTrain, Indexer indexer) {
        addCommands(new AutoDump(indexer, 0.5));
        addCommands(new DriveDistance(driveTrain, Units.inchesToMeters(-100)));
    }
}

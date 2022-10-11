package frc.robot.commands.autoroutines;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autocommands.DriveDistance;

public class OneBall extends SequentialCommandGroup {
    
    public OneBall() {
        addCommands(new DriveDistance(Units.inchesToMeters(50)));
    }
}

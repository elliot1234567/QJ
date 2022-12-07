package frc.robot.commands.autoroutines;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.autocommands.AutoPath;

public class Elliot extends SequentialCommandGroup {
    public Elliot() {
        addCommands(new AutoPath("paths\\Elliot.wpilib.json"));
    }
}

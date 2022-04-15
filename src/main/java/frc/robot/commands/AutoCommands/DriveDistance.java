package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
    double setpoint;
    boolean finished;
    DriveTrain driveTrain;

    public DriveDistance(DriveTrain DT, double s) {
        driveTrain = DT;
        setpoint = s;

        addRequirements(driveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        driveTrain.resetGyro();
        driveTrain.resetEncoders();
        driveTrain.setSetpoint(setpoint);
        driveTrain.drivePID();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        driveTrain.setAngle(0);
        if (driveTrain.atSetpoint()) {
            finished = true;
        } else {
            driveTrain.update();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        if (finished) {
            driveTrain.rotateSlow();
            driveTrain.resetEncoders();
            driveTrain.resetGyro();
        }
        return finished;
    }
}
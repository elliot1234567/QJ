package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
    double setpoint;
    boolean finished;
    double power;
    private static DriveTrain driveTrain = DriveTrain.getInstance();

    public DriveDistance(double s, double p) {
        power = p;
        setpoint = s;

        addRequirements(driveTrain);
    }

    public DriveDistance(double s) {
        power = 0.33;
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
        finished = driveTrain.atSetpoint();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveTrain.drive(0, 0);
        driveTrain.resetEncoders();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return finished;
    }
}
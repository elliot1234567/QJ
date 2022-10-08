package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
    private static DriveTrain mDriveTrain = DriveTrain.getInstance();

    private double mSetpoint;
    private boolean mFinished;
    private double mPower;

    public DriveDistance(double s, double p) {
        mPower = p;
        mSetpoint = s;

        addRequirements(mDriveTrain);
    }

    public DriveDistance(double s) {
        mPower = 0.33;
        mSetpoint = s;

        addRequirements(mDriveTrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        mDriveTrain.resetGyro();
        mDriveTrain.resetEncoders();
        mDriveTrain.setSetpoint(mSetpoint);
        mDriveTrain.drivePID(mPower);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        mFinished = mDriveTrain.atSetpoint();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        mDriveTrain.drive(0, 0);
        mDriveTrain.resetEncoders();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return mFinished;
    }
}
package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase { // subclassing the DriveDistance as child of CommandBase class (subclassing, inheritance)
    private static DriveTrain mDriveTrain = DriveTrain.getInstance(); // getting static instance of the drivetrain

    private double mSetpoint; // declaring double setpoint value
    private boolean mFinished; // declaring boolean finished value
    private double mPower; // declaring double power value

    public DriveDistance(double s, double p) { // constructor passing two doubles, setpoint and value
        mPower = p; // initializing as constructor pass through
        mSetpoint = s; // ^^^

        addRequirements(mDriveTrain); // required argument for CommandBase class constructor
    }

    public DriveDistance(double s) { // constructor passing double setpoint
        mPower = 0.33; // initializing as 0.33 which is a random value that makes the robot move fast-ish
        mSetpoint = s; // ^^^

        addRequirements(mDriveTrain); // ^^^
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() { // overridden method
        mDriveTrain.resetGyro(); // resetting gyro
        mDriveTrain.resetEncoders(); // resetting encoder positions
        mDriveTrain.setSetpoint(mSetpoint); // setting the PID setpoint
        mDriveTrain.drivePID(mPower); // drive to distance with power of mPower as defined above ^^^
    }

    // Called every time the scheduler runs while the command is scheduled. (every 20 ms)
    @Override
    public void execute() { // ^^^
        mFinished = mDriveTrain.atSetpoint(); // initializes as whether the robot is at the distance we specified using setSetpoint(mSetpoint)
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) { // ^^^
        mDriveTrain.drive(0, 0); // setting the drivetrain to 0 forward motion and 0 turn motion
        mDriveTrain.resetEncoders(); // resetting encoders
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() { // ^^^
        return mFinished; // checks to see if the end condition is true
    }
}
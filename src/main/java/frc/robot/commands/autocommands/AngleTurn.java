package frc.robot.commands.autocommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AngleTurn extends CommandBase { // subclassing AngleTurn as a child of CommandBase
    private final DriveTrain mDriveTrain = DriveTrain.getInstance(); // getting the instance of the drivetrain

    private double mAngle; // variable for the angle
    private boolean mFinished; // boolean for whether the command is finished
    
    public AngleTurn(double angle) { // constructor with parameter - this will be the angle that the drivetrain is supposed to turn to
        mAngle = angle; // setting instance variable as parameter

        addRequirements(mDriveTrain); // required method call
    }

    @Override
    public void initialize() { // overridden method for what happens when the command is scheduled
        mDriveTrain.resetEncoders(); // reset encoders
        mDriveTrain.resetGyro(); // reset gyro
        mDriveTrain.setAngle(mAngle); // set the angle
    }

    @Override
    public void execute() { // overridden method for what happens every 20 ms while the command is running
        mFinished = mDriveTrain.turn(); // turn to the angle until you are at the angle
    }

    @Override
    public void end(boolean interrupted) { // overridden method for what happens when the command ends
        mDriveTrain.drive(0, 0); // stop driving
        mDriveTrain.resetEncoders(); // reset encoders
        mDriveTrain.resetGyro(); // reset gyro
    }

    public boolean isFinished() { // method for checking if the command is finished
        return mFinished; // the finished variable dictates the state of this method
    }
}

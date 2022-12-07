package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private static DriveTrain mInstance; // defining static instance of drivetrain

    private CANSparkMax mLeftLeader; // defining spark max objects for interface with spark max motorcontroller hardware
    private CANSparkMax mRightLeader; // ^^^
    
    private CANSparkMax mLeftFollower; // ^^^
    private CANSparkMax mRightFollower; // ^^^

    private DifferentialDrive mDrive; // defining our drive base

    private ADXRS450_Gyro mGyro; // defining our gyroscope

    private DifferentialDriveOdometry mOdometry;

    public DriveTrain() {
        mLeftLeader = new CANSparkMax(1, MotorType.kBrushless); // initializing motorcontroller object with the CAN ID 1, which was set when we imaged the spark maxes with the proper firmware
        mRightLeader = new CANSparkMax(2, MotorType.kBrushless); // ^^^

        mLeftFollower = new CANSparkMax(3, MotorType.kBrushless); // ^^^
        mRightFollower = new CANSparkMax(4, MotorType.kBrushless); // ^^^

        mLeftLeader.setIdleMode(IdleMode.kBrake);
        mLeftFollower.setIdleMode(IdleMode.kBrake);
        mRightLeader.setIdleMode(IdleMode.kBrake);
        mRightFollower.setIdleMode(IdleMode.kBrake);

        mDrive = new DifferentialDrive(mLeftLeader, mRightLeader); // initializing our drivebase with two motors - left and right leaders

        mGyro = new ADXRS450_Gyro(); // initializing our gyroscope
        mGyro.calibrate(); // calibrating our gyroscope

        mLeftFollower.follow(mLeftLeader); // setting one NEO to follow the other on the left side
        mRightFollower.follow(mRightLeader); // ^^^

        mDrive.setSafetyEnabled(false); // weird error occurs when this isnt there - probably don't actually do this

        mOdometry = new DifferentialDriveOdometry(mGyro.getRotation2d());

        mLeftLeader.getEncoder().setPositionConversionFactor(Constants.POSITION_CONVERSION_FACTOR);
        mRightLeader.getEncoder().setPositionConversionFactor(Constants.POSITION_CONVERSION_FACTOR);
        mLeftLeader.getEncoder().setVelocityConversionFactor(Constants.VELOCITY_CONVERSION_FACTOR);
        mRightLeader.getEncoder().setVelocityConversionFactor(Constants.VELOCITY_CONVERSION_FACTOR);
    }

    @Override
    public void periodic() {
    mOdometry.update(
        mGyro.getRotation2d(), mLeftLeader.getEncoder().getPosition(), mRightLeader.getEncoder().getPosition());
    }

    public Pose2d getPose() {
        return mOdometry.getPoseMeters();
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(mLeftLeader.getEncoder().getVelocity(), mRightLeader.getEncoder().getVelocity());
    }

    public void resetOdometry(Pose2d pose) {
        resetEncoders();
        mOdometry.resetPosition(pose, mGyro.getRotation2d());
    }

    public void tankDriveVolts(double leftVolts, double rightVolts) {
        mLeftLeader.setVoltage(leftVolts);
        mRightLeader.setVoltage(rightVolts);
        mDrive.feed();
    }

    public void resetEncoders() {
        mLeftLeader.getEncoder().setPosition(0);
        mRightLeader.getEncoder().setPosition(0);
    }

    public double getAverageEncoderDistance() {
        return (mLeftLeader.getEncoder().getPosition() + mRightLeader.getEncoder().getPosition()) / 2;
    }

    public void drive(double f, double t) { // our drive method
        mDrive.arcadeDrive(f, t); // this method will be what actually converts our joystick values into motor voltages, which are outputted automatically to the NEOs
    }

    public void resetGyro() { // method to reset the gyroscope heading to 0
        mGyro.reset(); // reset gyroscope heading to 0
    }

    public double getHeading() {
        return mGyro.getRotation2d().getDegrees();
    }

    public double getTurnRate() {
        return -mGyro.getRate();
    }

        
    public static DriveTrain getInstance() { // method to get the static instance of the drivetrain
        if (mInstance == null) { // if the drivetrain is not defined
            mInstance = new DriveTrain(); // define it
        }
        return mInstance; // return the drivetrain instance
    }
}
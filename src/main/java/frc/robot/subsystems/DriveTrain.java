package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private static DriveTrain mInstance; // defining static instance of drivetrain

    private CANSparkMax mLeftLeader; // defining spark max objects for interface with spark max motorcontroller hardware
    private CANSparkMax mRightLeader; // ^^^
    
    private CANSparkMax mLeftFollower; // ^^^
    private CANSparkMax mRightFollower; // ^^^

    private RelativeEncoder mLeftEncoder; // defining NEO's built in encoder for use with PID control
    private RelativeEncoder mRightEncoder; // ^^^

    private SparkMaxPIDController mLeftController; // defining PID controller for use with driving a certain distance
    private SparkMaxPIDController mRightController; // ^^^

    private DifferentialDrive mDrive; // defining our drive base

    private ADXRS450_Gyro mGyro; // defining our gyroscope
    
    private double mSetpoint; // defining a double that represents a setpoint for PID control
    private double mAngle;

    private double mLeftDefault = 0.5; // default max speeds
    private double mRightDefault = 0.5; // ^^^

    public DriveTrain() {
        mLeftLeader = new CANSparkMax(1, MotorType.kBrushless); // initializing motorcontroller object with the CAN ID 1, which was set when we imaged the spark maxes with the proper firmware
        mRightLeader = new CANSparkMax(2, MotorType.kBrushless); // ^^^

        mLeftFollower = new CANSparkMax(3, MotorType.kBrushless); // ^^^
        mRightFollower = new CANSparkMax(4, MotorType.kBrushless); // ^^^


        mLeftEncoder = mLeftLeader.getEncoder(); // initializing the encoder object as the built in encoder from the left leader NEO
        mRightEncoder = mRightLeader.getEncoder(); // ^^^

        // manually move robot 10 feet. this value is equal to the number outputted by the smartdashboard for "Left Encoder Ticks"

        mLeftController = mLeftLeader.getPIDController(); // initializing PID controller
        mRightController = mRightLeader.getPIDController(); // ^^^

        mDrive = new DifferentialDrive(mLeftLeader, mRightLeader); // initializing our drivebase with two motors - left and right leaders

        mGyro = new ADXRS450_Gyro(); // initializing our gyroscope
        mGyro.calibrate(); // calibrating our gyroscope

        mLeftFollower.follow(mLeftLeader); // setting one NEO to follow the other on the left side
        mRightFollower.follow(mRightLeader); // ^^^

        mLeftController.setP(Constants.kP); // setting PID constants
        mLeftController.setI(Constants.kI); // ^^^
        mLeftController.setD(Constants.kD); // ^^^
        
        mRightController.setP(Constants.kP); // ^^^
        mRightController.setI(Constants.kI); // ^^^
        mRightController.setD(Constants.kD); // ^^^

        mLeftEncoder.setPositionConversionFactor(1);
        mRightEncoder.setPositionConversionFactor(1);

        mDrive.setSafetyEnabled(false); // weird error occurs when this isnt there - probably don't actually do this
    }
    
    public static DriveTrain getInstance() { // method to get the static instance of the drivetrain
        if (mInstance == null) { // if the drivetrain is not defined
            mInstance = new DriveTrain(); // define it
        }
        return mInstance; // return the drivetrain instance
    }

    public void drive(double f, double t) { // our drive method
        mDrive.arcadeDrive(f, t); // this method will be what actually converts our joystick values into motor voltages, which are outputted automatically to the NEOs

        SmartDashboard.putNumber("Left Encoder Ticks", mLeftEncoder.getPosition()); // debug
        SmartDashboard.putNumber("Right Encoder Ticks", mRightEncoder.getPosition()); // ^^^
    }

    public void setSetpoint(double s) { // method to set the setpoint for driving a distance
        mSetpoint = (Units.inchesToMeters(s) * (20/1.27));
    }

    public void drivePID(double speed) { // actually driving a specified distance
        mLeftController.setOutputRange(-speed, speed); // setting the maximum speeds of our motors while in PID control
        mRightController.setOutputRange(-speed, speed);

        mLeftController.setReference(-mSetpoint, CANSparkMax.ControlType.kPosition); // actually setting the drivetrain to drive the specified distance as definied by the setpoint
        mRightController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition); // ^^^\
        SmartDashboard.putNumber("setpoint", mSetpoint);

        SmartDashboard.putNumber("Left Encoder Ticks", mLeftEncoder.getPosition()); // debug
        SmartDashboard.putNumber("Right Encoder Ticks", mRightEncoder.getPosition());
    }

    public void drivePID() { // ^^^ except uses the default speeds
        mLeftController.setOutputRange(-mLeftDefault, mLeftDefault); // ^^^
        mRightController.setOutputRange(-mRightDefault, mRightDefault); // ^^^
        SmartDashboard.putNumber("setpoint", mSetpoint);

        mLeftController.setReference(-mSetpoint, CANSparkMax.ControlType.kPosition); // ^^^
        mRightController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition); // ^^^

        SmartDashboard.putNumber("Left Encoder Ticks", mLeftEncoder.getPosition()); // debug
        SmartDashboard.putNumber("Right Encoder Ticks", mRightEncoder.getPosition());
    }

    public boolean atSetpoint() { // method to check to see if the encoder position = the setpoint
        if (Math.abs(mSetpoint) <= Math.abs(mLeftEncoder.getPosition())) { // if the encoder position >= the setpoint, use absolute value because both values are vectors
            return true; // return true
        }
        return false; // return false
    }

    public void setAngle(double angle) { // method to set the angle for use in the turn method
        mAngle = angle; // setting the instance variable to be the parameter
    }

    public boolean turn() { // method to turn the robot to a specific angle
        double currentAngle = mGyro.getAngle();
        if (Math.abs(currentAngle) >= Math.abs(mAngle)) {
            mDrive.arcadeDrive(0, 0);
            return true;
        }

        if (mAngle < 0) {
            mDrive.arcadeDrive(-0.35, 0);
        }

        if (mAngle > 0) {
            mDrive.arcadeDrive(0.35, 0);
        }
        return false;
    }

    public boolean atAngle() {
        return (mAngle == mGyro.getAngle());
    }
    
    public void resetEncoders() { // method to reset the encoders to 0
        mLeftEncoder.setPosition(0); // resetting encoder position
        mRightEncoder.setPosition(0); // ^^^
    }

    public void resetGyro() { // method to reset the gyroscope heading to 0
        mGyro.reset(); // reset gyroscope heading to 0
    }
}
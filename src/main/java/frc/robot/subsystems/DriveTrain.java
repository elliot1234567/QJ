package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private static DriveTrain mInstance;

    private CANSparkMax mLeftLeader;
    private CANSparkMax mRightLeader;
    
    private CANSparkMax mLeftFollower;
    private CANSparkMax mRightFollower;

    private RelativeEncoder mLeftEncoder;
    private RelativeEncoder mRightEncoder;

    private SparkMaxPIDController mLeftController;
    private SparkMaxPIDController mRightController;

    private DifferentialDrive mDrive;

    private ADXRS450_Gyro mGyro;
    
    private double mSetpoint;

    private double mLeftDefault = 0.5;
    private double mRightDefault = 0.5;

    public DriveTrain() {
        mLeftLeader = new CANSparkMax(1, MotorType.kBrushless);
        mRightLeader = new CANSparkMax(2, MotorType.kBrushless);

        mLeftFollower = new CANSparkMax(3, MotorType.kBrushless);
        mRightFollower = new CANSparkMax(4, MotorType.kBrushless);

        mLeftEncoder = mLeftLeader.getEncoder();
        mRightEncoder = mRightLeader.getEncoder();

        mLeftController = mLeftLeader.getPIDController();
        mRightController = mRightLeader.getPIDController();

        mDrive = new DifferentialDrive(mLeftLeader, mRightLeader);

        mGyro = new ADXRS450_Gyro();
        mGyro.calibrate();

        mLeftFollower.follow(mLeftLeader);
        mRightFollower.follow(mRightLeader);
        mLeftController.setP(Constants.kP);
        mLeftController.setI(Constants.kI);
        mLeftController.setD(Constants.kD);
        mRightController.setP(Constants.kP);
        mRightController.setI(Constants.kI);
        mRightController.setD(Constants.kD);


        mDrive.setSafetyEnabled(false);
    }

    public void resetEncoders() {
        mLeftEncoder.setPosition(0);
        mRightEncoder.setPosition(0);
    }

    public void drive(double f, double t) {
        mDrive.arcadeDrive(f, t);
    }

    public void setSetpoint(double s) {
        mSetpoint = s * (Constants.GEAR_BOX_RATIO / Constants.WHEEL_CIRCUMFERENCE);
    }

    public void drivePID(double speed) {
        mLeftController.setOutputRange(-speed, speed);
        mRightController.setOutputRange(-speed, speed);

        mLeftController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition);
        mRightController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition);
    }

    public void drivePID() {
        mLeftController.setOutputRange(-mLeftDefault, mLeftDefault);
        mRightController.setOutputRange(-mRightDefault, mRightDefault);

        mLeftController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition);
        mRightController.setReference(mSetpoint, CANSparkMax.ControlType.kPosition);
    }

    public void resetGyro() {
        mGyro.reset();
    }

    public boolean atSetpoint() {
        if (Math.abs(mSetpoint) <= Math.abs(mLeftEncoder.getPosition())) {
            System.out.print("\n\nyay\n\n");
            return true;
        }
        return false;
    }

    public static DriveTrain getInstance() {
        if (mInstance == null) {
            mInstance = new DriveTrain();
        }
        return mInstance;
    }
}
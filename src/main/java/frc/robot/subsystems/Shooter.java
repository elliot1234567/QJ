package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private static Shooter mInstance;

    private final CANSparkMax mLeader = new CANSparkMax(6, MotorType.kBrushless);
    private final CANSparkMax mFollower = new CANSparkMax(7, MotorType.kBrushless);

    private final RelativeEncoder mLeaderEncoder = mLeader.getEncoder();

    private static BooleanSupplier mFire;

    private static double mCurrentSpeed = 0;

    public Shooter() {
        mFollower.follow(mLeader, true);
        mLeader.setIdleMode(IdleMode.kCoast);
    }

    public static Shooter getInstance() {
        if (mInstance == null) {
            mInstance = new Shooter();
        }
        return mInstance;
    }

    public void shoot() {
        if (mLeaderEncoder.getVelocity() <= ((mCurrentSpeed * Constants.kMaxRPMs) + Constants.kShootingTolerance) && mLeaderEncoder.getVelocity() >= ((mCurrentSpeed * Constants.kMaxRPMs) - Constants.kShootingTolerance)) {
            mFire = () -> true;
        } else {
            mFire = () -> false;
        }
        mLeader.set(mCurrentSpeed);
    }

    public void autoShoot(double speed) {
        if (mLeaderEncoder.getVelocity() <= ((speed * Constants.kMaxRPMs) + Constants.kShootingTolerance) && mLeaderEncoder.getVelocity() >= ((speed * Constants.kMaxRPMs) - Constants.kShootingTolerance)) {
            mFire = () -> true;
        } else {
            mFire = () -> false;
        }
        mLeader.set(speed);
    }

    public void variablePower(double power) {
        mLeader.set(power);
    }

    public void antiShooter() {
        mLeader.set(-1);
    }

    public void wrongColor() {
        mLeader.set(0.1);
    }

    public BooleanSupplier getFire() {
        return mFire;
    }

    public void shooterOff() {
        mLeader.set(0);
    }

    public void setSpeed(double speed) {
        mCurrentSpeed = speed;
    }
}
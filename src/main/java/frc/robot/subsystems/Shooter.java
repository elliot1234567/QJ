package frc.robot.subsystems;

import java.util.function.BooleanSupplier;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase { // defining class as a child of the subsystembase class (subclassing, inheritance)
    private static Shooter mInstance; // static variable for shooter instance

    private CANSparkMax mLeader; // defining spark max object for motor
    private CANSparkMax mFollower; // ^^^

    private RelativeEncoder mLeaderEncoder; // defining encoder for interface with the NEO encoder

    private BooleanSupplier mFire; // defining a booleansupplier for checking whether the shooter is up to speed

    private double mCurrentSpeed = 0; // defining double for the current speed setting of the flywheel

    public Shooter() {
        mLeader = new CANSparkMax(6, MotorType.kBrushless); // initializing the spark max with CAN ID 6
        mFollower = new CANSparkMax(7, MotorType.kBrushless); // ^^^

        mLeaderEncoder = mLeader.getEncoder(); // initializing encoder for interface with the NEO

        mFollower.follow(mLeader, true); // set the second motor to follow the first but with an inverted speed (1 = -1, -1 = 1)
        mLeader.setIdleMode(IdleMode.kCoast); // setting the motor to coast to a stop
    }

    public static Shooter getInstance() { // method to get the instance of the shooter
        if (mInstance == null) { // if the instance isn't defined
            mInstance = new Shooter(); // define it
        }
        return mInstance; // return the instance
    }

    public void rampUp() { // method to ramp up the shooter speed and check if it is at the desired speed
        if (mLeaderEncoder.getVelocity() <= ((mCurrentSpeed * Constants.kMaxRPMs) + Constants.kShootingTolerance) && mLeaderEncoder.getVelocity() >= ((mCurrentSpeed * Constants.kMaxRPMs) - Constants.kShootingTolerance)) { // if the speed is within a certain tolerance of the desired speed
            mFire = () -> true; // set mFire to true
        } else { // otherwise
            mFire = () -> false; // set mFire to false
        }
        mLeader.set(mCurrentSpeed); // actually set the speed
    }

    public void autoRampUp(double speed) { // ^^^ except with a parameter for the speed so you dont need to set it
        if (mLeaderEncoder.getVelocity() <= ((speed * Constants.kMaxRPMs) + Constants.kShootingTolerance) && mLeaderEncoder.getVelocity() >= ((speed * Constants.kMaxRPMs) - Constants.kShootingTolerance)) { // ^^^
            mFire = () -> true; // ^^^
        } else { // ^^^
            mFire = () -> false; // ^^^
        }
        mLeader.set(speed); // ^^^
    }

    public void shoot() { // method to set the speed of the shooter
        mLeader.set(mCurrentSpeed); // set speed
    }

    public void autoShoot(double power) { // ^^^ with speed parameter so you dont need to set the power
        mLeader.set(power); // ^^^
    }

    public BooleanSupplier getFire() { // method to get whether we are up to speed
        return mFire;
    }

    public void setSpeed(double speed) { // method to set the speed variable for use in the shoot methods
        mCurrentSpeed = speed;
    }
}
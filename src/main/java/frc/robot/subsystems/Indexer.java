package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase { // defining class as a child of the subsystembase class (subclassing, inheritance)
    private static Indexer mInstance; // defining a variable for the static instance of the indexer

    private CANSparkMax mIndexer; // defining a spark max object for the indexer

    public Indexer() { // constructor - no parameters
        mIndexer = new CANSparkMax(5, MotorType.kBrushless); // initializing the indexer motor with CAN ID 5 
        mIndexer.setIdleMode(IdleMode.kBrake);
    }

    public static Indexer getInstance() { // method to get the static instance of the 
        if (mInstance == null) { // if the instance isn't defined
            mInstance = new Indexer(); // define it
        }
        return mInstance; // return mIndexer
    }

    public void setSpeed(double speed) { // method to set the speed of the motor
        mIndexer.set(speed); // set the speed
    }
}

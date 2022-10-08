package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
    private static Indexer mInstance;
    private CANSparkMax mIndexer;

    public Indexer() {
        mIndexer = new CANSparkMax(5, MotorType.kBrushless);
    }

    public static Indexer getInstance() {
        if (mInstance == null) {
            mInstance = new Indexer();
        }
        return mInstance;
    }

    public void setSpeed(double speed) {
        mIndexer.set(0);
    }
}

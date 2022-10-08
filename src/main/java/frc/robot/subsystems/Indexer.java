package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
    static Indexer mInstance;
    CANSparkMax indexer = new CANSparkMax(5, MotorType.kBrushless);

    public Indexer() {

    }

    public static Indexer getInstance() {
        if (mInstance == null) {
            mInstance = new Indexer();
        }
        return mInstance;
    }

    public void setSpeed(double speed) {
        indexer.set(0);
    }
}

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    CANSparkMax climber;

    public Climber() {
        climber = new CANSparkMax(8, MotorType.kBrushless);
        climber.setIdleMode(IdleMode.kBrake);
    }

    public void up() {
        climber.set(1);
    }

    public void down() {
        climber.set(-1);
    }

    public void off() {
        climber.set(0);
    }
}

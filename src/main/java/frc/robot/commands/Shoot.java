package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
    private static Shooter mShooter = Shooter.getInstance();
    private BooleanSupplier mShoot;
    private BooleanSupplier mPreset1;
    private BooleanSupplier mPreset2;
    private BooleanSupplier mPreset3;
    private BooleanSupplier mPreset4;


    public Shoot(BooleanSupplier s, BooleanSupplier p1, BooleanSupplier p2, BooleanSupplier p3, BooleanSupplier p4) {
        mShoot = s;
        mPreset1 = p1;
        mPreset2 = p2;
        mPreset3 = p3;
        mPreset4 = p4;

        addRequirements(mShooter);
    }

    @Override
    public void execute() {
        if (mPreset1.getAsBoolean()) {
            mShooter.setSpeed(Constants.cargoRingSpeed);
        }

        else if (mPreset2.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset1);
        }

        else if (mPreset3.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset2);
        }

        else if (mPreset4.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset3);
        }

        else if (mShoot.getAsBoolean()) {
            mShooter.shoot();
        }

        else {
            mShooter.shooterOff();
        }
    }
}

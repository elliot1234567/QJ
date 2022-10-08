package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
    private static Shooter mShooter = Shooter.getInstance();
    private BooleanSupplier shoot;
    private BooleanSupplier preset1;
    private BooleanSupplier preset2;
    private BooleanSupplier preset3;
    private BooleanSupplier preset4;


    public Shoot(BooleanSupplier s, BooleanSupplier p1, BooleanSupplier p2, BooleanSupplier p3, BooleanSupplier p4) {
        shoot = s;
        preset1 = p1;
        preset2 = p2;
        preset3 = p3;
        preset4 = p4;

        addRequirements(mShooter);
    }

    @Override
    public void execute() {
        if (preset1.getAsBoolean()) {
            mShooter.setSpeed(Constants.cargoRingSpeed);
        }

        else if (preset2.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset1);
        }

        else if (preset3.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset2);
        }

        else if (preset4.getAsBoolean()) {
            mShooter.setSpeed(Constants.preset3);
        }

        else if (shoot.getAsBoolean()) {
            mShooter.shoot();
        }

        else {
            mShooter.shooterOff();
        }
    }
}

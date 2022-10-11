package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase { // define class as child of CommandBase class (subclassing, inheritance)
    private static Shooter mShooter = Shooter.getInstance(); // get static instance of the shooter

    private BooleanSupplier mShoot; // defining booleansupplier - this will be our button state indicator
    private BooleanSupplier mPreset1; // ^^^
    private BooleanSupplier mPreset2; // ^^^
    private BooleanSupplier mPreset3; // ^^^
    private BooleanSupplier mPreset4; // ^^^
    private BooleanSupplier mWrongColor; // ^^^


    public Shoot(BooleanSupplier s, BooleanSupplier p1, BooleanSupplier p2, BooleanSupplier p3, BooleanSupplier p4, BooleanSupplier wc) { // constructor for whenever we construct a Shoot object in the robot container, the parameters correspond to the buttons we want to control the shooter with
        mShoot = s; // setting the booleansupplier value to be parameter which is a button.
        mPreset1 = p1; // ^^^
        mPreset2 = p2; // ^^^
        mPreset3 = p3; // ^^^
        mPreset4 = p4; // ^^^
        mWrongColor = wc; // ^^^

        addRequirements(mShooter); // required method call for adding requirements to a command
    }

    @Override
    public void execute() { // overridden method from commandbase
        if (mPreset1.getAsBoolean()) { // if the button to select the 1st preset speed is pressed
            mShooter.setSpeed(Constants.cargoRingSpeed); // set the speed of the shooter to the speed as specified in the Constants.java file
        }

        else if (mPreset2.getAsBoolean()) { // ^^^
            mShooter.setSpeed(Constants.preset1); // ^^^
        }

        else if (mPreset3.getAsBoolean()) { // ^^^
            mShooter.setSpeed(Constants.preset2); // ^^^
        }

        else if (mPreset4.getAsBoolean()) { // ^^^
            mShooter.setSpeed(Constants.preset3); // ^^^
        }
        
        else if (mShoot.getAsBoolean() && mWrongColor.getAsBoolean()) {
            mShooter.autoShoot(0.1);
        }

        else if (mShoot.getAsBoolean()) { // if the button to shoot the ball is pressed
            mShooter.shoot(); // call the shoot method from the shooter class
        }

        else { // otherwise
            mShooter.setSpeed(0); // shooter stays off
        }
    }
}

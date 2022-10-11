// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // robot constants
    public static final double WHEEL_CIRCUMFERENCE = Units.inchesToMeters(6) * Math.PI; // circumference of the wheel
    public static final double GEAR_BOX_RATIO = 10.71; // ratio of the drive gear box
    public static final double ELLIOT_COEFFICIENT = 5.0/4.0;
    public static final double POSITION_CONVERSION_FACTOR = 0; // position conversion factor for drive train


    // controller Gains
    public static final double kP = 1.8777; // PID P term for drive train
    public static final double kI = 0; // PID I term for drive train
    public static final double kD = 0.14398; // PID D term for drive train

    // trajectory constants
    public static final double kMaxRPMs = 5700; // max NEO RPMs
    public static final double kShootingTolerance = 10; // 10 rpms for shooter

    // shooting constants
    public static final double cargoRingSpeed = 0.4; // .set() value for speed at the cargo ring
    public static final double preset1 = 0.5; // ^^^
    public static final double preset2 = 0.75; // ^^^
    public static final double preset3 = 1; // ^^^
}
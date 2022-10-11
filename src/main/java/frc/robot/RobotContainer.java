// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.commands.Drive;
import frc.robot.commands.Index;
import frc.robot.commands.Shoot;
import frc.robot.commands.autoroutines.OneBall;
import frc.robot.subsystems.DriveTrain;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private DriveTrain mDriveTrain = DriveTrain.getInstance(); // getting instance of the drivetrain
  private Indexer mIndexer = Indexer.getInstance(); // getting instance of the indexer
  private Shooter mShooter = Shooter.getInstance(); // getting instance of the shooter

  private Drive mDrive; // defining drive command object
  private Index mIndex; // defining index command object
  private Shoot mShoot; // defining shoot command object

  SendableChooser<CommandGroupBase> mAutoChooser = new SendableChooser<CommandGroupBase>(); // defining autochooser smartdashboard widget

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    XboxController mController = new XboxController(0);
    Joystick mJoystick = new Joystick(1);

    mDrive = new Drive(() -> mController.getRightX(), () -> mController.getLeftY());
    mIndex = new Index(() -> mJoystick.getRawButton(2), () -> mJoystick.getRawButton(3));
    mShoot = new Shoot(() -> mJoystick.getRawButton(1), () -> mJoystick.getRawButton(7), () -> mJoystick.getRawButton(8), () -> mJoystick.getRawButton(9), () -> mJoystick.getRawButton(10), () -> mJoystick.getRawButton(11));

    mDriveTrain.setDefaultCommand(mDrive);
    mIndexer.setDefaultCommand(mIndex);
    mShooter.setDefaultCommand(mShoot);
  }

  public Command getAutonomousCommand() {
    return new OneBall();
  }
}
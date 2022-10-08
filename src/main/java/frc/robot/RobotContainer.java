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
import frc.robot.commands.Routine;
import frc.robot.commands.Shoot;
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
  DriveTrain driveTrain = DriveTrain.getInstance();
  Indexer indexer = Indexer.getInstance();
  Shooter shooter = Shooter.getInstance();

  Drive drive;
  Index index;
  Shoot shoot;

  SendableChooser<CommandGroupBase> autoChooser;

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
    XboxController controller = new XboxController(0);
    Joystick joystick = new Joystick(1);

    // index = new Index(indexer, () -> controller.getAButton(), () -> controller.getBButton());
    // lift = new Lift(lifter, () -> controller.getXButton(), () -> controller.getYButton());
    drive = new Drive(() -> controller.getRightX(), () -> controller.getLeftY());
    index = new Index(() -> joystick.getRawButton(2), () -> joystick.getRawButton(3));
    shoot = new Shoot(() -> joystick.getRawButton(1), () -> joystick.getRawButton(7), () -> joystick.getRawButton(8), () -> joystick.getRawButton(9), () -> joystick.getRawButton(10));

    driveTrain.setDefaultCommand(drive);
    indexer.setDefaultCommand(index);
    shooter.setDefaultCommand(shoot);
  }

  public Command getAutonomousCommand() {
    return new Routine();
  }
}
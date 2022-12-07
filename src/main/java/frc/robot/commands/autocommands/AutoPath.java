package frc.robot.commands.autocommands;
import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class AutoPath extends CommandBase {
    private Trajectory mTrajectory;
    private RamseteCommand mCommand;

    public AutoPath(String jsonPath) {
        try {
            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(jsonPath);
            mTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
         } catch (IOException ex) {
            DriverStation.reportError("Unable to open trajectory: " + jsonPath, ex.getStackTrace());
         }

        mCommand =
            new RamseteCommand(
                mTrajectory,
                DriveTrain.getInstance()::getPose,
                new RamseteController(Constants.kRamseteB, Constants.kRamseteZeta),
                new SimpleMotorFeedforward(
                    Constants.ksVolts,
                    Constants.kvVoltSecondsPerMeter,
                    Constants.kaVoltSecondsSquaredPerMeter),
                Constants.kDriveKinematics,
                DriveTrain.getInstance()::getWheelSpeeds,
                new PIDController(Constants.kPDriveVel, 0, 0),
                new PIDController(Constants.kPDriveVel, 0, 0),
                DriveTrain.getInstance()::tankDriveVolts,
                DriveTrain.getInstance());
    }
    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        mCommand.execute();
    }

    public void end(boolean interrupted) {
        DriveTrain.getInstance().tankDriveVolts(0, 0);
    }

    public boolean isFinished() {
        return mCommand.isFinished();
    }
}

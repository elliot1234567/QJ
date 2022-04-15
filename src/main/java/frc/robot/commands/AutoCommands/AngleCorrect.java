package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AngleCorrect extends CommandBase {
    DriveTrain driveTrain;
    double angle;
    boolean finished;

    public AngleCorrect(DriveTrain DT, double a) {
        driveTrain = DT;
        angle = a;

        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        reset();
        driveTrain.setAngle(angle);
    }

    @Override
    public void execute() {
        finished = driveTrain.rotatePID();
        driveTrain.update();
    }
    @Override
    public void end(boolean interrupted) {
        finished = false;
        reset();
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    private void reset() {
        driveTrain.resetGyro();
        driveTrain.resetEncoders();
        driveTrain.setCurrentHedding();
    }
}
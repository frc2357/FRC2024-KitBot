package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoStopDriveCommand {
    public static Command createAutoStopDriveCommand(DriveSubsystem driveSub, double timeMillis,
            double speed, double turn, BooleanSupplier stopper) {
        return new AutoDriveCommand(driveSub, timeMillis, speed, turn).until(stopper);
    }
}

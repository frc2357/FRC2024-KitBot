package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoDriveCommandGroup extends SequentialCommandGroup {
    public AutoDriveCommandGroup() {
        addCommands(new AutoDriveCommand(2000, 0.25, 0.0)); // Drive forward for 2000ms
        addCommands(new WaitCommand(0.25)); // Wait 0.25 seconds / 250ms
        addCommands(new AutoDriveCommand(500, 0.2, 0.25)); // Turn right for 500ms
        addCommands(new WaitCommand(0.25));
        addCommands(new AutoDriveCommand(1000, 0.3, 0.0)); // Drive forward for 1000ms
    }

    @Override
    public String toString() {
        return "Auto Drive Command Group";
    }
}

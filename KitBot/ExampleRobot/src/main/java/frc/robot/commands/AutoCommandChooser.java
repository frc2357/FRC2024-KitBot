package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class AutoCommandChooser {
    private Command[] m_autoCommands;
    private SendableChooser<Command> m_chooser;

    public AutoCommandChooser(DriveSubsystem driveSub) {

        DigitalInput wallSensorIn = new DigitalInput(Constants.WALL_SENSOR_DIO);
        BooleanSupplier wallSensor = () -> {
            return !wallSensorIn.get();
        };

        m_autoCommands = new Command[] {
                new AutoDriveCommand(driveSub, 1000, 0.25, 0),
                new AutoDriveCommandGroup(driveSub),
                AutoStopDriveCommand.createAutoStopDriveCommand(driveSub, 2000, -0.2, 0.0, wallSensor)
        };

        m_chooser = new SendableChooser<>();

        m_chooser.setDefaultOption("None", new WaitCommand(0));
        for (Command autoCommand : m_autoCommands) {
            m_chooser.addOption(autoCommand.toString(), autoCommand);
        }

        SmartDashboard.putData("Auto chooser", m_chooser);
    }

    public Command getSelectedAutoCommand() {
        return m_chooser.getSelected();
    }
}
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveProportionalCommand extends CommandBase {

    public DriveProportionalCommand() {

        super.addRequirements(Robot.drive);
    }

    @Override 
    public void execute() {
        Robot.drive.driveProportionalWithStick(Robot.controller.getLeftY(), Robot.controller.getRightX());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

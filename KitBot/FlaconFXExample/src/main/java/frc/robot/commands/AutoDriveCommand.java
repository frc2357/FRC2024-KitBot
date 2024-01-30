package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoDriveCommand extends CommandBase {

    private double m_timeMillis, m_timeTotal, m_turn, m_speed;

    public AutoDriveCommand(double timeMillis, double speed, double turn) {
        m_timeMillis = timeMillis;
        m_turn = turn;
        m_speed = speed;

        addRequirements(Robot.drive);
    }

    @Override
    public void initialize() {
        Robot.drive.driveProportional(m_speed, m_turn);
        m_timeTotal = System.currentTimeMillis() + m_timeMillis;
    }

    @Override
    public boolean isFinished() {
        return m_timeTotal < System.currentTimeMillis();
    }

    @Override
    public void end(boolean interrupted) {
        Robot.drive.driveProportional(0.0, 0.0);
    }

    @Override
    public String toString() {
        return "Auto Drive";
    }
}
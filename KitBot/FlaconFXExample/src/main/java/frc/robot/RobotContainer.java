// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoCommandChooser;
import frc.robot.commands.DriveProportionalCommand;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private AutoCommandChooser m_chooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    WPI_TalonFX leftFalconMaster = new WPI_TalonFX(Constants.CAN_ID.DRIVE_MOTOR_LEFT_1);

    WPI_TalonFX[] leftFalconSlaves = new WPI_TalonFX[] {
        new WPI_TalonFX(Constants.CAN_ID.DRIVE_MOTOR_LEFT_2) };

    WPI_TalonFX rightFalconMaster = new WPI_TalonFX(Constants.CAN_ID.DRIVE_MOTOR_RIGHT_1);

    WPI_TalonFX[] rightFalconSlaves = new WPI_TalonFX[] {
        new WPI_TalonFX(Constants.CAN_ID.DRIVE_MOTOR_LEFT_2) };

    Robot.drive = new DriveSubsystem(leftFalconMaster, leftFalconSlaves, rightFalconMaster, rightFalconSlaves,
        Constants.DRIVE.IS_RIGHT_DRIVE_INVERTED);

    Robot.controller = new XboxController(Constants.CONTROLLER.PORT);

    Robot.drive.setDefaultCommand(new DriveProportionalCommand());

    m_chooser = new AutoCommandChooser();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //return new AutoDriveCommand(m_driveSub, 1000, 0.25, 0.0);

    //return new AutoDriveCommandGroup(m_driveSub);

    return m_chooser.getSelectedAutoCommand();
  }
}

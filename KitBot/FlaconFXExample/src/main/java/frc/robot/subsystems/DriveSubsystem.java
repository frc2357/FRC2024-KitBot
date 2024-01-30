package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

    private WPI_TalonFX m_leftFalconMaster;
    private WPI_TalonFX m_rightFalconMaster;

    private WPI_TalonFX[] m_leftFalconFollowers;
    private WPI_TalonFX[] m_rightFalconFollowers;

    double m_deadband;

    public DriveSubsystem(WPI_TalonFX leftFalconMaster, WPI_TalonFX[] leftFalconFollowers,
            WPI_TalonFX rightFalconMaster,
            WPI_TalonFX[] rightFalconFollowers, boolean isRightInverted) {
        m_leftFalconMaster = leftFalconMaster;
        m_rightFalconMaster = rightFalconMaster;

        m_leftFalconFollowers = leftFalconFollowers;
        m_rightFalconFollowers = rightFalconFollowers;

        m_leftFalconMaster.configFactoryDefault();
        m_leftFalconMaster.setInverted(!isRightInverted);

        m_rightFalconMaster.configFactoryDefault();
        m_rightFalconMaster.setInverted(isRightInverted);

        for (WPI_TalonFX follower : m_leftFalconFollowers) {
            follower.configFactoryDefault();
            follower.setInverted(!isRightInverted);
            follower.setNeutralMode(NeutralMode.Brake);
            follower.follow(m_leftFalconMaster, FollowerType.PercentOutput);
        }

        for (WPI_TalonFX follower : m_rightFalconFollowers) {
            follower.configFactoryDefault();
            follower.setInverted(isRightInverted);
            follower.setNeutralMode(NeutralMode.Brake);
            follower.follow(m_rightFalconMaster, FollowerType.PercentOutput);
        }
    }

    public void driveProportionalWithStick(double speed, double turn) {
        speed = -1 * MathUtil.applyDeadband(speed, Constants.DRIVE.DEADBAND);
        turn = MathUtil.applyDeadband(turn, Constants.DRIVE.DEADBAND);
        driveProportional(speed, turn);
    }

    /**
     * 
     * @param speed -1 to 1
     * @param turn  -1 to 1
     */
    public void driveProportional(double speed, double turn) {
        double leftProportion = speed + turn;
        double rightProportion = speed - turn;

        leftProportion = MathUtil.clamp(leftProportion, -1.0, 1.0);

        rightProportion = MathUtil.clamp(rightProportion, -1.0, 1.0);

        m_leftFalconMaster.set(leftProportion);
        m_rightFalconMaster.set(rightProportion);
    }
}

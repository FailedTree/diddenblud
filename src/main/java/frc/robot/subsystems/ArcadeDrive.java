package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class ArcadeDrive extends SubsystemBase {
    
    SparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    SparkMaxConfig leftLeaderConfig, rightLeaderConfig, leftFollowerConfig, rightFollowerConfig;
    DifferentialDrive drive;

    public ArcadeDrive(){
        // instantiate motors 
        leftLeader = new SparkMax(DriveConstants.LBId, MotorType.kBrushless);
        leftFollower = new SparkMax(DriveConstants.LFId, MotorType.kBrushless);
        rightFollower = new     SparkMax(DriveConstants.RFId, MotorType.kBrushless);
        rightLeader = new SparkMax(DriveConstants.RBId, MotorType.kBrushless);
        
        // instantiating configs
        leftLeaderConfig = new SparkMaxConfig();
        leftFollowerConfig = new SparkMaxConfig();
        rightFollowerConfig = new SparkMaxConfig();
        rightLeaderConfig = new SparkMaxConfig();

        // calling config methods
        leftLeaderConfig
        .idleMode(IdleMode.kBrake)
        .inverted(false);

        rightLeaderConfig
        .idleMode(IdleMode.kBrake)
        .inverted(true);

        leftFollowerConfig
        .idleMode(IdleMode.kBrake)
        .inverted(false)
        .follow(leftLeader);

        rightFollowerConfig
        .idleMode(IdleMode.kBrake)
        .inverted(false)
        .follow(rightLeader);

        leftLeader.configure(leftLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        drive = new DifferentialDrive(leftLeader, rightLeader);
    }

    /**
     * 
     * @param xSpeed straight line speed (Forward is positive)
     * @param zRotation rotational speed (counterclockwise is positive)
     */
    public void drive(double xSpeed, double zRotation){
        drive.arcadeDrive(xSpeed, zRotation);
    }


}

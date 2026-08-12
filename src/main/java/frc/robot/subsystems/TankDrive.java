package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveConstants;

public class TankDrive extends SubsystemBase {

    SparkMax LLeader, LFollow, RLeader, RFollow;
    SparkMaxConfig leftLeadConfig, leftFollowConfig, rightLeadConfig, rightFollowConfig;
    
    public TankDrive (){
        
        /*Instantiate Sparks*/
        LLeader = new SparkMax(DriveConstants.LBId, MotorType.kBrushless);
        LFollow = new SparkMax(DriveConstants.LFId, MotorType.kBrushless);
        RLeader = new SparkMax(DriveConstants.RFId, MotorType.kBrushless);
        RFollow = new SparkMax(DriveConstants.RBId, MotorType.kBrushless);

        /*Instantiate & Apply Configs*/
        //LEFT LEAD
        leftLeadConfig = new SparkMaxConfig();
        leftLeadConfig.inverted(false);
        leftLeadConfig.idleMode(IdleMode.kBrake)
        .smartCurrentLimit(90);
    
        LLeader.configure(leftLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //LEFT FOLLOW
        leftFollowConfig = new SparkMaxConfig();
        leftFollowConfig.inverted(false);
        leftFollowConfig.idleMode(IdleMode.kBrake)
        .smartCurrentLimit(90)
        .follow(LLeader);

        LFollow.configure(leftFollowConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //RIGHT LEAD
        rightLeadConfig = new SparkMaxConfig();
        rightLeadConfig.inverted(true);
        rightLeadConfig.idleMode(IdleMode.kBrake)
        .smartCurrentLimit(90);

        RLeader.configure(rightLeadConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        //RIGHT FOLLOW
        rightFollowConfig = new SparkMaxConfig();
        rightFollowConfig.inverted(true);
        rightFollowConfig.idleMode(IdleMode.kBrake)
        .smartCurrentLimit(90)
        .follow(RLeader);

        RFollow.configure(rightFollowConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void drive(CommandXboxController driverController){
        double leftPower = MathUtil.applyDeadband(-driverController.getLeftY() + driverController.getRightX(), 0.075)
        *Math.abs(MathUtil.applyDeadband(-driverController.getLeftY() + driverController.getRightX(), 0.075))
        *Math.abs(MathUtil.applyDeadband(-driverController.getLeftY() + driverController.getRightX(), 0.075));

        double rightPower = MathUtil.applyDeadband(-driverController.getLeftY() - driverController.getRightX(), 0.075)
        *Math.abs(MathUtil.applyDeadband(-driverController.getLeftY() - driverController.getRightX(), 0.075))
        *Math.abs(MathUtil.applyDeadband(-driverController.getLeftY() - driverController.getRightX(), 0.075));

        LLeader.set(leftPower);
        RLeader.set(rightPower);

    }


}

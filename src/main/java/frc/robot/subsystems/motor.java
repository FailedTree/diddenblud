package frc.robot.subsystems;


import java.lang.annotation.Target;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class motor extends SubsystemBase {
    
    SparkMax sparkMotor;
    SparkMaxConfig sparkMotorConfig;
    RelativeEncoder sparkEncoder;
    PIDController motorPid = new PIDController(0.1, 0, 0);

    public motor(){


        sparkMotor = new SparkMax(5, MotorType.kBrushless);
        sparkMotorConfig = new SparkMaxConfig();
        sparkMotorConfig.idleMode(IdleMode.kCoast).inverted(false);

        sparkMotor.configure(sparkMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        sparkEncoder = sparkMotor.getEncoder();
    }

    public void motorRun (double speed){
        sparkMotor.set(speed);
    }

    public void motorStop (){
        sparkMotor.set(0);
    }

    public double getPos(){
        return sparkEncoder.getPosition();
    }

    @Override
    public void periodic(){
        updateDashboard();
    }

    private void updateDashboard(){
        SmartDashboard.putNumber("position", getPos());
        SmartDashboard.putNumber("velocity", getVel());
    }

    public double getVel(){
        return sparkEncoder.getVelocity();
    }

    public void setPos (double position){
        motorPid.setSetpoint(position);
    }

    public void goPos (){
        sparkMotor.set(motorPid.calculate(getPos()));
    }


}
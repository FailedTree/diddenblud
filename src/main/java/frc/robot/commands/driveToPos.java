package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motor;

public class driveToPos extends Command {
    
    motor sparkMotor;
    double target = 10;
    


    public driveToPos(motor eSparkMotor){

        sparkMotor = eSparkMotor;
        addRequirements(eSparkMotor);
    }

    @Override
    public void initialize (){ 
        sparkMotor.setPos(target);
    }

    @Override
    public void execute (){
        sparkMotor.goPos();
    }

    @Override 
    public void end (boolean interrupted){
        sparkMotor.motorStop();
    }
}

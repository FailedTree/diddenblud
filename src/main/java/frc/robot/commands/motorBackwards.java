package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motor;

public class motorBackwards extends Command {
    
    motor sparkMotor;
   

    public motorBackwards(motor eSparkMotor){

        sparkMotor = eSparkMotor;
        addRequirements(sparkMotor);

    }

    @Override
    public void execute (){

        sparkMotor.motorRun(-0.123);
    }

    @Override
    public void end (boolean interrupted){
        sparkMotor.motorStop();

    }





}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.motor;

public class runMotor extends Command{

    motor motor;

    public runMotor(motor Motor){

        motor = Motor;
        addRequirements(motor);
    }

    @Override
    public void execute () {
        motor.motorRun(0.5);
    }

    @Override
    public void end (boolean interrupted){
        motor.motorStop();
    }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorTestSubsystem extends SubsystemBase {
    public final TalonFX motor;
    public final MotorVisualizer visualizer;
    private double currentSpeed = 0.0;

    public MotorTestSubsystem(int motorPort, MotorVisualizer visualizer) {
        this.motor = new TalonFX(motorPort);
        motor.setNeutralMode(NeutralModeValue.Brake);
        this.visualizer = visualizer;

    }

    public void setSpeed(double speed) {
        currentSpeed = speed;
        motor.set(speed);
    }

    public void stopMotor() {
        currentSpeed = 0.0;
        motor.stopMotor();
    }

    public double getSpeed() {
        return currentSpeed;
    }

  /**
   * Example command factory method.
   *
   *    @return a command
   */
    public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      visualizer.update(currentSpeed);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

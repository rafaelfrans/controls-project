// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MotorVisualizer extends SubsystemBase {

    private final Mechanism2d mechanism;
    private final MechanismRoot2d root;
    private final MechanismLigament2d[] blades = new MechanismLigament2d[3];

    double motorSpeed;
    double currentAngle;

    public MotorVisualizer() {
        mechanism = new Mechanism2d(500, 500);

        root = mechanism.getRoot("Center", 250, 250);

        for (int i = 0; i < 3; i++) {
            blades[i] = root.append(new MechanismLigament2d(
                "Blade" + i,
                100,
                i * 120  // 3 blades, 120° apart
            ));
            blades[i].setLineWeight(5);
        };

        //sending visual to the smart dashboard
        SmartDashboard.putData("Motor Visualizer", mechanism);
    }

    public void update(double speed) {
        // Convert motor speed (-1 to 1) into degrees per second
        double degreesPerSecond = speed * 360.0; 
        double deltaAngle = degreesPerSecond * 0.2;
    
        currentAngle += deltaAngle;
    
        // Wrap angle between 0-360
        if (currentAngle >= 360) currentAngle -= 360;
        if (currentAngle < 0) currentAngle += 360;
    
        for (int i = 0; i < blades.length; i++) {
            double baseAngle = i * 120;
            blades[i].setAngle(baseAngle + currentAngle);

            int strength = (int)(Math.abs(speed) * 255); // 0-255
            Color8Bit color;

            if (speed >= 0) {
                // Forward = green
                color = new Color8Bit(225, 255-strength, 255-strength);
            } else {
                // Reverse = red
                color = new Color8Bit(225-strength,225,225-strength);
            }
            blades[i].setColor(color);
        }
        
      }

  /**
   * Example command factory method.
   *
   * @return a command
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
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

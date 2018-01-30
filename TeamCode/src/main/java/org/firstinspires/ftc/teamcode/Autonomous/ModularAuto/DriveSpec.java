package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;

/**
 * Created by rostifar on 1/25/18.
 */

public class DriveSpec {
    public final float encodersPerRotation;
    public final float gearRatio;
    public final float wheelDiameter;

    public DriveSpec(float encodersPerRotation, float gearRatio, float wheelDiameter) {
        this.encodersPerRotation = encodersPerRotation;
        this.gearRatio = gearRatio;
        this.wheelDiameter = wheelDiameter;
    }
}

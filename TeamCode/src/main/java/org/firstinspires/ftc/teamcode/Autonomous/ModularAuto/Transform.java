package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;

import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;

/**
 * Created by rostifar on 1/25/18.
 */

public class Transform {
    public final Vector2 initialPosition;
    public final Vector2 forward;
    public final float currentAngle;

    public Transform(Vector2 initialPosition, Vector2 forward, float currentAngle) {
        this.initialPosition = initialPosition;
        this.forward = forward;
        this.currentAngle = currentAngle;
    }
}

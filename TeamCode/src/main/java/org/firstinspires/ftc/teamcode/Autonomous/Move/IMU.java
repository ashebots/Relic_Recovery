package org.firstinspires.ftc.teamcode.Autonomous.Move;

/**
 * Created by rostifar on 8/31/17.
 */

public interface IMU {
    enum Unit {
        RADIAN,
        DEGREE
    }

    int yaw();

    int pitch();

    int roll();
}

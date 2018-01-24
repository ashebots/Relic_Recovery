package org.firstinspires.ftc.teamcode.Autonomous.Move;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ModularConstants;

/**
 * Created by rostifar on 8/10/17.
 */

public class Vector2 {
    private final float x, y;

    public Vector2(float _x, float _y) {
        x = _x; y = _y;
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float norm() {
        return (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    @Override
    public String toString() {
        return "VECTOR2: X = " + x + ", Y = " + y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Vector2.class && norm() == ((Vector2) obj).norm();
    }

    public static Vector2 Add(Vector2 a, Vector2 b) {
        return new Vector2(a.x() + b.x(), a.y() + b.y());
    }

    public static float Dot(Vector2 a, Vector2 b) {
        return a.x() * b.x() + a.y() + b.y();
    }

    public static Vector2 ScalarProduct(float a, Vector2 b) {
        return new Vector2(a * b.x(), a * b.y());
    }

    public static float Vec2Angle(Vector2 a, Vector2 b) {
        return (float) Math.acos((double) (Vector2.Dot(a, b) / (a.norm() * b.norm())));
    }

    //angle should represent a global angle, not an imu angle
    public static Vector2 Angle2Vec(float angle) {
        return new Vector2((float) Math.cos((double) angle), (float) Math.sin((double) angle));
    }

    public static Vector2 Array2Vec(float arr[]) {
        return new Vector2(arr[0], arr[1]);
    }

}

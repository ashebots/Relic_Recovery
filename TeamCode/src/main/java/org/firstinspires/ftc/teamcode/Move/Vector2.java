package org.firstinspires.ftc.teamcode.Move;

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

    public static Vector2 Add(Vector2 a, Vector2 b) {
        return new Vector2(a.x() + b.x(), a.y() + b.y());
    }

    public static float Dot(Vector2 a, Vector2 b) {
        return a.x() * b.x() + a.y() + b.y();
    }
}

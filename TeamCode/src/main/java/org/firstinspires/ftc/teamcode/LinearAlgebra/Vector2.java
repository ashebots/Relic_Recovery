package org.firstinspires.ftc.teamcode.LinearAlgebra;

/**
 * Vector2 is a data structure that represents an element of the vector space R^2.
 * It's important to note that our R^2 vector space is over the real numbers rather than any other field.
 **/

public class Vector2 {
    private final float x, y;
    private final float _norm;

    public Vector2(float _x, float _y) {
        x = _x; y = _y;
        _norm = (float) Math.sqrt((Math.pow(x, 2) + Math.pow(y, 2)));
    }

    public float x() {
        return x;
    }

    public float y() {
        return y;
    }

    public float norm() {
        return _norm;
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

    public static Vector2 Normalize(Vector2 a) {
        final float norm = a.norm();
        return new Vector2(a.x() / norm, a.y() / norm);
    }

    //UnsignedVec2Angle uses the dot product to calculate vector angles. By definition, arccos: [-1, 1] -> [0, pi];
    //Therefore, UnsignedVec2Angle does not calculate signed angles.
    public static float UnsignedVec2Angle(Vector2 a, Vector2 b) {
        return (float) Math.acos((double) (Vector2.Dot(a, b) / (a.norm() * b.norm())));
    }

    //Vec2Angle calculates the signed angle between vectors.
    //See: https://tinyurl.com/y78gdv84
    public static float Vec2Angle(Vector2 a, Vector2 b) {
        return (float) Math.atan2(a.x() * b.y() - a.x() * b.x(), a.x() * b.x() + a.y() * b.y());
    }

    //angle should represent a global angle, not an imu angle
    public static Vector2 Angle2Vec(float angle) {
        return new Vector2((float) Math.cos((double) angle), (float) Math.sin((double) angle));
    }

    public static Vector2 Array2Vec(float arr[]) {
        return new Vector2(arr[0], arr[1]);
    }

    //Returns the zero vector of the vector space R^2
    public static Vector2 Zero() {
        return new Vector2(0, 0);
    }

    //Returns the multiplicative identity vector of the vector space R^2
    public static Vector2 Eye() {
        return new Vector2(1, 1);
    }

    @Override
    public String toString() {
        return "VECTOR2: X = " + x + ", Y = " + y;
    }

    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Vector2.class && norm() == ((Vector2) obj).norm();
    }
}
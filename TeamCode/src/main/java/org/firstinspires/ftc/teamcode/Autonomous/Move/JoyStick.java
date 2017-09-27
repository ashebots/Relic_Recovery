package org.firstinspires.ftc.teamcode.Autonomous.Move;

/**
 * Created by secre on 9/13/2017.
 */

public class JoyStick {

    public int degrees(double x, double y){
        return (int) Math.toDegrees(Math.atan(x / y));
    }

    public double radians(double x, double y){
        return Math.atan(x / y);
    }

    public double magnitude(double x, double y){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}

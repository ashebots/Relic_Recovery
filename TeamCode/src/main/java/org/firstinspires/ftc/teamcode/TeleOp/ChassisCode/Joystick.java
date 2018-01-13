package org.firstinspires.ftc.teamcode.TeleOp.ChassisCode;

/**
 * Created by jezebelquit on 8/1/17.
 */

public class Joystick {

    //caclulateNormal returns the double array that should be assigned to a two (or 4) wheel drive depending on the joystick's position.
    //[0] is the speed of the left motor, and [1] is the speed of the right motor.
    public static double[] calculateNormal(double xPos, double yPos) {

        //angleCount is the amount of different angles you can have on the joystick.
        double angleCount = 360;
        angleCount = 360d/angleCount;

        //The angle formed by the y axis and the line formed by (0,0) and where the joystick is located.
        double joystickAngle;

        //The distance that the joystick is from the center
        double joystickDistance;

        //[0] is the speed of the left motor, and [1] is the speed of the right motor.
        double[] motorSpeeds = new double[2];

        //The distance that the joystick is from the center can be calculated by using the pythagorean theorem.
        joystickDistance = Math.sqrt((xPos * xPos) + (yPos * yPos));

        //When calculating the joystick's distance from the center, sometimes the distance exceeds this by a tiny bit. This if statement sets the distance to 1 if this happens.
        if (joystickDistance > 1) joystickDistance = 1;

        //Trigonometry is used to calculate joystickAngle.
        joystickAngle = Math.round(Math.toDegrees(Math.atan2(xPos, yPos))/angleCount)*angleCount;


        //While the angle of the joystick dictates dictates the maximum speed the motors should be at, the distance from the center dictates what fraction of this maximum speed it should be.
        //Depending on which quadrant of the joystick we are in, the method of calculating motor speeds will vary.

        //The following if statements see which quadrant you are in. The quadrant order is 1, 4, 2, 3
        if (joystickAngle >= 0 && joystickAngle < 90) {

            //Calculates the motors speeds.
            motorSpeeds[1] = ((45 - joystickAngle) / 45) * joystickDistance;
            motorSpeeds[0] = joystickDistance;

        } else if (joystickAngle >= 90) {

            motorSpeeds[0] = ((135 - joystickAngle) / 45) * joystickDistance;
            motorSpeeds[1] = -joystickDistance;

        } else if (joystickAngle < 0 && joystickAngle > -90) {

            //The only difference between the left and right sides of the joystick is that the speeds assinged to the motors are swapped.
            motorSpeeds[0] = ((45 + joystickAngle) / 45) * joystickDistance;
            motorSpeeds[1] = joystickDistance;

        } else if (joystickAngle <= -90) {

            motorSpeeds[1] = ((135 + joystickAngle) / 45) * joystickDistance;
            motorSpeeds[0] = -joystickDistance;

        }

        return motorSpeeds;
    }

    //calculateCar returns the rear motor speed and turning servo position of a car drive depending on the positions of th joystick(s) (or triggers).
    public static double[] calculateCar(double xPos, double yPos) {

        //[0] is the speed of the rear motor, and [1] is position of the turning servo.
        double[] motorSpeeds = new double[2];

        //The rear motor speed is assigned to be the inputted y position.
        motorSpeeds[0] = yPos;

        //The inputted x position (which ranges from -1 to 1) is converted to a servo position (which ranges from 0 to 1).
        motorSpeeds[1] = (xPos + 1) / 2;

        return motorSpeeds;
    }
}

package org.firstinspires.ftc.teamcode.Autonomous.Move;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.Queue;

public class Transform {
    //Hardware mapped components
    private     DcMotor     leftMotor;
    private     DcMotor     rightMotor;
    private     Telemetry   telemetry;
    private     GoodIMU     imu;
    private     BNO055IMU   rawImu;

    //Encoder variables dependent on robot design
    private     int         positionL;
    private     int         positionR;
    private     double      encoderCons;

    //Holds position of bot relative to starting position and world space
    private     Vector2     localPosition;
    private     Vector2     position;
    private     Vector2     forwardVector;
    private     double      theta0;


    public Transform(Telemetry telemetry, HardwareMap hardwareMap, double wheelDiameter, double theta0) {
        leftMotor       = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        rightMotor      = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        rawImu          = hardwareMap.get(BNO055IMU.class, ConfigStrings.IMU);
        encoderCons     = ((MathConstants.neverRest40 * 12) / (wheelDiameter * Math.PI));
        this.theta0     = theta0;
        this.telemetry  = telemetry;
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    //Encoder based forward movement. Superseded by imu based movement.
    @Deprecated
    public void forward(double dist, double pow) {
        positionL = leftMotor.getCurrentPosition();
        positionR = rightMotor.getCurrentPosition();
        dist *= encoderCons;
        while (leftMotor.getCurrentPosition() - positionL < dist || rightMotor.getCurrentPosition() - positionR < dist){
            if (leftMotor.getCurrentPosition() - positionL < dist) {
               leftMotor.setPower(pow * this.easing(encoderCons, leftMotor.getCurrentPosition() - positionL, dist));
            }
            if (rightMotor.getCurrentPosition() - positionR < dist) {
                rightMotor.setPower(pow * this.easing(encoderCons, rightMotor.getCurrentPosition() - positionR, dist));
            }
            telemetry.update();
        }
        rightMotor.setPower(0);
        rightMotor.setPower(0);
    }

    //Encoder based backward movement. Superseded by imu based movement.
    @Deprecated
    public void backward(double dist, double pow) {
        positionL = leftMotor.getCurrentPosition();
        positionR = rightMotor.getCurrentPosition();
        dist *= encoderCons;
        while (leftMotor.getCurrentPosition() - positionL > -dist || rightMotor.getCurrentPosition() - positionR > -dist) {
            if (leftMotor.getCurrentPosition() - positionL > -dist) {
                leftMotor.setPower(-pow * this.easing(encoderCons, leftMotor.getCurrentPosition() - positionL, dist));
            }
            if (rightMotor.getCurrentPosition() - positionR > -dist) {
                leftMotor.setPower(-pow * this.easing(encoderCons, rightMotor.getCurrentPosition() - positionR, dist));
            }
            telemetry.update();
        }
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    public void turn(int angle, double pow) {
        float startAngle = imu.yaw();
        if (angle > 0) {
            while (startAngle - imu.yaw() < angle) {
                leftMotor.setPower(pow);
                rightMotor.setPower(-pow);
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }else{
            while (startAngle - imu.yaw() < angle) {
                leftMotor.setPower(-pow);
                rightMotor.setPower(pow);
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }

    public void lookAt(int angle, double pow){
        int angleDifrence = imu.yaw() - angle;
        boolean turnRight = false;
        if (angleDifrence + imu.yaw() == angle){
            turnRight = true;
        }

        if (turnRight) {
            while (imu.yaw() < angle - 5 || imu.yaw() > angle + 5) {
                leftMotor.setPower(pow);
                rightMotor.setPower(-pow);
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }else{
            while (imu.yaw() < angle - 5 || imu.yaw() > angle + 5) {
                leftMotor.setPower(-pow);
                rightMotor.setPower(pow);
            }
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
    }

    public double easing(double dist, double curDist, double dt){
        if (curDist > dist){
            return curDist / dist;
        }
        if (curDist > dt - dist){
            return 1- curDist / dist;
        }
        return 1;
    }

    public void updatePosition(Vector2 target, double power) {
      //  lookAt();
    }

    public void modularDrive(Queue<Vector2> points) {
        for (int i = 0; i < points.size(); i++) {
        }

       // position = positions[0];// set the current position variable to the first Vector in the positions array
   /*     Vector2 targetPosition;// make Vector2 object for storeing the nex position to muve to
        for(int i = 0; i > positions.length; i++){
            targetPosition = positions[i+1]; //set the target position to the next Vector in the positions array

            this.turnTo((int) Math.atan((targetPosition.x() - position.x()) / (targetPosition.y() - position.y())), pow);
            this.forward(Math.sqrt(Math.pow(targetPosition.x() - position.x(), 2) + Math.pow(targetPosition.y() - position.y(), 2)), pow);
        }
        */
    }

}
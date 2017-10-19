package org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


/**
 * Created by jezebelquit on 8/12/17.
 */

public class ImuChassis {

    //encodersPerFoot is required for calculating the encoder position to drive
    public int encodersPerFoot;

    //The IMU chassis consists of two motors and an IMU.
    DcMotor leftMotor;
    DcMotor rightMotor;

    BNO055IMU imu;

    Double maxEncodersPerFoot;

    //The IMU chassis constructor
    public ImuChassis(DcMotor leftMotor, DcMotor rightMotor, BNO055IMU imu, double maxEncodersPerFoot){

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.leftMotor = leftMotor;

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.rightMotor = rightMotor;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.mode = BNO055IMU.SensorMode.NDOF;
        imu.initialize(parameters);
        this.imu = imu;

        this.maxEncodersPerFoot = maxEncodersPerFoot;

    }


    //Simple programs to turn or drive forward at the motor speed you input, as well as stop
    public void driveAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }
    public void turnAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);
    }
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    //SmartImu converts angles to Imu angles, which also serves to loop the Imu angle when adding two angles
    public static float smartImu (float input){
        while (input <= -180) {
            input += 360;
        }
        while (input > 180){
            input -= 360;
        }
        return input;
    }

    public void turnToAngle (float angleTo, double speed) {

        speed =  speed * maxEncodersPerFoot / 4000;

        float currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        float angleDifference = Math.abs(currentAngle - angleTo);
        if (angleDifference > 360-angleDifference) angleDifference = 360-angleDifference;

        boolean turnToRight = false;
        if (smartImu(currentAngle + angleDifference) == angleTo) turnToRight = true;

        if (turnToRight) {

            if (currentAngle > angleTo) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                while (currentAngle > angleTo) currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                stop();

            }else{

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                while (currentAngle < angleTo) currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                stop();
            }

        } else{

            if (currentAngle < angleTo) {

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                while (currentAngle < angleTo) currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                stop();

            }else{

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                while (currentAngle > angleTo) currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

                stop();
            }
        }
    }

    public void turnXDegrees (float angleTo, double speed){
        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + angleTo);
        turnToAngle(angleTo, speed);
        
    }

    public void driveSetup(float encodersPerRotation, float gearRatio, float wheelDiameter){
        encodersPerFoot = (int)((12 * encodersPerRotation) / (gearRatio * Math.PI * wheelDiameter));
    }

    public void driveXFeet(int feet, double speed) {

        speed = speed * maxEncodersPerFoot / 4000;
        int leftGoal = (feet*encodersPerFoot) + leftMotor.getCurrentPosition();

        driveAtSpeed(speed);

        while (leftMotor.getCurrentPosition() < leftGoal){}

        stop();
    }

    public float[] driveToCoord (float[] startPosition, float[] coords, double driveSpeed, double turnSpeed, String teamColor){

        driveSpeed = driveSpeed * (maxEncodersPerFoot / 4000);
        turnSpeed = turnSpeed * (maxEncodersPerFoot / 4000);

        float angleTo;
        int distance = (int)Math.sqrt((startPosition[1]-coords[1])*(startPosition[1]-coords[1])+(startPosition[0]-coords[0])*(startPosition[0]-coords[0]));

        if (startPosition[0] != coords[0]) {
            angleTo = (float)-Math.toDegrees(Math.atan((startPosition[1] - coords[1]) / (startPosition[0] - coords[0])));
        }else if (startPosition[1] < coords[1]){
            angleTo = -90;
        }else{
            angleTo = 90;
        }

        if (startPosition[0] > coords[0]){
            angleTo = Math.abs(angleTo)-180;

            if (startPosition[1] >= coords[1]) angleTo = -angleTo;

        }

        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - angleTo );

        turnXDegrees(angleTo, turnSpeed);
        driveXFeet(distance, driveSpeed);

        return coords;

    }

    public void driveToCoords(float[][] coordList, double driveSpeed, double turnSpeed, String teamColor){

        for (int i = 1; i < coordList.length; i++){
            coordList[0] = driveToCoord(coordList[0], coordList[i], driveSpeed, turnSpeed, teamColor);
        }

    }
}

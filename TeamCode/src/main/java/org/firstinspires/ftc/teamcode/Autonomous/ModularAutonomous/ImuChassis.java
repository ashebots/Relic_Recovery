package org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.DisplayPrograms.ArrayDis;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;


/**
 * Created by jezebelquit on 8/12/17.
 */

public class ImuChassis {

    //encodersPerFoot is required for calculating the encoder position to drive
    public static int encodersPerFoot;

    //The IMU chassis consists of two motors and an IMU.
    static DcMotor leftMotor;
    static DcMotor rightMotor;

    static BNO055IMU imu;

    static Double maxSpeed;

    AnnualModule annualModule;

    //The IMU chassis constructor
    public ImuChassis(DcMotor left, DcMotor right, BNO055IMU IMU, Double maxSpeed, AnnualModule annualModule){

        leftMotor = left;
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightMotor = right;
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = IMU;
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.mode = BNO055IMU.SensorMode.NDOF;
        imu.initialize(parameters);

        this.maxSpeed = maxSpeed;

        this.annualModule = annualModule;
    }


    //Simple programs to turn or drive forward at the motor speed you input, as well as stop
    public static void driveAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }
    public static void turnAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);
    }
    public static void stop() {
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

    public static void turnToAngle (float angleTo, double speed) {

        speed = speed * maxSpeed / 4000;

        float currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        float angleDifference = Math.abs(currentAngle - angleTo);
        if (angleDifference > 360-angleDifference) angleDifference = 360-angleDifference;

        boolean turnToRight = false;
        if (smartImu(currentAngle + angleDifference) == angleTo) turnToRight = true;

        if (turnToRight) {

            while (currentAngle > angleTo) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

            while (currentAngle < angleTo) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

        } else{

            while (currentAngle < angleTo) {

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

            while (currentAngle > angleTo){

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

        }
    }

    public static void turnXDegrees (float angleTo, double speed){
        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + angleTo);
        turnToAngle(angleTo, speed);
        
    }

    public static void driveSetup(float encodersPerRotation, float gearRatio, float wheelDiameter){
        encodersPerFoot = (int)((12 * encodersPerRotation) / (gearRatio * Math.PI * wheelDiameter));
    }

    public static void driveXFeet(double feet, double speed) {

        speed = speed * maxSpeed / 4000;
        int leftGoal = (int)((feet*encodersPerFoot) + leftMotor.getCurrentPosition());

        if (leftMotor.getCurrentPosition() < leftGoal) {
            while (leftMotor.getCurrentPosition() < leftGoal) {
                driveAtSpeed(speed);
            }
        }else{
            while (leftMotor.getCurrentPosition() > leftGoal) {
                driveAtSpeed(-speed);
            }
        }

        stop();
    }

    public void driveToCoord (float[] startPosition, float[] coords, double driveSpeed, double turnSpeed, Boolean isRed){

        driveSpeed = driveSpeed * (maxSpeed / 4000);
        turnSpeed = turnSpeed * (maxSpeed / 4000);

        float initialAngle;

        float distance = (int)Math.sqrt((startPosition[1]-coords[1])*(startPosition[1]-coords[1])+(startPosition[0]-coords[0])*(startPosition[0]-coords[0]));

        if (startPosition[0] != coords[0]) {
            initialAngle = (float)-Math.toDegrees(Math.atan((startPosition[1] - coords[1]) / (startPosition[0] - coords[0])));
        }else if (startPosition[1] < coords[1]){
            initialAngle = -90;
        }else{
            initialAngle = 90;
        }

        if (startPosition[0] > coords[0]){
            initialAngle = Math.abs(initialAngle)-180;

            if (startPosition[1] >= coords[1]) initialAngle = -initialAngle;

        }

        initialAngle = smartImu(initialAngle+90);

        if (isRed) initialAngle = -initialAngle;

        turnToAngle(initialAngle, turnSpeed);
        driveXFeet(distance, driveSpeed);

    }

    public void driveToCoords(float[][] coordList, double driveSpeed, double turnSpeed, Boolean isRed){

        for(int i = 1; i < coordList.length; i++){
            driveToCoord(coordList[i-1], coordList[i], driveSpeed, turnSpeed, isRed);

            //Scan the pictograph and set next location to the appropriate crypto box position
            annualModule.coordCheck(coordList, i);
        }

    }
}
package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;

import android.graphics.CornerPathEffect;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
<<<<<<< HEAD
=======
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Action;
import org.firstinspires.ftc.teamcode.Autonomous.DisplayPrograms.ArrayDis;
import org.firstinspires.ftc.teamcode.Autonomous.Move.GoodIMU;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;
import org.firstinspires.ftc.teamcode.Coordinate;
import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;

import java.util.Queue;


/**
 * Created by jezebelquit on 8/12/17.*-
 */
>>>>>>> e7cd22a3cbe007b9c88a52e5c98839e603f538b0

public class ImuChassis {
    private LinearOpMode opMode; //this is not a good idea

    //encodersPerFoot is required for calculating the encoder position to drive
    private int encodersPerFoot;
    private DriveSpec driveSpec;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private BNO055IMU imu;
    private GoodIMU goodIMU;

    private Vector2 initialPosition;
    private Vector2 currentPosition;
    private Vector2 forward;

    public enum Direction {
        Left, Right
    }

    public class DriveSpec {
        public float encodersPerRotation;
        public float gearRatio;
        public float wheelDiameter;
    }

    public ImuChassis(DcMotor leftMotor, DcMotor rightMotor, BNO055IMU imu, LinearOpMode opMode) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.imu = imu;
        this.opMode = opMode;
        this.goodIMU = new GoodIMU(imu, GoodIMU.Unit.DEGREE);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        initVuforia();
    }

    public ImuChassis(DcMotor leftMotor, DcMotor rightMotor, BNO055IMU imu, LinearOpMode opMode, DriveSpec driveSpec) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.imu = imu;
        this.opMode = opMode;
        this.driveSpec = driveSpec;

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        initVuforia();
    }

    private void initVuforia() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.mode = BNO055IMU.SensorMode.NDOF;
        imu.initialize(parameters);
    }

    public void driveAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }
    private void turnAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);
    }

    private  void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    //SmartImu converts angles to Imu angles, which also serves to loop the Imu angle when adding two angles
    private float smartImu (float input){
        while (input <= -180 && opMode.opModeIsActive()) {
            input += 360;
        }
        while (input > 180 && opMode.opModeIsActive()){
            input -= 360;
        }
        return input;
    }

    public void lookAt(Vector2 target, double speed) {
        float angle = Vector2.Vec2Angle(target, forward);

    }



    public void turnToAngle (float angleTo, double speed) {

        //speed = speed * maxSpeed / 4000;

        float currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        float angleDifference = Math.abs(currentAngle - angleTo);
        if (angleDifference > 360-angleDifference) angleDifference = 360-angleDifference;

        boolean turnToRight = false;
        if (smartImu(currentAngle + angleDifference) == angleTo) turnToRight = true;

        if (turnToRight) {

            while (currentAngle <= angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle < angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

        } else{

            while (currentAngle < angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle > angleTo && opMode.opModeIsActive()){

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

        }
    }

    public void turnXDegrees (float angleTo, double speed){
        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + angleTo);
        turnToAngle(angleTo, speed);
    }

    //driveSetup is needed to calculate the encoders per foot of the robot, using the encoders per rotation, the gear ratio, and the wheel diameter.
    public void driveSetup(float encodersPerRotation, float gearRatio, float wheelDiameter){
        encodersPerFoot = (int)((12 * encodersPerRotation) / (gearRatio * Math.PI * wheelDiameter));
    }

<<<<<<< HEAD
    public void driveFromStart(double feet, double speed){
=======
    public void driveSetup(DriveSpec spec) {
        encodersPerFoot = (int)((12 * spec.encodersPerRotation) / (spec.gearRatio * Math.PI * spec.wheelDiameter));
    }

    public void driveXFeet(double feet, double speed) {
>>>>>>> 1d6d82860f25100cc8a63675d830bbd4427d431b

        //speed = speed * maxSpeed / 4000;
        int leftGoal = (int)(-feet*encodersPerFoot);

        if (leftMotor.getCurrentPosition() > leftGoal) {

            while (leftMotor.getCurrentPosition() > leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(speed);
            }
            while (leftMotor.getCurrentPosition() < leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(-speed/2);
            }

        }else{

            while (leftMotor.getCurrentPosition() < leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(-speed);
            }
            while (leftMotor.getCurrentPosition() > leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(speed/2);
            }

        }

        stop();
    }

    public void driveXFeet(double feet, double speed) {

        feet = feet - leftMotor.getCurrentPosition()/encodersPerFoot;
        driveFromStart(feet, speed);

    }

    public void driveToCoord (float[] startPosition, float[] coords, double driveSpeed, double turnSpeed, Boolean isRed){

        //driveSpeed = driveSpeed * (maxSpeed / 4000);
        //turnSpeed = turnSpeed * (maxSpeed / 4000);

        float initialAngle;

        //The distance calculation
        float distance = (int)Math.sqrt((startPosition[1]-coords[1])*(startPosition[1]-coords[1])+(startPosition[0]-coords[0])*(startPosition[0]-coords[0]));

        //Calculating the angle is a bit tricky, and takes quite a bit of if statements
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

        //If the robots are on the red alliance, the angle needs to be reversed
        if (isRed) initialAngle = -initialAngle;

        //Turns in the direction of the coordinate, and then drives until it reaches it
        turnToAngle(initialAngle, turnSpeed);
        driveXFeet(distance, driveSpeed);

    }

    public void driveToCoords(float[][] coordList, double driveSpeed, double turnSpeed, Boolean isRed){

        for(int i = 1; i < coordList.length; i++){
            driveToCoord(coordList[i-1], coordList[i], driveSpeed, turnSpeed, isRed);

            //Scan the pictograph and set next location to the appropriate crypto box position
            //annualModule.coordCheck(coordList, i);
        }

    }
}
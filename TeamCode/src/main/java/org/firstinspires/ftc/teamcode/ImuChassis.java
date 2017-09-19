package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.RelicRecovery.ConfigStrings;
import org.firstinspires.ftc.teamcode.RelicRecovery.IMU;

/**
 * Created by jezebelquit on 8/12/17.
 */

public class ImuChassis {
    private static int[] ORIGIN = {0,0};

    //encodersPerFoot is needed for driving forward a certain amount of feet
    private int encodersPerFoot;

    //The IMU chassis consists of two motors and an IMU
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private BNO055IMU imu;

    //The IMU chassis constructor
    public ImuChassis(HardwareMap hardwareMap){
        leftMotor   = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        rightMotor  = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = ConfigStrings.IMU_CALIBRATION_FILE;
        parameters.mode = BNO055IMU.SensorMode.NDOF;
        imu.initialize(parameters);
    }

    //Simple programs to turn or drive forward at the motor speed you input, as well as stop
    public void driveAtPower(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }
    public void turnAtPower(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    }
    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Sorter_Robot/Autonomous/ModularAutonomous/ImuChassis.java
    //SmartImu converts angles to Imu angles, which also serves to loop the Imu angle when adding two angles
    public static float smartImu(float input){
        while (input <= -180) {
            input =+ 360;
        }
        while (input > 180){
            input -= 360;
        }
        return input;
    }

    public void turnToAngle (float angleTo, double power){

=======
    public void turnToAngle(float angleTo, double power) {
>>>>>>> origin/master:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/RelicRecovery/ImuChassis.java
        float currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        float angleDifference = Math.abs(currentAngle - angleTo);
        if (angleDifference > 360-angleDifference) angleDifference = 360-angleDifference;

        boolean turnToRight = false;
        if (smartImu(currentAngle + angleDifference) == angleTo) turnToRight = true;

        if (turnToRight) {

            while (currentAngle > angleTo) {
                turnAtPower(power);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle < angleTo){
                turnAtPower(power);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

        } else {

            while (currentAngle < angleTo) {
                turnAtPower(-power);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle > angleTo){
                turnAtPower(-power);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
        }

        stop();
    }

    public void turnXDegrees (float angleTo, double power){
        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + angleTo);
        turnToAngle(angleTo, power);
        
    }

    public void encodersPerFoot(float encodersPerRotation, float gearRatio, float wheelDiameter){
        encodersPerFoot = (int)(12*encodersPerRotation / (gearRatio*Math.PI*wheelDiameter));
    }

    public void driveXFeet(int feet, double power){

        feet = feet * encodersPerFoot;
        int leftGoal = feet + leftMotor.getCurrentPosition();

        float startAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        while (leftMotor.getCurrentPosition() < leftGoal){
            driveAtPower(power);
        }
        while (leftMotor.getCurrentPosition() > leftGoal){
            driveAtPower(-power/4);
        }
    }

    public void driveToCoord(int[] coords){
        
    }
    public void driveToCoords(int[][] coords){
        for (int i = 1; i < coords.length; i++){
            driveToCoord(coords[i]);
        }
    }
}

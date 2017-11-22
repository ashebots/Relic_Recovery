package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.motors.NeveRest60Gearmotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ModularConstants;

/**
 * Created by jezebelquit on 9/21/17.
 */

@Autonomous
public class ModularTest extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo lTray;
    Servo rTray;

    BNO055IMU imu;

    ImuChassis imuChassis;

    float[] b = {1, 1};
<<<<<<< HEAD
    float[][] test = {ModularConstants.BALANCE_STONE_A, ModularConstants.Mid_COLUMN_A};
=======
    float[][] test = {ModularConstants.BALANCE_STONE_A,ModularConstants.Mid_COLUMN_A};
>>>>>>> a1264641a1325c059ab7e719fc2c8529394babdb

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right wheel");

        leftMotor = hardwareMap.dcMotor.get("Left wheel");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        lTray = hardwareMap.servo.get("Left rotator");
        rTray = hardwareMap.servo.get("Right rotator");
        rTray.setDirection(Servo.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2959);
        imuChassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        lTray.setPosition(0.8);
        rTray.setPosition(0.8);

        waitForStart();

        imuChassis.driveToCoords(test, 0.8, 0.3, false);

        imuChassis.turnToAngle(90, .4);
        imuChassis.driveXFeet(-2, .8);
        lTray.setPosition(0.4);
        rTray.setPosition(0.4 );



    }
}

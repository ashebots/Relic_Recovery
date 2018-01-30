package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ImuChassis;

/**
 * Created by Lenovo on 11/9/2017.
 */
@Autonomous
public class KeeganAndIanColorSensor extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    ColorSensor colorSensor;
    ImuChassis imuChassis;
    BNO055IMU imu;

    public void runOpMode () {

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right");

        colorSensor = hardwareMap.colorSensor.get("Color");
        imu = hardwareMap.get(BNO055IMU.class, "imu");



        waitForStart();

        telemetry.addData("Red", colorSensor.red());
        telemetry.addData("Blue", colorSensor.blue());
        telemetry.addData("Green", colorSensor.green());

    }

}
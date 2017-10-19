package org.firstinspires.ftc.teamcode.Autonomous.DisplayPrograms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;

/**
 * Created by jezebelquit on 10/12/17.
 */

@Autonomous
public class FeetPerSecond extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right");

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        sleep(10000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        sleep(100);

        telemetry.addData("Feet per second for left", (leftMotor.getCurrentPosition()*(4*Math.PI)/(12*1680))/10);
        telemetry.addData("Feet per second for right", (rightMotor.getCurrentPosition()*(4*Math.PI)/(12*1680))/10);

        telemetry.update();

        sleep(15000);
    }
}

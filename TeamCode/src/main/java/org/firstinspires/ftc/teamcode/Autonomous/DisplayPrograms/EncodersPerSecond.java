package org.firstinspires.ftc.teamcode.Autonomous.DisplayPrograms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by jezebelquit on 9/21/17.
 */
@Autonomous
public class EncodersPerSecond extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor = hardwareMap.dcMotor.get("Left");

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        rightMotor.setPower(1);
        leftMotor.setPower(1);

        sleep(10000);

        rightMotor.setPower(0);
        leftMotor.setPower(0);

        sleep(100);

        telemetry.addData("Encoders per second on left", leftMotor.getCurrentPosition()/10);
        telemetry.addData("Encoders per second on right", rightMotor.getCurrentPosition()/10);
        telemetry.update();

        sleep(15000);

    }
}



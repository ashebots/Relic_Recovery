package org.firstinspires.ftc.teamcode.TeleOp.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by famil on 1/6/2018.
 */
@TeleOp
public class EncoderDisplay extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void init(){

        leftMotor = hardwareMap.dcMotor.get("Left wheel");
        rightMotor = hardwareMap.dcMotor.get("Right wheel");

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void loop(){

        leftMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);
        rightMotor.setPower(gamepad1.right_trigger-gamepad1.left_trigger);

        telemetry.addData("Feet traveled on left ", -leftMotor.getCurrentPosition()/713.014145);
        telemetry.addData("Feet traveled on right", -rightMotor.getCurrentPosition()/713.014145);

    }
}

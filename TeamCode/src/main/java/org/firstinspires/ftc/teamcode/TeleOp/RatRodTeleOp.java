package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by famil on 11/12/2017.
 */
@TeleOp
public class RatRodTeleOp extends OpMode {

    DcMotor driveMotor;
    Servo turnServo;

    public void init(){

        driveMotor = hardwareMap.dcMotor.get("drive");
        driveMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        turnServo = hardwareMap.servo.get("turn");

    }

    public void loop(){

        driveMotor.setPower(-gamepad1.left_stick_y);
        turnServo.setPosition((0.1875*gamepad1.right_stick_x+1)/2);

    }
}

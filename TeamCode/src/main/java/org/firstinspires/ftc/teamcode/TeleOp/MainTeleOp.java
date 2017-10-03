package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by jezebelquit on 9/21/17.
 */
@TeleOp
public class MainTeleOp extends OpMode{

    DcMotor driveMot;
    Servo turn;

    public void init(){
        driveMot = hardwareMap.dcMotor.get("Left");
        turn = hardwareMap.servo.get("turn");

    }

    public void loop(){
        driveMot.setPower(gamepad1.left_stick_y);
        turn.setPosition(((gamepad1.left_stick_x)/2 + 1)/2);
    }
}

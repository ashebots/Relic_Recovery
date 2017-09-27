package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by jezebelquit on 9/21/17.
 */
@TeleOp
public class MainTeleOp extends OpMode{
    Chassis drive;
    DcMotor driveMot;
    Servo turn;

    public void init(){
        driveMot = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        turn = hardwareMap.servo.get("turn");
        drive = new Chassis(driveMot, turn);
    }

    public void loop(){
        drive.CarDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);
    }
}

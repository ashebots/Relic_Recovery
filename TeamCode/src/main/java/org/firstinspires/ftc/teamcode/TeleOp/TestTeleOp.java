package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/*
 * Created by jezebelquit on 9/30/17.
 */
@TeleOp
public class TestTeleOp extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor rearLeftMotor;
    DcMotor rearRightMotor;

    Chassis chassis;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("LeftForward");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor = hardwareMap.dcMotor.get("LeftBack");
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("RightForward");
        rearRightMotor = hardwareMap.dcMotor.get("RightBack");

        chassis = new Chassis(leftMotor, rightMotor, rearLeftMotor, rearRightMotor);

    }

    public void loop() {
        chassis.HoloMecaDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);

    }
}

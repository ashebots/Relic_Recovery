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

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor = hardwareMap.dcMotor.get("Left Rear");
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right");
        rearRightMotor = hardwareMap.dcMotor.get("Right Rear");

        chassis = new Chassis(leftMotor, rightMotor, rearLeftMotor, rearRightMotor);

    }

    public void loop() {

        chassis.HoloMecaDrive(gamepad1.right_stick_x, -gamepad1.right_stick_y);
        chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

    }
}

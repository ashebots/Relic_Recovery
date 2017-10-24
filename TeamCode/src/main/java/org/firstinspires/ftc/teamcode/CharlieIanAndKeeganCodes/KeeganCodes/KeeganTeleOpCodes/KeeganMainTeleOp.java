package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganTeleOpCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/7/2017.
 */
@TeleOp
public class KeeganMainTeleOp extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor boxMotors;
    DcMotor BoxMotorRight;
    DcMotor BoxMotorLeft;
    Servo RotatorLeft;
    Servo RotatorRight;
    Chassis Chassis;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right");
        BoxMotorRight = hardwareMap.dcMotor.get("Right intake");
        BoxMotorLeft = hardwareMap.dcMotor.get("Left intake");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RotatorRight = hardwareMap.servo.get("Right rotator");
        RotatorLeft = hardwareMap.servo.get("Left rotator");
        RotatorLeft.setDirection(Servo.Direction.REVERSE);

        Chassis = new Chassis(leftMotor, rightMotor);

    }

    public void loop() {

        Chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.a) {

            BoxMotorLeft.setPower(1);
            BoxMotorRight.setPower(1);

        }
        else if (gamepad1.y) {

            BoxMotorLeft.setPower(-1);
            BoxMotorRight.setPower(-1);

        }
        else if (gamepad1.x) {

            BoxMotorLeft.setPower(-1);
            BoxMotorRight.setPower(1);

        }
        else if (gamepad1.b) {

            BoxMotorLeft.setPower(1);
            BoxMotorRight.setPower(-1);

        }
        else {

            BoxMotorRight.setPower(0);
            BoxMotorLeft.setPower(0);

        }

        if (gamepad1.dpad_up) {

            RotatorLeft.setPosition(0.75);
            RotatorRight.setPosition(0.75);

        }
        else if (gamepad1.dpad_down) {

            RotatorLeft.setPosition(0);
            RotatorRight.setPosition(0);

        }
        else {

            RotatorLeft.setPosition(0.5);
            RotatorRight.setPosition(0.5);

        }



    }
}
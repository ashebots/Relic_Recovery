package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganTeleOpCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
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
    DcMotor BoxMotorRight;
    DcMotor BoxMotorLeft;
    CRServo RotatorLeft;
    CRServo RotatorRight;
    CRServo RightFlyWheel;
    CRServo LeftFlyWheel;
    Servo RightFlyRotator;
    Servo LeftFlyRotator;
    Chassis Chassis;
    DcMotor Lift;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("Left wheel");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right wheel");
        BoxMotorRight = hardwareMap.dcMotor.get("Right sweeper");
        BoxMotorLeft = hardwareMap.dcMotor.get("Left sweeper");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RotatorLeft = hardwareMap.crservo.get("Left rotator");
        RotatorRight = hardwareMap.crservo.get("Right rotator");
        LeftFlyWheel = hardwareMap.crservo.get("Left fly wheel");
        RightFlyWheel = hardwareMap.crservo.get("Right fly wheel");
        LeftFlyWheel.setDirection(CRServo.Direction.REVERSE);
        RotatorLeft.setDirection(CRServo.Direction.REVERSE);
        RightFlyRotator = hardwareMap.servo.get("Right fly rotator");
        LeftFlyRotator = hardwareMap.servo.get("Left fly rotator");
        Lift = hardwareMap.dcMotor.get("Lift");



        Chassis = new Chassis(leftMotor, rightMotor);

    }

    public void loop() {

        Chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.a) {

            BoxMotorLeft.setPower(1);
            BoxMotorRight.setPower(1);
            RightFlyWheel.setPower(1);
            LeftFlyWheel.setPower(1);

        } else if (gamepad1.y) {

            BoxMotorLeft.setPower(-1);
            BoxMotorRight.setPower(-1);
            RightFlyWheel.setPower(0);
            LeftFlyWheel.setPower(0);

        } else if (gamepad1.x) {

            BoxMotorLeft.setPower(-1);
            BoxMotorRight.setPower(1);
            RightFlyWheel.setPower(1);
            LeftFlyWheel.setPower(1);

        } else if (gamepad1.b) {

            BoxMotorLeft.setPower(1);
            BoxMotorRight.setPower(-1);
            RightFlyWheel.setPower(1);
            LeftFlyWheel.setPower(1);

        } else {

            BoxMotorRight.setPower(0);
            BoxMotorLeft.setPower(0);
            RightFlyWheel.setPower(0);
            LeftFlyWheel.setPower(0);

        }

        if (gamepad1.left_bumper) {

            RotatorLeft.setPower(1);
            RotatorRight.setPower(1);

        } else if (gamepad1.right_bumper) {

            RotatorLeft.setPower(0);
            RotatorRight.setPower(0);

        } else {

            RotatorLeft.setPower(0.5);
            RotatorRight.setPower(0.5);

        }
        if (gamepad1.right_trigger > 0) {

            RightFlyRotator.setPosition(0.75);
            LeftFlyRotator.setPosition(0.75);

        } else if (gamepad1.left_trigger > 0) {

            RightFlyRotator.setPosition(0);
            LeftFlyRotator.setPosition(0);

        } else {

            RightFlyRotator.setPosition(0.5);
            LeftFlyRotator.setPosition(0.5);

        }
        if (gamepad1.dpad_up) {

            Lift.setPower(1);

        } else if (gamepad1.dpad_down) {

            Lift.setPower(-1);

        } else {

            Lift.setPower(0);

        }

    }

}
package org.firstinspires.ftc.teamcode.MaincompetitiveTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/19/2017.
 */
@TeleOp
public class OfficialTeamRobotTeleop extends OpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor leftIntake;
    DcMotor rightIntake;

    Servo rotateLeft;
    Servo rotateRight;

    Chassis chassis;

    public void init() {
        rightMotor = hardwareMap.dcMotor.get("Right");
        leftMotor = hardwareMap.dcMotor.get("Left");
        leftIntake = hardwareMap.dcMotor.get("Left intake");
        rightIntake = hardwareMap.dcMotor.get("Right intake");

        rotateRight = hardwareMap.servo.get("Right rotator");
        rotateLeft = hardwareMap.servo.get("Left rotator");
        rotateLeft.setDirection(Servo.Direction.REVERSE);

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftMotor, rightMotor);
    }

    public void loop () {

        chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.right_bumper){
            leftIntake.setPower(1);
            rightIntake.setPower(1);

        } else if (gamepad1.right_trigger > 0){
            leftIntake.setPower(-1);
            rightIntake.setPower(-1);

        } else {
            leftIntake.setPower(0);
            rightIntake.setPower(0);



        }

        rotateLeft.setPosition((1+gamepad1.right_stick_y/2)/2);
        rotateRight.setPosition((1+gamepad1.right_stick_y/2)/2);
    }
}
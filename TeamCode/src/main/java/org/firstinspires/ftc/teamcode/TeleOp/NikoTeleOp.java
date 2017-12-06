package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by famil on 11/11/2017.
 */

@TeleOp
public class NikoTeleOp extends OpMode{

    DcMotor leftWheel;
    DcMotor rightWheel;

    Chassis chassis;

    int slowness;

    DcMotor leftSweeper;
    DcMotor rightSweeper;
    DcMotor arm;
    DcMotor tilt;

    CRServo leftRotate;
    CRServo rightRotate;
    Servo rist;
    Servo grab;

    DcMotor lift;

    Servo adjuster;

    String speedStatus;
    String adjusterStatus;

    public void init(){

        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        slowness = 1;

        leftSweeper = hardwareMap.dcMotor.get("Left sweeper");
        rightSweeper = hardwareMap.dcMotor.get("Right sweeper");
        leftSweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        arm = hardwareMap.dcMotor.get("arm");
        tilt = hardwareMap.dcMotor.get("tilt");

        rist = hardwareMap.servo.get("rist");
        grab = hardwareMap.servo.get("grab");

        leftRotate = hardwareMap.crservo.get("Left rotator");
        rightRotate = hardwareMap.crservo.get("Right rotator");

        rightRotate.setDirection(CRServo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");

        adjuster = hardwareMap.servo.get("Adjuster");
        adjuster.setPosition(0.75);

        speedStatus = "Normal";
        adjusterStatus = "Raised";

    }

    public void loop(){

        if (gamepad1.a){
            slowness = 2;
            speedStatus = "Slow";
        } else if (gamepad1.y) {
            slowness = 1;
            speedStatus = "Normal";
        }

        if(Toggle.toggle(gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right || gamepad1.dpad_up || gamepad2.dpad_down || gamepad2.dpad_left || gamepad2.dpad_right || gamepad2.dpad_up,0)) {
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
        }else{
            chassis.NormalDrive(gamepad2.left_stick_x / slowness, gamepad2.left_stick_y / slowness);
        }

        if (gamepad1.dpad_down){
            adjuster.setPosition(0.05);
            adjusterStatus = "Lowered";
        }else if (gamepad1.dpad_up){
            adjuster.setPosition(0.75);
            adjusterStatus = "Raised";
        }

        if (gamepad1.right_bumper){
            leftSweeper.setPower(1);
            rightSweeper.setPower(1);
        }else if (gamepad1.right_trigger > 0){
            leftSweeper.setPower(-1);
            rightSweeper.setPower(-1);
        }else{
            leftSweeper.setPower(0);
            rightSweeper.setPower(0);
        }

        leftRotate.setPower(gamepad1.right_stick_y/2);
        rightRotate.setPower(gamepad1.right_stick_y/2);

        if (gamepad1.left_bumper){
            lift.setPower(1);
        }else if (gamepad1.left_trigger > 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);
        }

         if (gamepad2.right_bumper){
            tilt.setPower(.5);
         }else if (gamepad2.left_bumper){
             tilt.setPower(-.5);
         }else{
             tilt.setPower(0);
         }

         if (gamepad2.right_trigger > 0){
             arm.setPower(gamepad2.right_trigger);
         }else if (gamepad2.left_trigger > 0){
             arm.setPower(-gamepad2.left_trigger);
         }else{
             arm.setPower(0);
         }

        rist.setPosition((gamepad2.right_stick_y/2)+.5);
        grab.setPosition((gamepad2.right_stick_x/2)+.5);

        telemetry.addData("Speed", speedStatus);
        telemetry.addData("Adjuster position", adjusterStatus);
        telemetry.addData("Motor Speed", leftWheel.getCurrentPosition() + "," + rightWheel.getCurrentPosition());
    }
}

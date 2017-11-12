package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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

    DcMotor leftSweeper;
    DcMotor rightSweeper;

    Servo leftRotate;
    Servo rightRotate;

    DcMotor lift;

    public void init(){

        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        leftSweeper = hardwareMap.dcMotor.get("Left sweeper");
        rightSweeper = hardwareMap.dcMotor.get("Right sweeper");

        leftSweeper.setDirection(DcMotorSimple.Direction.REVERSE);

        leftRotate = hardwareMap.servo.get("Left rotator");
        rightRotate = hardwareMap.servo.get("Right rotator");

        rightRotate.setDirection(Servo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");
    }

    public void loop(){

        chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.a){
            leftSweeper.setPower(1);
            rightSweeper.setPower(1);
        }else if (gamepad1.y){
            leftSweeper.setPower(-1);
            rightSweeper.setPower(-1);
        }else{
            leftSweeper.setPower(0);
            rightSweeper.setPower(0);
        }

        leftRotate.setPosition((0.75*gamepad1.right_stick_y+1)/2);
        rightRotate.setPosition((0.75*gamepad1.right_stick_y+1)/2);

        if (gamepad1.left_bumper){
            lift.setPower(1);
        }else if (gamepad1.left_trigger > 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);4
        }
    }
}

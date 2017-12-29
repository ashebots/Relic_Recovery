package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
public class Teleop extends OpMode {

    DcMotor left;
    DcMotor right;
    DcMotor arm;
    DcMotor tilt;
    DcMotor pickUpR;
    DcMotor pickUpL;
    Servo placeR;
    Servo placeL;

    Chassis chassis;
    public void init(){
        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        arm = hardwareMap.dcMotor.get("arm");
        tilt = hardwareMap.dcMotor.get("tilt");
        pickUpL = hardwareMap.dcMotor.get("pickUpL");
        pickUpR = hardwareMap.dcMotor.get("pickUpR");
        placeL = hardwareMap.servo.get("placeL");
        placeR = hardwareMap.servo.get("placeR");
        chassis = new Chassis(left, right);
    }

    public void loop(){
        chassis.NormalDrive(gamepad1.left_stick_x, gamepad1.right_stick_y);

        if(gamepad1.a){
            pickUpL.setPower(0.5);
            pickUpR.setPower(0.5);
        }else if (gamepad1.b){
            pickUpL.setPower(-0.5);
            pickUpR.setPower(-0.5);
        }else{
            pickUpL.setPower(0);
            pickUpR.setPower(0);
        }

        if(gamepad1.x){
            placeL.setPosition(0);
            placeR.setPosition(0);
        }else {
            placeL.setPosition(0.5);
            placeR.setPosition(0.5);
        }

        arm.setPower(-gamepad1.right_stick_y);
        if(gamepad1.left_bumper){
            tilt.setPower(.25);
        }else if(gamepad1.right_bumper){
            tilt.setPower(-.25);
        }else{
            tilt.setPower(0);
        }
    }
}

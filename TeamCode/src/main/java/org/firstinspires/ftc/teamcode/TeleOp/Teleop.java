package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by secre on 10/17/2017.
 */

@TeleOp
public class Teleop extends OpMode {

    DcMotor left;
    DcMotor right;
    DcMotor pickUpL;
    DcMotor pickUpR;
    Servo placeR;
    Servo placeL;
    Servo grab;
    boolean pos;

    Chassis chassis;
    public void init(){
        left = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        right = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        pickUpL = hardwareMap.dcMotor.get("pickUpL");
        pickUpR = hardwareMap.dcMotor.get("pickUpR");
        placeR = hardwareMap.servo.get("placeR");
        placeL = hardwareMap.servo.get("placeL");
        grab = hardwareMap.servo.get("grab");
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
            placeL.setPosition(0.5);
            placeR.setPosition(0.5);
        }else {
            placeL.setPosition(0);
            placeR.setPosition(0);
        }

        if(gamepad1.y){
            pos = !pos;
        }

        if(pos){
            grab.setPosition(0.05);
        }else{
            grab.setPosition(0.72);
        }
    }
}

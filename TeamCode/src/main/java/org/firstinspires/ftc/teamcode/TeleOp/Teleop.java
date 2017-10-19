package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by secre on 10/17/2017.
 */

public class Teleop extends OpMode {

    DcMotor left;
    DcMotor right;
    DcMotor pickUp;
    Servo place;

    Chassis chassis;
    public void init(){
        left = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        right = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        pickUp = hardwareMap.dcMotor.get("pickUp");
        place = hardwareMap.servo.get("place");
        chassis = new Chassis(left, right);
    }

    public void loop(){
        chassis.NormalDrive(gamepad1.left_stick_x, gamepad1.right_stick_y);

        if(gamepad1.a){
            pickUp.setPower(0.5);
        }else if (gamepad1.b){
            pickUp.setPower(-0.5);
        }else{
            pickUp.setPower(0);
        }

        if(gamepad1.x){
            place.setPosition(0.5);
        }else {
            place.setPosition(0);
        }


    }
}

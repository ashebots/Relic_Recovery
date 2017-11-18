package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
public class Teleop extends OpMode {

    DcMotor left;
    DcMotor right;
    DcMotor arm;
    DcMotor tilt;

    Chassis chassis;
    public void init(){
        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        arm = hardwareMap.dcMotor.get("arm");
        tilt = hardwareMap.dcMotor.get("tilt");
        chassis = new Chassis(left, right);
    }

    public void loop(){
        chassis.NormalDrive(gamepad1.left_stick_y, gamepad1.left_stick_x);

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

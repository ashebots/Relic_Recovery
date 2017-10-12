package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp(name = "USE THIS ONE!!!!!", group = "A")
public class MainTeleOp extends OpMode{

    Chassis drive;
    DcMotor left;
    DcMotor right;
    DcMotor leftB;
    DcMotor rightB;

    public void init(){
        left = hardwareMap.dcMotor.get("LeftForward");
        leftB = hardwareMap.dcMotor.get("LeftBack");
        right = hardwareMap.dcMotor.get("RightForward");
        rightB = hardwareMap.dcMotor.get("RightBack");
        drive = new Chassis(left, right, leftB, rightB);
    }

    public void loop(){
        drive.HoloMecaDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);
    }
}

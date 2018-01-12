package org.firstinspires.ftc.teamcode.Recreational.Recreational.IanCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 9/30/2017.
 */
@TeleOp
public class Ian4DriveBaseTeleOp extends OpMode {

    DcMotor RightBack;
    DcMotor Rightforward;
    DcMotor LeftBack;
    DcMotor LeftForward;

    Chassis chassis;

    public void init(){

        RightBack = hardwareMap.dcMotor.get("RightBack");
        Rightforward = hardwareMap.dcMotor.get("RightForward");
        LeftBack = hardwareMap.dcMotor.get("LeftBack");
        LeftForward = hardwareMap.dcMotor.get("LeftForward");
        Rightforward.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(LeftForward, Rightforward, LeftBack, RightBack);

    }

    public void loop(){
        chassis.HoloMecaDrive(gamepad1.right_stick_x, gamepad1.right_stick_y);
        chassis.NormalDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);


    }


}

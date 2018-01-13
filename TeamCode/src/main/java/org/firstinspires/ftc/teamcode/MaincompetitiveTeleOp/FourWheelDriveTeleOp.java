package org.firstinspires.ftc.teamcode.MaincompetitiveTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/5/2017.
 */
//@TeleOp
public class FourWheelDriveTeleOp extends OpMode {

        DcMotor RightBack;
        DcMotor RightForward;
        DcMotor LeftBack;
        DcMotor LeftForward;
        Chassis chassis;


    public void init (){

        RightBack = hardwareMap.dcMotor.get("RightBack");
        RightForward = hardwareMap.dcMotor.get("RightForward");
        LeftBack = hardwareMap.dcMotor.get("LeftBack");
        LeftForward = hardwareMap.dcMotor.get("LeftForward");
        RightForward.setDirection(DcMotorSimple.Direction.REVERSE);
        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        chassis = new Chassis(LeftForward, RightForward, LeftBack, LeftForward);
    }

    public void loop (){





    }















}

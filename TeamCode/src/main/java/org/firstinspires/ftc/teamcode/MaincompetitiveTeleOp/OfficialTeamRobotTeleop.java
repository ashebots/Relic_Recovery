package org.firstinspires.ftc.teamcode.MaincompetitiveTeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/19/2017.
 */

public class OfficialTeamRobotTeleop extends LinearOpMode {

    DcMotor rightmotor;
    DcMotor leftmotor;
    DcMotor leftintake;
    DcMotor rightintake;
    Chassis chassis;


    public void runOpMode()  {

        rightmotor = hardwareMap.dcMotor.get("rightmotor");
        leftmotor = hardwareMap.dcMotor.get("leftmotor");
        leftintake = hardwareMap.dcMotor.get("leftintake");
        rightintake = hardwareMap.dcMotor.get("rightmotor");
        rightmotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftmotor.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftmotor, rightmotor);

        chassis = new Chassis(leftintake, rightintake);

        waitForStart();

        chassis.NormalDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);

        chassis.glyphColecter(boolean);







    }
}
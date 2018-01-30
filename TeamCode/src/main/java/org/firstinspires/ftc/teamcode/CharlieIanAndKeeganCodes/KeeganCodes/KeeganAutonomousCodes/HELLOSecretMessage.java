package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class HELLOSecretMessage extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;

    public void runOpMode(){

        LeftMotor = hardwareMap.dcMotor.get("Left wheel");
        RightMotor = hardwareMap.dcMotor.get("Right wheel");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);
        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(400);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(-1);
        RightMotor.setPower(-1);
        sleep(1000);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);
        sleep(1000);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(600);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(1000);
        System.out.print("HI!");

    }

}
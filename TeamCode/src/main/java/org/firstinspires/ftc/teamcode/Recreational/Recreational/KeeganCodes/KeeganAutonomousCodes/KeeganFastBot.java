package org.firstinspires.ftc.teamcode.Recreational.Recreational.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Lenovo on 10/7/2017.
 */
@Autonomous
public class KeeganFastBot extends LinearOpMode{

    DcMotor LeftMotor;
    DcMotor RightMotor;

    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("Left");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        RightMotor = hardwareMap.dcMotor.get("Right");

        LeftMotor.setPower(1);
        RightMotor.setPower(1);

        sleep(5000);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);

        sleep(3500);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);

        sleep(2500);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);

        sleep(1500);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);

        sleep(500);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);

        sleep(350);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);

        sleep(200);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
    }
}
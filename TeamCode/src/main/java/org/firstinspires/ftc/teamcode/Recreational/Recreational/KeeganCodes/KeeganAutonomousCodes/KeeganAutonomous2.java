package org.firstinspires.ftc.teamcode.Recreational.Recreational.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

//@Autonomous
public class KeeganAutonomous2 extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    public void runOpMode(){

        leftMotor = hardwareMap.dcMotor.get("Left");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMotor = hardwareMap.dcMotor.get("Right");

        waitForStart();

        leftMotor.setPower(1);
        rightMotor.setPower(1);

        sleep(5000);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        sleep(150);

        rightMotor.setPower(-0.5);
        leftMotor.setPower(0.5);

        sleep(6000);

        rightMotor.setPower(0);
        leftMotor.setPower(0);

        sleep(150);

        rightMotor.setPower(0.5);
        leftMotor.setPower(-0.5);

        sleep(12000);

        rightMotor.setPower(0);
        leftMotor.setPower(0);

        sleep(1000);

        rightMotor.setPower(-0.5);
        leftMotor.setPower(0.5);

        sleep(6000);

        rightMotor.setPower(1);
        leftMotor.setPower(1);

        sleep(500);

        rightMotor.setPower(-1);
        leftMotor.setPower(-1);

        sleep(450);
    }
}

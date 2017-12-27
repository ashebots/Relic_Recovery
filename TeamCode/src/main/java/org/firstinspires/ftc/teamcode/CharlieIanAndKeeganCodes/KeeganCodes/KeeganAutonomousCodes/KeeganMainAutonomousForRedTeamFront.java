package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Lenovo on 11/2/2017.
 */
@Autonomous
public class KeeganMainAutonomousForRedTeamFront extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor BoxMotorLeft;
    DcMotor BoxMotorRight;
    Servo RotatorLeft;
    Servo RotatorRight;

    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("Left");
        RightMotor = hardwareMap.dcMotor.get("Right");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left intake");
        BoxMotorRight = hardwareMap.dcMotor.get("Right intake");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RotatorLeft = hardwareMap.servo.get("Left rotator");
        RotatorRight = hardwareMap.servo.get("Right rotator");
        RotatorLeft.setDirection(Servo.Direction.REVERSE);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(888);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        BoxMotorLeft.setPower(-1);
        BoxMotorRight.setPower(-1);
        RotatorLeft.setPosition(0);
        RotatorRight.setPosition(0);
        sleep(1000);

        LeftMotor.setPower(-1);
        RightMotor.setPower(-1);
        sleep(400);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(520);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(350);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(1000);

        RotatorLeft.setPosition(0.75);
        RotatorRight.setPosition(0.75);
        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);
    }

}
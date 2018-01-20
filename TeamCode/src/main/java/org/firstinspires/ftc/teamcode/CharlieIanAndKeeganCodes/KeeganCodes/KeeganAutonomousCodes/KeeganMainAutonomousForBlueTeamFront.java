package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Lenovo on 11/2/2017.
 */
@Autonomous
public class KeeganMainAutonomousForBlueTeamFront extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor BoxMotorLeft;
    DcMotor BoxMotorRight;
    CRServo RotatorLeft;
    CRServo RotatorRight;
    Servo LeftFlyRotator;
    Servo RightFlyRotator;

    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("Left wheel");
        RightMotor = hardwareMap.dcMotor.get("Right wheel");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left intake");
        BoxMotorRight = hardwareMap.dcMotor.get("Right intake");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RotatorLeft = hardwareMap.crservo.get("Left rotator");
        RotatorRight = hardwareMap.crservo.get("Right rotator");
        RotatorLeft.setDirection(CRServo.Direction.REVERSE);
        LeftFlyRotator = hardwareMap.servo.get("Left fly rotator");
        RightFlyRotator = hardwareMap.servo.get("Right fly rotator");
        LeftFlyRotator.setDirection(Servo.Direction.REVERSE);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        LeftFlyRotator.setPosition(0.5);
        RightFlyRotator.setPosition(0.5);
        sleep(888);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        BoxMotorLeft.setPower(-1);
        BoxMotorRight.setPower(-1);
        RotatorLeft.setPower(-1);
        RotatorRight.setPower(-1);
        LeftFlyRotator.setPosition(0.5);
        RightFlyRotator.setPosition(0.5);
        sleep(1000);

        LeftMotor.setPower(-1);
        RightMotor.setPower(-1);
        LeftFlyRotator.setPosition(0.75);
        RightFlyRotator.setPosition(0.75);
        sleep(400);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(520);

        LeftMotor.setPower(-1);
        RightMotor.setPower(1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(350);

        LeftMotor.setPower(1);
        RightMotor.setPower(-1);
        sleep(900);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        sleep(10);

        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(1000);

        RotatorLeft.setPower(1);
        RotatorRight.setPower(1);
        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(1500);
    }

}
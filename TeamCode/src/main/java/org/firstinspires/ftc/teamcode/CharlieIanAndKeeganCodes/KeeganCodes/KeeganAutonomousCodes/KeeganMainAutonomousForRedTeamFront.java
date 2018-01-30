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
public class KeeganMainAutonomousForRedTeamFront extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor BoxMotorLeft;
    DcMotor BoxMotorRight;
    Servo RotatorFlyLeft;
    Servo RotatorFlyRight;
    CRServo LeftFlyWheel;
    CRServo LeftRotator;
    CRServo RightFlyWheel;
    CRServo RightRotator;

    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("Left wheel");
        RightMotor = hardwareMap.dcMotor.get("Right wheel");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left sweeper");
        BoxMotorRight = hardwareMap.dcMotor.get("Right sweeper");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RotatorFlyLeft = hardwareMap.servo.get("Left fly rotator");
        RotatorFlyRight = hardwareMap.servo.get("Right fly rotator");
        RotatorFlyLeft.setDirection(Servo.Direction.REVERSE);
        LeftFlyWheel = hardwareMap.crservo.get("Left fly wheel");
        RightFlyWheel = hardwareMap.crservo.get("Right fly wheel");
        LeftFlyWheel.setDirection(CRServo.Direction.REVERSE);
        LeftRotator = hardwareMap.crservo.get("Left rotator");
        RightRotator = hardwareMap.crservo.get("Right rotator");
        LeftRotator.setDirection(CRServo.Direction.REVERSE);


        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        RotatorFlyLeft.setPosition(0.5);
        RotatorFlyRight.setPosition(0.5);
        sleep(888);

        LeftMotor.setPower(0);
        RightMotor.setPower(0);
        BoxMotorLeft.setPower(-1);
        BoxMotorRight.setPower(-1);
        LeftFlyWheel.setPower(-1);
        RightFlyWheel.setPower(-1);
        LeftRotator.setPower(-1);
        RightRotator.setPower(-1);
        RotatorFlyLeft.setPosition(0.5);
        RotatorFlyRight.setPosition(0.5);
        sleep(1000);

        LeftMotor.setPower(-1);
        RightMotor.setPower(-1);
        LeftRotator.setPower(1);
        RightRotator.setPower(1);
        RotatorFlyLeft.setPosition(0.75);
        RotatorFlyRight.setPosition(0.75);
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

        RotatorFlyLeft.setPosition(0.75);
        RotatorFlyRight.setPosition(0.75);
        LeftMotor.setPower(1);
        RightMotor.setPower(1);
        sleep(500);
    }
}
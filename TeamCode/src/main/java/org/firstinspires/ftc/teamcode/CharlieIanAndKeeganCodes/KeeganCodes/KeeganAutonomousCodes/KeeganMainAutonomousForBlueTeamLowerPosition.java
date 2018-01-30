package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Lenovo on 10/28/2017.
 */
@Autonomous
public class KeeganMainAutonomousForBlueTeamLowerPosition extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor BoxMotorLeft;
    DcMotor BoxMotorRight;
    CRServo LeftRotator;
    CRServo RightRotator;
    Servo RightFlyRotator;
    Servo LeftFlyRotator;
    CRServo RightFlyWheel;
    CRServo LeftFlyWheel;

    public void runOpMode() {

        RightMotor = hardwareMap.dcMotor.get("Right wheel");
        LeftMotor = hardwareMap.dcMotor.get("Left wheel");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left sweeper");
        BoxMotorRight = hardwareMap.dcMotor.get("Right sweeper");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftRotator = hardwareMap.crservo.get("Left rotator");
        RightRotator = hardwareMap.crservo.get("Right rotator");
        LeftRotator.setDirection(CRServo.Direction.REVERSE);
        LeftFlyRotator = hardwareMap.servo.get("Left fly rotator");
        RightFlyRotator = hardwareMap.servo.get("Right fly rotator");
        LeftFlyRotator.setDirection(Servo.Direction.REVERSE);
        LeftFlyWheel = hardwareMap.crservo.get("Left fly wheel");
        RightFlyWheel = hardwareMap.crservo.get("Right fly wheel");
        LeftFlyWheel.setDirection(CRServo.Direction.REVERSE);

        RightMotor.setPower(1);
        LeftMotor.setPower(1);
        sleep(888);

        RightMotor.setPower(-1);
        LeftMotor.setPower(1);
        sleep(900);

       RightMotor.setPower(1);
       LeftMotor.setPower(1);
       sleep(500);

       BoxMotorRight.setPower(-1);
       BoxMotorLeft.setPower(-1);
       sleep(100);

       RightMotor.setPower(1);
       LeftMotor.setPower(-1);
       sleep(900);

       RightMotor.setPower(1);
       LeftMotor.setPower(1);
       RightFlyRotator.setPosition(0.5);
       LeftFlyRotator.setPosition(0.5);
       sleep(2000);

       RightRotator.setPower(-1);
       LeftRotator.setPower(-1);
       BoxMotorRight.setPower(-1);
       BoxMotorLeft.setPower(-1);
       RightFlyRotator.setPosition(0.5);
       LeftFlyRotator.setPosition(0.5);
       RightFlyWheel.setPower(-1);
       LeftFlyWheel.setPower(-1);
       sleep(1000);

       RightRotator.setPower(1);
       LeftRotator.setPower(1);
       RightMotor.setPower(1);
       LeftMotor.setPower(1);
       LeftFlyRotator.setPosition(0.75);
       RightFlyRotator.setPosition(0.75);
       sleep(500);

       RightMotor.setPower(-1);
       LeftMotor.setPower(-1);
       sleep(1500);

    }
}

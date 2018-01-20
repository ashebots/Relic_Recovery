package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


import static android.os.SystemClock.sleep;

/**
 * Created by Lenovo on 11/2/2017.
 */
@Autonomous
public class KeeganMainAutonomousForRedTeamLowerPosition extends LinearOpMode{

    DcMotor LeftMotor;
    DcMotor RightMotor;
    DcMotor BoxMotorLeft;
    DcMotor BoxMotorRight;
    CRServo LeftRotoator;
    CRServo RightRotator;
    CRServo LeftFlyWheel;
    CRServo RightFlyWheel;
    Servo LeftFlyRotator;
    Servo RightFlyRotator;

    public void runOpMode() {

        RightMotor = hardwareMap.dcMotor.get("Right wheel");
        LeftMotor = hardwareMap.dcMotor.get("Left wheel");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left sweeper");
        BoxMotorRight = hardwareMap.dcMotor.get("Right sweeper");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftRotoator = hardwareMap.crservo.get("Left rotator");
        RightRotator = hardwareMap.crservo.get("Right rotator");
        LeftRotoator.setDirection(CRServo.Direction.REVERSE);
        LeftFlyWheel = hardwareMap.crservo.get("Left fly wheel");
        RightFlyWheel = hardwareMap.crservo.get("Right fly wheel");
        LeftFlyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftFlyRotator = hardwareMap.servo.get("left fly rotator");
        RightFlyRotator = hardwareMap.servo.get("Right fly rotator");
        LeftFlyRotator.setDirection(Servo.Direction.REVERSE);

        RightMotor.setPower(1);
        LeftMotor.setPower(1);
        sleep(888);

        RightMotor.setPower(1);
        LeftMotor.setPower(-1);
        sleep(900);

        RightMotor.setPower(1);
        LeftMotor.setPower(1);
        sleep(500);

        RightMotor.setPower(0);
        LeftMotor.setPower(0);
        LeftFlyRotator.setPosition(0.5);
        RightFlyRotator.setPosition(0.5);
        sleep(1000);

        BoxMotorRight.setPower(-1);
        BoxMotorLeft.setPower(-1);
        LeftFlyRotator.setPosition(0.5);
        RightFlyRotator.setPosition(0.5);
        LeftFlyWheel.setPower(-1);
        RightFlyWheel.setPower(-1);
        sleep(200);

        RightMotor.setPower(-1);
        LeftMotor.setPower(1);
        LeftFlyRotator.setPosition(0.75);
        RightFlyRotator.setPosition(0.75);
        sleep(900);

        RightMotor.setPower(1);
        LeftMotor.setPower(1);
        sleep(2000);

        RightRotator.setPower(0);
        LeftRotoator.setPower(0);
        BoxMotorRight.setPower(-1);
        BoxMotorLeft.setPower(-1);
        sleep(500);

        RightRotator.setPower(1);
        LeftRotoator.setPower(1);
        RightMotor.setPower(1);
        LeftMotor.setPower(1);
        sleep(500);

        RightMotor.setPower(-1);
        LeftMotor.setPower(-1);
        sleep(500);

    }

}

package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    Servo LeftRotoator;
    Servo RightRotator;

    public void runOpMode() {

        RightMotor = hardwareMap.dcMotor.get("Right");
        LeftMotor = hardwareMap.dcMotor.get("Left");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BoxMotorLeft = hardwareMap.dcMotor.get("Left intake");
        BoxMotorRight = hardwareMap.dcMotor.get("Right intake");
        BoxMotorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftRotoator = hardwareMap.servo.get("Left rotator");
        RightRotator = hardwareMap.servo.get("Right rotator");
        LeftRotoator.setDirection(Servo.Direction.REVERSE);

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
       sleep(2000);

       RightRotator.setPosition(0);
       LeftRotoator.setPosition(0);
       BoxMotorRight.setPower(-1);
       BoxMotorLeft.setPower(-1);
       sleep(500);

       RightRotator.setPosition(0.75);
       LeftRotoator.setPosition(0.75);
       RightMotor.setPower(1);
       LeftMotor.setPower(1);
       sleep(500);

       RightMotor.setPower(-1);
       LeftMotor.setPower(-1);
       sleep(1500);

    }
}

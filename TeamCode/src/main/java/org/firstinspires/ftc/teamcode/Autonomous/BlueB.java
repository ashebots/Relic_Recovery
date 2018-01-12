package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Autonomous
public class BlueB extends LinearOpMode{

    ImuChassis chassis;
    VueMarkID mark;

    RelicRecoveryVuMark vueMark;

    DcMotor left;
    DcMotor right;

    CRServo placeL;
    CRServo placeR;

    BNO055IMU imu;

    public void runOpMode() throws InterruptedException {
        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        placeL = hardwareMap.crservo.get("Left rotator");
        placeR = hardwareMap.crservo.get("Right rotator");
        placeR.setDirection(DcMotorSimple.Direction.REVERSE);
        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        mark = new VueMarkID(hardwareMap);

        chassis = new ImuChassis(left, right, imu, 2960d);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        placeL.setPower(-.3);
        placeR.setPower(-.3);
        sleep(500);
        placeL.setPower(0);
        placeR.setPower(0);

        chassis.driveXFeet(1.295, 0.8);

        vueMark = mark.vueName();

        chassis.driveXFeet(1, 0.8);
        chassis.turnToAngle(-90, .5);

        switch (vueMark){
            case CENTER:
                chassis.driveXFeet(1/3,0.4);
                break;
            case LEFT:
                chassis.driveXFeet(1,0.4);
                break;
            default:
                chassis.driveXFeet(5/3, 0.4);
                break;
        }

        chassis.turnToAngle(180, .5);
        chassis.driveXFeet(-1, 0.4);

        placeL.setPower(.5);
        placeR.setPower(.5);
        sleep(750);
        placeL.setPower(0);
        placeR.setPower(0);

        chassis.driveXFeet(0.5, 0.5);
    }
}

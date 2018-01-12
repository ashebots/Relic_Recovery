package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Autonomous
public class BlueA extends LinearOpMode{

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

        chassis = new ImuChassis(left, right, imu, 2960.0);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

         waitForStart();

        placeL.setPower(0.3);
        placeR.setPower(0.3);
        sleep(500);
        placeL.setPower(0);
        placeR.setPower(0);

        chassis.driveXFeet(0.5d, 0.5);

        vueMark = mark.vueName();

        switch (vueMark){

            case CENTER:
                chassis.driveXFeet(2.5d, 0.5);
                break;
            case RIGHT:
                chassis.driveXFeet(3d, 0.5);
                break;
            default:
                chassis.driveXFeet(7/3d-0.5d,0.5);
        }


        sleep(250);
        chassis.turnToAngle(80, 0.5);


        sleep(250);
        chassis.driveXFeet(-0.8, 0.4);

        placeL.setPower(-0.3);
        placeR.setPower(-0.3);
        sleep(1000);
        placeL.setPower(0);
        placeR.setPower(0);

        chassis.driveXFeet(0.5, 0.5);

    }
}

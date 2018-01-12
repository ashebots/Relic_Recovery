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

    DcMotor intakeL;
    DcMotor intakeR;

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

        intakeL = hardwareMap.dcMotor.get("Left sweeper");
        intakeR = hardwareMap.dcMotor.get("Right sweeper");
        intakeL.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        mark = new VueMarkID(hardwareMap);

        chassis = new ImuChassis(left, right, imu, 2960.0);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

         waitForStart();

        placeL.setPower(0.5);
        placeR.setPower(0.5);
        sleep(1650);

        placeL.setPower(0);
        placeR.setPower(0);

        intakeL.setPower(1);
        intakeR.setPower(1);

        sleep(1500);

        intakeL.setPower(0);
        intakeR.setPower(0);

        chassis.driveXFeet(0.25d, 0.5);

        sleep(1000);
        vueMark = mark.vueName();

        sleep(250);
        switch (vueMark){

            case CENTER:
                chassis.driveXFeet(3d+left.getCurrentPosition()/713.014145d, 0.5);

                telemetry.addData("Position", "Center");
                telemetry.update();
                break;

            case RIGHT:
                chassis.driveXFeet(3.5d+left.getCurrentPosition()/713.014145d, 0.5);

                telemetry.addData("Position", "Right");
                telemetry.update();
                break;

            default:
                chassis.driveXFeet(7/3d+left.getCurrentPosition()/713.014145d,0.5);

                telemetry.addData("Position", "Left (Or unknown)");
                telemetry.update();
                break;
        }


        sleep(250);
        chassis.turnToAngle(80, 0.5);

        placeL.setPower(-0.5);
        placeR.setPower(-0.5);
        sleep(1500);

        chassis.driveAtSpeed(-0.4);
        sleep(1000);

        placeL.setPower(-0.3);
        placeR.setPower(-0.3);
        sleep(1000);
        placeL.setPower(0);
        placeR.setPower(0);

        chassis.driveXFeet(0.5, 0.2);

    }
}

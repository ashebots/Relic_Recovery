package org.firstinspires.ftc.teamcode.Autonomous;

import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ModularConstants;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Autonomous
public class RedB extends LinearOpMode {

    private ImuChassis chassis;
    private VueMarkID mark;

    private RelicRecoveryVuMark vueMark = RelicRecoveryVuMark.UNKNOWN;

    private Servo jewelArm;
    private ColorSensor color;

    private DcMotor left;
    private DcMotor right;

    private DcMotor intakeLeft;
    private DcMotor intakeRight;

    private Servo leftTray;
    private Servo rightTray;

    private BNO055IMU imu;

    public void runOpMode() throws InterruptedException {

        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeLeft = hardwareMap.dcMotor.get("Left intake");
        intakeRight = hardwareMap.dcMotor.get("Right intake");

        intakeRight.setDirection(DcMotorSimple.Direction.REVERSE);

        leftTray = hardwareMap.servo.get("Left tray");
        rightTray = hardwareMap.servo.get("Right tray");

        leftTray.setDirection(Servo.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        mark = new VueMarkID(hardwareMap);

        jewelArm = hardwareMap.servo.get("Jewel arm");
        color = hardwareMap.colorSensor.get("Jewel sensor");

        chassis = new ImuChassis(left, right, imu, this);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        vueMark = mark.vueName();

        leftTray.setPosition(0);
        rightTray.setPosition(0);

        sleep(1000);

        leftTray.setPosition(0.5);
        rightTray.setPosition(0.5);

        intakeLeft.setPower(0.75);
        intakeRight.setPower(0.75);

        sleep(1000);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        jewelArm.setPosition(0.6);

        sleep(750);

        if (color.blue() > color.red()){

            telemetry.addData("Jewel color", "Blue");
            chassis.driveXFeet(-0.25, 0.15);

        }else if (color.red() > color.blue()){

            telemetry.addData("Jewel color", "Red");
            chassis.driveXFeet(0.25, 0.15);

        }
        sleep(250);

        jewelArm.setPosition(0.4);

        chassis.driveFromStart(2, 0.5);
        chassis.turnToAngle(-80, 0.5);

        switch (vueMark){

            case CENTER:
                chassis.driveXFeet(0.9325, 0.5);

                telemetry.addData("Position", "Center");
                telemetry.update();
                break;

            case RIGHT:
                chassis.driveXFeet(0.3125, 0.25);

                telemetry.addData("Position", "Right");
                telemetry.update();
                break;

            default:
                chassis.driveXFeet(1.5625, 0.5);

                telemetry.addData("Position", "Left (Or unknown)");
                telemetry.update();
                break;
        }

        chassis.turnToAngle(-10, 0.5);

        left.setPower(0.3);
        right.setPower(0.3);

        sleep(150);

        intakeLeft.setPower(-1);
        intakeRight.setPower(-1);

        sleep(1500);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        chassis.driveXFeet(-0.5, 0.2);

    }
}

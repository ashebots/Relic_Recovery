package org.firstinspires.ftc.teamcode.Autonomous;

import  com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.CRServo;
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

    private RelicRecoveryVuMark vueMark;

    private Servo jewelArm;
    private Servo jewelScorer;
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
        jewelScorer = hardwareMap.servo.get("Jewel scorer");
        color = hardwareMap.colorSensor.get("Jewel sensor");

        chassis = new ImuChassis(left, right, imu, this);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        vueMark = mark.vueName();

        jewelScorer.setPosition(0.5);

        leftTray.setPosition(0.1);
        rightTray.setPosition(0.1);

        sleep(500);

        intakeLeft.setPower(0.5);
        intakeRight.setPower(0.5);

        sleep(1500);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        jewelArm.setPosition(0.9);

        sleep(750);

        if (color.blue() > color.red()){

            telemetry.addData("Jewel color", "Blue");
            jewelScorer.setPosition(0.3);

        }else if (color.red() > color.blue()){

            telemetry.addData("Jewel color", "Red");
            jewelScorer.setPosition(0.7);

        }
        sleep(750);

        jewelArm.setPosition(0.325);

        sleep(500);

        chassis.driveXFeet(2, 0.5);

        switch (vueMark){

            case CENTER:

                telemetry.addData("Position", "Center");
                telemetry.update();

                chassis.turnToAngle(174, 0.4);

                break;

            case RIGHT:

                telemetry.addData("Position", "Right");
                telemetry.update();

                chassis.turnToAngle(-176, 0.4);

                break;

            default:

                telemetry.addData("Position", "Left (Or unknown)");
                telemetry.update();

                chassis.turnToAngle(165, 0.4);

                break;
        }

        leftTray.setPosition(0.75);
        rightTray.setPosition(0.75);

        sleep(500);

        chassis.driveAtSpeed(-0.5);

        sleep(2500);

        chassis.driveAtSpeed(0.3);

        sleep(75);

        chassis.driveAtSpeed(0);

    }
}

package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ModularConstants;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Autonomous
public class RedA extends LinearOpMode {

    private ImuChassis chassis;
    private VueMarkID mark;

    private RelicRecoveryVuMark vueMark;

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

        leftTray = hardwareMap.servo.get("Left tray");
        rightTray = hardwareMap.servo.get("Right tray");
        rightTray.setDirection(Servo.Direction.REVERSE);

        intakeLeft = hardwareMap.dcMotor.get("Left intake");
        intakeRight = hardwareMap.dcMotor.get("Right intake");
        intakeLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        mark = new VueMarkID(hardwareMap);

        jewelArm = hardwareMap.servo.get("Jewel arm");
        color = hardwareMap.colorSensor.get("Jewel sensor");

        chassis = new ImuChassis(left, right, imu, 2960.0, this);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        for (int i = 0; i < 10 && opModeIsActive(); i++) { vueMark = mark.vueName(); }

        /* TBD */ leftTray.setPosition(0.875);
        /* TBD */ rightTray.setPosition(0.875);

        intakeLeft.setPower(1);
        intakeRight.setPower(1);

        sleep(2000);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        /* TBD */ jewelArm.setPosition(0.75);

        if (color.blue() > color.red()){
            /* TBD */ chassis.driveXFeet(-0.2, 0.15);
        }else if (color.red() > color.blue()){
            /* TBD */ chassis.driveXFeet(0.2, 0.15);
        }

        /* TBD */  jewelArm.setPosition(0.5);

        switch (vueMark){

            case CENTER:
                /* TBD */ chassis.driveFromStart(3, 0.5);

                telemetry.addData("Position", "Center");
                telemetry.update();
                break;

            case RIGHT:
                /* TBD */ chassis.driveFromStart(3.5, 0.5);

                telemetry.addData("Position", "Right");
                telemetry.update();
                break;

            default:
                /* TBD */ chassis.driveFromStart(7/3, 0.5);

                telemetry.addData("Position", "Left (Or unknown)");
                telemetry.update();
                break;
        }

        /* TBD */ chassis.turnToAngle(-85, 0.3);

        /* TBD */ leftTray.setPosition(0.5);
        /* TBD */ rightTray.setPosition(0.5);

        left.setPower(-0.3);
        right.setPower(-0.3);

        /* TBD */ sleep(1000);

        chassis.driveXFeet(0.5, 0.2);

        /*
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

        chassis.driveXFeet(0.125 - 7/6d, 0.5);

        sleep(1000);
        vueMark = mark.vueName();

        sleep(250);
        switch (vueMark){

            case CENTER:
                chassis.driveXFeet(-11/6d+left.getCurrentPosition()/713.014145d, 0.5);

                telemetry.addData("Position", "Center");
                telemetry.update();
                break;

            case RIGHT:

                chassis.driveXFeet(-7/6d+left.getCurrentPosition()/713.014145d,0.5);

                telemetry.addData("Position", "Right (Or unknown)");
                telemetry.update();
                break;

            default:

                chassis.driveXFeet(-14/6d+left.getCurrentPosition()/713.014145d, 0.5);

                telemetry.addData("Position", "Left");
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
        */

    }
}

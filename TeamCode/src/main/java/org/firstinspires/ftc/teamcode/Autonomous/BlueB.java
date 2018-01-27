package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

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
public class BlueB extends LinearOpMode{

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

        chassis = new ImuChassis(left, right, imu, this);
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
            /* TBD */ chassis.driveXFeet(0.2, 0.15);
        }else if (color.red() > color.blue()){
            /* TBD */ chassis.driveXFeet(-0.2, 0.15);
        }

        /* TBD */  jewelArm.setPosition(0.5);

        /*
        placeL.setPower(-0.3);
        placeR.setPower(-0.3);
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
        */
    }
}

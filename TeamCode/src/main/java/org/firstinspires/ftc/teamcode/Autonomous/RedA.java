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

    private BNO055IMU imu;

    public void runOpMode() throws InterruptedException {

        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        left.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeLeft = hardwareMap.dcMotor.get("Left intake");
        intakeRight = hardwareMap.dcMotor.get("Right intake");
        intakeRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        mark = new VueMarkID(hardwareMap);

        jewelArm = hardwareMap.servo.get("Jewel arm");
        color = hardwareMap.colorSensor.get("Jewel sensor");

        chassis = new ImuChassis(left, right, imu, this);
        chassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        for (int i = 0; i < 10 && opModeIsActive(); i++) { vueMark = mark.vueName(); }

        intakeLeft.setPower(1);
        intakeRight.setPower(1);

        sleep(250);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        jewelArm.setPosition(0.6);
        /*
        if (color.blue() > color.red()){
            chassis.driveXFeet(-0.25, 0.15);
        }else if (color.red() > color.blue()){
            chassis.driveXFeet(0.25, 0.15);
        }
        */
        jewelArm.setPosition(0.4);

        switch (vueMark){

            case CENTER:
                chassis.driveFromStart(3, 0.5);

                telemetry.addData("Position", "Center");
                telemetry.update();
                break;

            case RIGHT:
                chassis.driveFromStart(2.375, 0.5);

                telemetry.addData("Position", "Right");
                telemetry.update();
                break;

            default:
                chassis.driveFromStart(3.625, 0.5);

                telemetry.addData("Position", "Left (Or unknown)");
                telemetry.update();
                break;
        }

        chassis.turnToAngle(85, 0.3);

        left.setPower(0.3);
        right.setPower(0.3);

        sleep(750);

        intakeLeft.setPower(-1);
        intakeRight.setPower(-1);

        sleep(500);

        intakeLeft.setPower(0);
        intakeRight.setPower(0);

        chassis.driveXFeet(-0.5, 0.2);

    }
}

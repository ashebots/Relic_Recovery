package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
public class MainTeleOp extends OpMode {

    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private Chassis chassis;

    private int slowness;
    private String driveMode;

    private DcMotor leftIntake;
    private DcMotor rightIntake;

    private Servo leftTray;
    private Servo rightTray;

    private String trayPos;

    private DcMotor lift;
    private boolean liftIsRaised;

    private Servo jewelArm;
    private Servo jewelScorer;

    public void init(){

        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        leftIntake = hardwareMap.dcMotor.get("Left intake");
        rightIntake = hardwareMap.dcMotor.get("Right intake");

        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        leftTray = hardwareMap.servo.get("Left tray");
        rightTray = hardwareMap.servo.get("Right tray");

        leftTray.setDirection(Servo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        jewelArm = hardwareMap.servo.get("Jewel arm");
        jewelScorer = hardwareMap.servo.get("Jewel scorer");

        Toggle.resetStates();
    }

    public void loop() {

        jewelArm.setPosition(0.325);
        jewelScorer.setPosition(0.7);

        slowness = Toggle.numChange(gamepad1.dpad_right, gamepad1.dpad_left, 4, 1);

        if (Toggle.toggle(gamepad1.b, 0)) {

            chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
            driveMode = "Reverse";

        } else {

            chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
            driveMode = "Forward";

        }


        if (gamepad1.right_bumper) {

            leftIntake.setPower(0.5);
            rightIntake.setPower(0.5);

            leftTray.setPosition(0.1);
            rightTray.setPosition(0.1);

            Toggle.setToggle(1, true);

            trayPos = "Intake";

        } else if (gamepad1.right_trigger > 0) {

            leftIntake.setPower(-0.5);
            rightIntake.setPower(-0.5);

        } else {

            leftIntake.setPower(0);
            rightIntake.setPower(0);

        }

        if (!gamepad1.right_bumper && Toggle.toggle(gamepad1.a, 1)){

            leftTray.setPosition(0.25);
            rightTray.setPosition(0.25);

            trayPos = "Lift";

        }else if (!gamepad1.right_bumper){

            leftTray.setPosition(0.8);
            rightTray.setPosition(0.8);

            trayPos = "Dump";

        }

        liftIsRaised = Toggle.toggle(gamepad1.left_bumper, 2);

        if (liftIsRaised){
            lift.setTargetPosition(3750);
        }else {
            lift.setTargetPosition(0);
        }

        lift.setPower(1);

        telemetry.addData("Speed", 100/slowness+"%");
        telemetry.addData("Drive mode", driveMode);

        telemetry.addData("Lift is raised", liftIsRaised);
        telemetry.addData("Tray position", trayPos);
    }
}
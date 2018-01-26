package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
public class MainTeleOp extends OpMode{

    DcMotor leftWheel;
    DcMotor rightWheel;

    Chassis chassis;

    int slowness;
    String driveMode;

    DcMotor leftIntake;
    DcMotor rightIntake;

    Servo leftTray;
    Servo rightTray;

    String trayStatus;

    DcMotor lift;

    int liftPos;

    Servo adjusterR;
    Servo adjusterL;

    String adjusterStatus;

    CRServo armWheelR;
    CRServo armWheelL;

    public void init(){

        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        leftIntake = hardwareMap.dcMotor.get("Left intake");
        rightIntake = hardwareMap.dcMotor.get("Right intake");
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        leftTray = hardwareMap.servo.get("Left tray");
        rightTray = hardwareMap.servo.get("Right tray");
        rightTray.setDirection(Servo.Direction.REVERSE);

        trayStatus = "Dump";

        lift = hardwareMap.dcMotor.get("Lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        adjusterR = hardwareMap.servo.get("Right intake rotator");
        adjusterR.setPosition(0.95);
        adjusterL = hardwareMap.servo.get("Left intake rotator");
        adjusterL.setDirection(Servo.Direction.REVERSE);
        adjusterL.setPosition(0.95);

        armWheelR = hardwareMap.crservo.get("Right intake wheel");
        armWheelL = hardwareMap.crservo.get("Left intake wheel");
        armWheelL.setDirection(DcMotorSimple.Direction.REVERSE);

        driveMode = "Normal";
        adjusterStatus = "Raised";

        Toggle.resetStates();
    }

    public void loop(){

        slowness = Toggle.numChange(gamepad1.dpad_right, gamepad1.dpad_left, 5, 0);

        if (Toggle.toggle(gamepad1.b, 0)){
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
            driveMode = "Reverse";
        }else {
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
            driveMode = "Forward";
        }

        if (Toggle.toggle(gamepad1.x || gamepad2.x, 1)){
            adjusterL.setPosition(0.35);
            adjusterR.setPosition(0.35);
            adjusterStatus = "Lowered";
        }else {
            adjusterL.setPosition(0.95);
            adjusterR.setPosition(0.95);
            adjusterStatus = "Raised";
        }

        if (gamepad1.right_bumper){

            leftIntake.setPower(1);
            rightIntake.setPower(1);

            armWheelL.setPower(0.5);
            armWheelR.setPower(0.5);

            leftTray.setPosition(0.875);
            rightTray.setPosition(0.875);

            trayStatus = "Intake";

        }else if (gamepad1.right_trigger > 0){

            leftIntake.setPower(-1);
            rightIntake.setPower(-1);

            armWheelL.setPower(-0.5);
            armWheelR.setPower(-0.5);

        }else{

            leftIntake.setPower(0);
            rightIntake.setPower(0);

            armWheelL.setPower(0);
            armWheelR.setPower(0);

        }

        if (!gamepad1.right_bumper && Toggle.toggle(gamepad1.a, 2)){

            leftTray.setPosition(0.75);
            rightTray.setPosition(0.75);

            trayStatus = "Lift";

        }else if (!gamepad1.right_bumper){

            leftTray.setPosition(0.5);
            rightTray.setPosition(0.5);

            trayStatus = "Dump";
        }

        liftPos = Toggle.numChange(gamepad1.left_trigger > 0.5, gamepad1.left_bumper, 4, 2);

        lift.setTargetPosition(1105*(liftPos-1));
        lift.setPower(1);

        if (gamepad1.left_bumper){
            lift.setPower(1);
        }else if (gamepad1.left_trigger > 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);
        }

        telemetry.addData("Speed", (int)(100/slowness)+"%");
        telemetry.addData("Drive mode", driveMode);

        telemetry.addData("Tray position", trayStatus);
        telemetry.addData("Lift position", liftPos);

        telemetry.addData("Adjuster position", adjusterStatus);
    }
}

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
public class MainTeleOp extends OpMode{

    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private Chassis chassis;

    private int slowness;
    private String driveMode;

    private DcMotor leftIntake;
    private DcMotor rightIntake;

    private Servo leftTray;
    private Servo rightTray;

    private DcMotor lift;

    private int liftPos;

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

        rightTray.setDirection(Servo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setDirection(DcMotorSimple.Direction.REVERSE);

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

        if (gamepad1.right_bumper){

            leftIntake.setPower(0.75);
            rightIntake.setPower(0.75);

        }else if (gamepad1.right_trigger > 0){

            leftIntake.setPower(-0.75);
            rightIntake.setPower(-0.75);

        }else{

            leftIntake.setPower(0);
            rightIntake.setPower(0);

        }

        leftTray.setPosition((gamepad1.right_stick_y+1)/2);
        rightTray.setPosition((gamepad1.right_stick_y+1)/2);

        liftPos = Toggle.numChange(gamepad1.left_trigger > 0.25, gamepad1.left_bumper, 4, 2);

        lift.setTargetPosition(1105*(liftPos-1));
        lift.setPower(1);

        telemetry.addData("Speed", 100/slowness+"%");
        telemetry.addData("Drive mode", driveMode);

        telemetry.addData("Lift position", liftPos);

    }
}
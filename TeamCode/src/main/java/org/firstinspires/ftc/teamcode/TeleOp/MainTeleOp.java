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

    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private Chassis chassis;

    private int slowness;
    private String driveMode;

    private DcMotor leftIntake;
    private DcMotor rightIntake;

<<<<<<< HEAD
    private Servo leftTray;
    private Servo rightTray;

    private String trayStatus;
=======
<<<<<<< HEAD
    Servo leftRotate;
    Servo rightRotate;
=======
    Servo leftTray;
    Servo rightTray;

    String trayStatus;
>>>>>>> e7cd22a3cbe007b9c88a52e5c98839e603f538b0
>>>>>>> b55a3946d8557a1de4a98c70f235e4c38900eee0

    private DcMotor lift;

    private int liftPos;

    private CRServo intakeWheelRight;
    private CRServo intakeWheelLeft;

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

<<<<<<< HEAD
        leftRotate = hardwareMap.servo.get("Left rotator");
        rightRotate = hardwareMap.servo.get("Right rotator");

        rightRotate.setDirection(Servo.Direction.REVERSE);
=======
        trayStatus = "Dump";
>>>>>>> e7cd22a3cbe007b9c88a52e5c98839e603f538b0

        lift = hardwareMap.dcMotor.get("Lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intakeWheelRight = hardwareMap.crservo.get("Right intake wheel");
        intakeWheelLeft = hardwareMap.crservo.get("Left intake wheel");

        intakeWheelLeft.setDirection(DcMotorSimple.Direction.REVERSE);

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

            leftIntake.setPower(1);
            rightIntake.setPower(1);

            intakeWheelLeft.setPower(0.5);
            intakeWheelRight.setPower(0.5);

            leftTray.setPosition(0.45);
            rightTray.setPosition(0.45);

            trayStatus = "Intake";

             Toggle.setToggle(2, true);

        }else if (gamepad1.right_trigger > 0){

            leftIntake.setPower(-1);
            rightIntake.setPower(-1);

            intakeWheelLeft.setPower(-0.5);
            intakeWheelRight.setPower(-0.5);

        }else{

            leftIntake.setPower(0);
            rightIntake.setPower(0);

            intakeWheelLeft.setPower(0);
            intakeWheelRight.setPower(0);

        }

<<<<<<< HEAD
        if(Toggle.toggle(gamepad1.a,3)){
            leftRotate.setPosition(0.7);
            rightRotate.setPosition(0.7);
        }else if (gamepad1.right_trigger > 0 || gamepad1.right_bumper){
            leftRotate.setPosition(0.4);
            rightRotate.setPosition(0.4);
        }else{
            leftRotate.setPosition(0.45);
            rightRotate.setPosition(0.45);
        }
=======
        if (!gamepad1.right_bumper && Toggle.toggle(gamepad1.a, 2)){

            leftTray.setPosition(0.4);
            rightTray.setPosition(0.4);

            trayStatus = "Lift";
>>>>>>> e7cd22a3cbe007b9c88a52e5c98839e603f538b0

        }else if (!gamepad1.right_bumper){

            leftTray.setPosition(0.7);
            rightTray.setPosition(0.7);

            trayStatus = "Dump";
        }

        liftPos = Toggle.numChange(gamepad1.left_trigger > 0.25, gamepad1.left_bumper, 4, 2);

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

    }
}
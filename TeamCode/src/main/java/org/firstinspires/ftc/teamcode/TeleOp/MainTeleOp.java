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

    DcMotor leftSweeper;
    DcMotor rightSweeper;

    CRServo leftRotate;
    CRServo rightRotate;

    DcMotor lift;

    int liftPos;

    Servo adjusterR;
    Servo adjusterL;

    String adjusterStatus;

    //CRServo armWheelR;
    CRServo armWheelL;

    public void init(){

        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        leftSweeper = hardwareMap.dcMotor.get("Left sweeper");
        rightSweeper = hardwareMap.dcMotor.get("Right sweeper");
        leftSweeper.setDirection(DcMotorSimple.Direction.REVERSE);


        leftRotate = hardwareMap.crservo.get("Left rotator");
        rightRotate = hardwareMap.crservo.get("Right rotator");

        rightRotate.setDirection(CRServo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        adjusterR = hardwareMap.servo.get("Right fly rotator");
        adjusterR.setPosition(0.95);
        adjusterL = hardwareMap.servo.get("Left fly rotator");
        adjusterL.setDirection(Servo.Direction.REVERSE);
        adjusterL.setPosition(0.95);
        //armWheelR = hardwareMap.crservo.get("Right fly wheel");
        armWheelL = hardwareMap.crservo.get("Left fly wheel");
        armWheelL.setDirection(DcMotorSimple.Direction.REVERSE);

        driveMode = "Normal";
        adjusterStatus = "Raised";

        Toggle.resetStates();
    }

    public void loop(){

        slowness = Toggle.numChange(gamepad1.dpad_right, gamepad1.dpad_left, 5, 0);

        if (Toggle.toggle(gamepad1.b, 1)){
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
            driveMode = "Reverse";
        }else {
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
            driveMode = "Forward";
        }

        if (Toggle.toggle(gamepad1.x || gamepad2.x, 2)){
            adjusterL.setPosition(0.35);
            adjusterR.setPosition(0.35);
            adjusterStatus = "Lowered";
        }else {
            adjusterL.setPosition(0.95);
            adjusterR.setPosition(0.95);
            adjusterStatus = "Raised";
        }

        if (gamepad1.right_bumper){
            leftSweeper.setPower(1);
            rightSweeper.setPower(1);
            armWheelL.setPower(0.5);
            //armWheelR.setPower(0.5);
        }else if (gamepad1.right_trigger > 0){
            leftSweeper.setPower(-1);
            rightSweeper.setPower(-1);
            armWheelL.setPower(-0.5);
            //armWheelR.setPower(-0.5);
        }else{
            leftSweeper.setPower(0);
            rightSweeper.setPower(0);
            armWheelL.setPower(0);
            //armWheelR.setPower(0);
        }

        leftRotate.setPower(gamepad1.right_stick_y/2);
        rightRotate.setPower(gamepad1.right_stick_y/2);

        liftPos = Toggle.numChange(gamepad1.left_trigger > 0.5, gamepad1.left_bumper, 4, 1);

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
        telemetry.addData("Lift position", liftPos);
        telemetry.addData("Adjuster position", adjusterStatus);
    }
}

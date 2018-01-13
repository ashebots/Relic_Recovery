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

    DcMotor leftSweeper;
    DcMotor rightSweeper;

    CRServo leftRotate;
    CRServo rightRotate;

    DcMotor lift;

    Servo adjusterR;
    Servo adjusterL;
    //CRServo armWheelR;
    CRServo armWheelL;

    String speedStatus;
    String adjusterStatus;
    String driver;

    public void init(){


        leftWheel = hardwareMap.dcMotor.get("Left wheel");
        rightWheel = hardwareMap.dcMotor.get("Right wheel");

        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis(leftWheel, rightWheel);

        slowness = 1;

        leftSweeper = hardwareMap.dcMotor.get("Left sweeper");
        rightSweeper = hardwareMap.dcMotor.get("Right sweeper");
        leftSweeper.setDirection(DcMotorSimple.Direction.REVERSE);


        leftRotate = hardwareMap.crservo.get("Left rotator");
        rightRotate = hardwareMap.crservo.get("Right rotator");

        rightRotate.setDirection(CRServo.Direction.REVERSE);

        lift = hardwareMap.dcMotor.get("Lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        adjusterR = hardwareMap.servo.get("Right fly rotator");
        adjusterR.setPosition(0.95);
        adjusterL = hardwareMap.servo.get("Left fly rotator");
        adjusterL.setDirection(Servo.Direction.REVERSE);
        adjusterL.setPosition(0.95);
        //armWheelR = hardwareMap.crservo.get("Right fly wheel");
        armWheelL = hardwareMap.crservo.get("Left fly wheel");
        armWheelL.setDirection(DcMotorSimple.Direction.REVERSE);

        speedStatus = "Normal";
        adjusterStatus = "Raised";


    }

    public void loop(){

        //if(!Toggle.toggle(gamepad1.y || gamepad2.y,1)) {
            //driver =  "GamePad 1";

            if (Toggle.toggle(gamepad1.a, 0)){
                slowness = 2;
                speedStatus = "Slow";
            } else {
                slowness = 1;
                speedStatus = "Normal";
            }

            if (Toggle.toggle(gamepad1.b, 2)){
                chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
                speedStatus = speedStatus + " & Reverse";
            }else {
                chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
                speedStatus = speedStatus + " & Forward";
            }

        if (Toggle.toggle(gamepad1.x || gamepad2.x, 3)){
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

        if (gamepad1.left_bumper){
            lift.setPower(1);
        }else if (gamepad1.left_trigger > 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);
        }

        telemetry.addData("Adjuster position", adjusterStatus);
        telemetry.addData("Driver", driver);
        telemetry.addData("Lift position", lift.getCurrentPosition());
    }
}

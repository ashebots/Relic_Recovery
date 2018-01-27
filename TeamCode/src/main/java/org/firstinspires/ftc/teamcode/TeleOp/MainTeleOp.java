package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
public class NikoTeleOp extends OpMode {
=======
public class MainTeleOp extends OpMode{
>>>>>>> be38e2d03bb1d707d6eca9b917bae2f7776211c1:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

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

    String driveMode;
    String adjusterStatus;
<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
    String driver;

    public enum lift {TOP, MID, LOW}

    lift liftPos = LOW;
=======
>>>>>>> be38e2d03bb1d707d6eca9b917bae2f7776211c1:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

    public void init() {


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

        driveMode = "Normal";
        adjusterStatus = "Raised";

    }

    public void loop() {

<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
        if (Toggle.toggle(gamepad1.back || gamepad2.back, 1)) {
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
            if (!Toggle.toggle(gamepad1.y || gamepad2.y, 1)) {
                driver = "GamePad 1";

                if (Toggle.toggle(gamepad1.a, 0)) {
                    slowness = 2;
                    speedStatus = "Slow";
                } else {
                    slowness = 1;
                    speedStatus = "Normal";
                }
            } else {
                chassis.NormalDrive(gamepad2.left_stick_x / slowness, -gamepad2.left_stick_y / slowness);
                driver = "Gampad 2";

                if (Toggle.toggle(gamepad2.a, 0)) {
                    slowness = 2;
                    speedStatus = "Slow";
                } else {
                    slowness = 1;
                    speedStatus = "Normal";
                }
            }

            if (Toggle.toggle(gamepad1.left_stick_button || gamepad2.left_stick_button, 3)) {
                adjusterL.setPosition(0.4);
                adjusterR.setPosition(0.4);
                if (Toggle.toggle(gamepad1.x || gamepad2.x, 3)) {
                    adjusterL.setPosition(0.35);
                    adjusterR.setPosition(0.35);
                    adjusterStatus = "Lowered";
                } else {
                    adjusterL.setPosition(0.95);
                    adjusterR.setPosition(0.95);
                    adjusterStatus = "Raised";
                }

                if (gamepad1.right_bumper) {
                    leftSweeper.setPower(1);
                    rightSweeper.setPower(1);
                    armWheelL.setPower(1);
                    armWheelR.setPower(1);
                } else if (gamepad1.right_trigger > 0) {
                    leftSweeper.setPower(-1);
                    rightSweeper.setPower(-1);
                    armWheelL.setPower(-1);
                    armWheelR.setPower(-1);
                } else {
                    leftSweeper.setPower(0);
                    rightSweeper.setPower(0);
                    armWheelL.setPower(0);
                    armWheelR.setPower(0);
                }

                leftRotate.setPower(gamepad1.right_stick_y / 2);
                rightRotate.setPower(gamepad1.right_stick_y / 2);

                if (gamepad1.left_bumper) {
                    lift.setPower(1);
                } else if (gamepad1.left_trigger > 0) {
                    lift.setPower(-1);
                } else {
                    lift.setPower(0);
                }
                if (lift.getCurrentPosition() < 0) {

                    lift.setPower(.5);

                }

                if (gamepad2.right_bumper) {
                    tilt.setPower(.2);
                } else if (gamepad2.left_bumper) {
                    tilt.setPower(-.2);
                } else {
                    tilt.setPower(0);
                }

                if (gamepad2.right_trigger > 0) {
                    arm.setPower(gamepad2.right_trigger);
                } else if (gamepad2.left_trigger > 0) {
                    arm.setPower(-gamepad2.left_trigger);
                } else {
                    arm.setPower(0);
                }

                if (gamepad1.dpad_right || gamepad2.dpad_right) {
                    liftPos = LOW;
                } else if (gamepad1.dpad_up || gamepad2.dpad_up) {
                    liftPos = MID;
                } else if (gamepad1.dpad_left || gamepad2.dpad_left) {
                    liftPos = TOP;
                }

                //liftMove(liftPos);

                wrist.setPosition((gamepad2.right_stick_y / 2) + .5);
                grab.setPosition((gamepad2.right_stick_x / 2) + .5);

                telemetry.addData("Adjuster position", adjusterStatus);
                telemetry.addData("Motor Speed", leftWheel.getCurrentPosition() + "," + rightWheel.getCurrentPosition());
                telemetry.addData("Lift Position", liftPos);

                telemetry.addData("Driver", driver);
                telemetry.addData("Lift position", lift.getCurrentPosition());
                telemetry.addData("Arm tilt position", tilt.getCurrentPosition());
            }
=======
        if (Toggle.toggle(gamepad1.a, 0)){
            slowness = 2;
            driveMode = "Slow";
        } else {
            slowness = 1;
            driveMode = "Normal";
        }

        if (Toggle.toggle(gamepad1.b, 1)){
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, gamepad1.left_stick_y / slowness);
            driveMode = driveMode + " & Reverse";
        }else {
            chassis.NormalDrive(gamepad1.left_stick_x / slowness, -gamepad1.left_stick_y / slowness);
            driveMode = driveMode + " & Forward";
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
>>>>>>> be38e2d03bb1d707d6eca9b917bae2f7776211c1:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

   /* private void liftMove(lift pos){
        switch (pos){
            case TOP:
                if(gamepad2.right_trigger > 0){

                    lift.setPower(1);

                }
                else{

                    lift.setPower(0);

                }
                if (lift.getCurrentPosition() < 9400){
                    lift.setPower(.8);
                }else{
                    lift.setPower(-.2);
                }
                break;
            case MID:
                if (lift.getCurrentPosition() < 100){
                    lift.setPower(.2);
                }else{
                    lift.setPower(-.2);
                }
                break;
            default:
                if (lift.getCurrentPosition() < 0){
                    lift.setPower(.2);
                }else{
                    lift.setPower(-.2);
                }
                break;
        }*/
        }
<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
=======

        telemetry.addData("Drive mode", driveMode);
        telemetry.addData("Adjuster position", adjusterStatus);
>>>>>>> be38e2d03bb1d707d6eca9b917bae2f7776211c1:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java
    }
}
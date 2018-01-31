/*package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

@TeleOp
//TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
public class NikoTeleOp extends OpMode {
public class MainTeleOp extends OpMode{
//TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java
//I hate myself
    private DcMotor leftWheel;
    private DcMotor rightWheel;

    private Chassis chassis;

    private int slowness;
    private String driveMode;

    private DcMotor leftIntake;
    private DcMotor rightIntake;
//im just PR
    private Servo leftTray;
    private Servo rightTray;

<<<<<<< HEAD
=======
    private String trayStatus;
    Servo leftRotate;
    Servo rightRotate;
    Servo leftTray;
    Servo rightTray;

    String trayStatus;

    String driveMode;
    String adjusterStatus;
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
    String driver;

    public enum lift {TOP, MID, LOW}

    lift liftPos = LOW;
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

    public void init() {
>>>>>>> 3bb57106bfb38b2d1d7ab1e517e35acad3998459
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

<<<<<<< HEAD
=======
        leftRotate = hardwareMap.servo.get("Left rotator");
        rightRotate = hardwareMap.servo.get("Right rotator");

        rightRotate.setDirection(Servo.Direction.REVERSE);
        trayStatus = "Dump";

>>>>>>> 3bb57106bfb38b2d1d7ab1e517e35acad3998459
        lift = hardwareMap.dcMotor.get("Lift");

        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setDirection(DcMotorSimple.Direction.REVERSE);

        Toggle.resetStates();
    }

    public void loop() {
        TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java
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
        if (Toggle.toggle(gamepad1.a, 0)){
            slowness = 2;
            driveMode = "Slow";
        } else {
            slowness = 1;
            driveMode = "Normal";
        }
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
//this is line 236
<<<<<<< HEAD
        }
=======
            intakeWheelLeft.setPower(0);
            intakeWheelRight.setPower(0);

        }

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
        if (!gamepad1.right_bumper && Toggle.toggle(gamepad1.a, 2)){

            leftTray.setPosition(0.4);
            rightTray.setPosition(0.4);

            trayStatus = "Lift";
>>>>>>> 3bb57106bfb38b2d1d7ab1e517e35acad3998459

        leftTray.setPosition((gamepad1.right_stick_y+1)/2);
        rightTray.setPosition((gamepad1.right_stick_y+1)/2);

        leftRotate.setPower(gamepad1.right_stick_y/2);
        rightRotate.setPower(gamepad1.right_stick_y/2);
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

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
        /*liftPos = Toggle.numChange(gamepad1.left_trigger > 0.25, gamepad1.left_bumper, 4, 2);

        lift.setTargetPosition(1105*(liftPos-1));
        lift.setPower(1);

<<<<<<< HEAD
        telemetry.addData("Speed", 100/slowness+"%");
=======
        if (gamepad1.left_bumper){
            lift.setPower(1);
        }else if (gamepad1.left_trigger > 0){
            lift.setPower(-1);
        }else{
            lift.setPower(0);
        }
//TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/NikoTeleOp.java

        telemetry.addData("Speed", (int)(100/slowness)+"%");
>>>>>>> 3bb57106bfb38b2d1d7ab1e517e35acad3998459
        telemetry.addData("Drive mode", driveMode);
        telemetry.addData("Adjuster position", adjusterStatus);
//TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOp/MainTeleOp.java

        telemetry.addData("Lift position", liftPos);

    }
}*/
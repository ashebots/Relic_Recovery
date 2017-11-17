package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

<<<<<<< HEAD
/**
 * Created by secre on 10/17/2017.
 */

=======
>>>>>>> 1dcfd3ceada450e4386302919a240d8b09a93782
@TeleOp
public class Teleop extends OpMode {

    DcMotor left;
    DcMotor right;
    DcMotor pickUpL;
    DcMotor pickUpR;
    Servo placeR;
    Servo placeL;
    Servo grab;
    DcMotor lift;
    boolean pos;

    Chassis chassis;
    public void init(){
        left = hardwareMap.dcMotor.get("Left wheel");
        right = hardwareMap.dcMotor.get("Right wheel");
        pickUpL = hardwareMap.dcMotor.get("Right sweeper");
        pickUpR = hardwareMap.dcMotor.get("Left sweeper");
        placeR = hardwareMap.servo.get("Right rotator");
        placeL = hardwareMap.servo.get("Left rotator");
        placeR.setDirection(Servo.Direction.REVERSE);
        grab = hardwareMap.servo.get("Adjuster");
        lift = hardwareMap.dcMotor.get("Lift");
        chassis = new Chassis(left, right);
    }

    public void loop(){
        chassis.NormalDrive(gamepad1.left_stick_y, -gamepad1.left_stick_x);

        if(gamepad1.a){
            pickUpL.setPower(0.5);
            pickUpR.setPower(0.5);
        }else if (gamepad1.b){
            pickUpL.setPower(-0.5);
            pickUpR.setPower(-0.5);
        }else{
            pickUpL.setPower(0);
            pickUpR.setPower(0);
        }

        if(gamepad1.x){
            placeL.setPosition(0.51);
            placeR.setPosition(0.51);
        }else {
            placeL.setPosition(0.15);
            placeR.setPosition(0.15);
        }

        if(gamepad1.y){
            pos = !pos;
        }

        if(pos){
            grab.setPosition(0.25);
        }else{
            grab.setPosition(0.75);
        }

        if (gamepad1.right_bumper){
            lift.setPower(.5);
        }else if(gamepad1.right_trigger <= 0){
            lift.setPower(-.5);
        }else{
            lift.setPower(0);
        }
    }
}

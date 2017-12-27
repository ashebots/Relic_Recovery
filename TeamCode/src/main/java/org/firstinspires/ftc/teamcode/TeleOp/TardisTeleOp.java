package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 11/4/2017.
 */
@TeleOp
public class TardisTeleOp extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    Chassis chassis;

    TouchSensor sensorA;
    TouchSensor sensorB;

    DcMotor Hand;

    public void init(){

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right");

        chassis = new Chassis(leftMotor, rightMotor);

        sensorA = hardwareMap.touchSensor.get("Sensor A");
        sensorB = hardwareMap.touchSensor.get("Sensor B");

        Hand = hardwareMap.dcMotor.get("Hand");

    }

    public void loop(){

        chassis.NormalDrive(gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.y && !sensorA.isPressed()){
            Hand.setPower(1);
        }else if (gamepad1.a && !sensorB.isPressed()){
            Hand.setPower(-1);
        }else{
            Hand.setPower(0);
        }
    }

}
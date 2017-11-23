package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by famil on 11/12/2017.
 */
@TeleOp
public class TardisTeleOp extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor handMotor;

    TouchSensor sensorA;
    TouchSensor sensorB;

    Chassis chassis;

    public void init (){

        leftMotor = hardwareMap.dcMotor.get("Left");
        rightMotor = hardwareMap.dcMotor.get("Right");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        chassis = new Chassis (leftMotor, rightMotor);

        handMotor = hardwareMap.dcMotor.get("Hand");

        sensorA = hardwareMap.touchSensor.get("Sensor A");
        sensorB = hardwareMap.touchSensor.get("Sensor B");

    }

    public void loop (){

        chassis.NormalDrive(0.5*gamepad1.left_stick_x, -0.5*gamepad1.left_stick_y);

        if (gamepad1.y && !sensorA.isPressed()){
            handMotor.setPower(1);

        }else if (gamepad1.a && !sensorB.isPressed()){
            handMotor.setPower(-1);

        }else{
            handMotor.setPower(0);

        }
    }
}

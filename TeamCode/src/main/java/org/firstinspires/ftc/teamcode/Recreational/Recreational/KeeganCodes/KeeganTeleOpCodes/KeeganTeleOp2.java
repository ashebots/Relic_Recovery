package org.firstinspires.ftc.teamcode.Recreational.Recreational.KeeganCodes.KeeganTeleOpCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

//@TeleOp
public class KeeganTeleOp2 extends OpMode {

        DcMotor leftMotor;
        DcMotor rightMotor;
        Chassis Drive;
        public void init() {

            leftMotor = hardwareMap.dcMotor.get("Left");

            rightMotor = hardwareMap.dcMotor.get("Right");

            Drive = new Chassis(leftMotor, rightMotor);

        }

        public void loop(){

            Drive.NormalDrive(-gamepad1.left_stick_x, -gamepad1.left_stick_y);

        }


    }
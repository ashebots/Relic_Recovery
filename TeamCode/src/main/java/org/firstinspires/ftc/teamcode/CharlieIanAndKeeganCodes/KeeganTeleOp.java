package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 9/26/2017.
 */
@TeleOp
public class KeeganTeleOp extends OpMode {

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


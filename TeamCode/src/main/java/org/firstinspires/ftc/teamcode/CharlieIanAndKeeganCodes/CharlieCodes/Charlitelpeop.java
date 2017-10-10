package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.CharlieCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/10/2017.
 */
@TeleOp
public class Charlitelpeop extends OpMode {
    DcMotor leftmotor;
    DcMotor rightmotor;
    Chassis Drive;

    public void init() {
        leftmotor = hardwareMap.dcMotor.get("left");
        rightmotor = hardwareMap.dcMotor.get("Right");
        Drive = new Chassis(leftmotor, rightmotor);
    }


    public void loop() {

        Drive.NormalDrive(-gamepad1.left_stick_x, -gamepad1.left_stick_y);


    }
}

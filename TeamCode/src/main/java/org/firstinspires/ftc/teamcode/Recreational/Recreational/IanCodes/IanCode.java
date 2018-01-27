package org.firstinspires.ftc.teamcode.Recreational.Recreational.IanCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Lenovo on 9/28/2017.
 */
//@Autonomous
public class IanCode extends LinearOpMode {

    DcMotor RightMotor;
    DcMotor LeftMotor;


    public void runOpMode() {

        LeftMotor = hardwareMap.dcMotor.get("Left");
        RightMotor = hardwareMap.dcMotor.get("Right");
        RightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        RightMotor.setPower(1);
        LeftMotor.setPower(1);

        sleep(3000);

        RightMotor.setPower(1);
        LeftMotor.setPower(-1);

        sleep(1000);


    }
}

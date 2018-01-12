package org.firstinspires.ftc.teamcode.Recreational.Recreational.IanCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Lenovo on 9/30/2017.
 */
@Autonomous
public class Ian4DriveBase extends LinearOpMode {

    DcMotor RightBack;
    DcMotor RightForward;
    DcMotor LeftBack;
    DcMotor LeftForward;

    public void runOpMode(){

        RightBack = hardwareMap.dcMotor.get("RightBack");
        RightForward = hardwareMap.dcMotor.get("RightForward");
        LeftBack = hardwareMap.dcMotor.get("LeftBack");
        LeftForward = hardwareMap.dcMotor.get("LeftForward");
        RightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        RightForward.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        RightBack.setPower(1);
        LeftBack.setPower(1);
        RightForward.setPower(1);
        LeftForward.setPower(1);

        sleep(10000);





    }
}

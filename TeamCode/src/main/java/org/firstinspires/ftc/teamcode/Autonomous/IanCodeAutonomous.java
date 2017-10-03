package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by secre on 9/28/2017.
 */

public class IanCodeAutonomous extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;

    public void runOpMode(){

        LeftMotor = hardwareMap.dcMotor.get("Left");
        RightMotor = hardwareMap.dcMotor.get("Right");
        RightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        RightMotor.setPower(1);
        LeftMotor.setPower(1);

        sleep(3000);

        RightMotor.setPower(0);
        LeftMotor.setPower(0);



    }


}

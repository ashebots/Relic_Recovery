package org.firstinspires.ftc.teamcode.Recreational.Recreational.IanCodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

/**
 * Created by Lenovo on 9/30/2017.
 */

public class IanVueMark extends LinearOpMode {

    DcMotor RightMotor;
    DcMotor LeftMotor;
    VueMarkID ViewMark;
    RelicRecoveryVuMark VuMark;

    public void runOpMode(){

        RightMotor = hardwareMap.dcMotor.get("Right");
        LeftMotor = hardwareMap.dcMotor.get("Left");

    }
}

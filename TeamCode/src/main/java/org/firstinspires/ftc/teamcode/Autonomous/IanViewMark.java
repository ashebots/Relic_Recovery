package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.Autonomous.Move.Transform;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

/**
 * Created by secre on 9/28/2017.
 */

public class IanViewMark extends LinearOpMode {

    RelicRecoveryVuMark VueMark;
    VueMarkID ViewMark;
    DcMotor RightMotor;
    DcMotor LeftMotor;
    Transform transform;

    public void runOpMode(){

        ViewMark = new VueMarkID(hardwareMap);
        LeftMotor = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        RightMotor = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        transform = new Transform(telemetry, hardwareMap, 4, 0);



        waitForStart();

        VueMark = ViewMark.vueName();

        transform.backward(1.5, 1);
        transform.turn(90, 1);
        transform.forward(2, 1);

        switch(VueMark){

            case RIGHT:


        }


        }
    }

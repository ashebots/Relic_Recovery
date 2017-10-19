package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.Transform;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

/**
 * Created by Lenovo on 9/30/2017.
 */

@Autonomous
public class KeeganVueMark extends LinearOpMode {

    DcMotor LeftMotor;
    DcMotor RightMotor;
    VueMarkID vueMark;
    RelicRecoveryVuMark vuMark;
    Transform transform;

    public void runOpMode () {
        LeftMotor = hardwareMap.dcMotor.get("Left");
        LeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RightMotor = hardwareMap.dcMotor.get("Right");
        vueMark = new VueMarkID(hardwareMap);
        transform = new Transform(hardwareMap, 4, 0);
        waitForStart();

        vuMark = vueMark.vueName();

        switch (vuMark){

            case LEFT:

                transform.turn(-90, 1);

                break;

            case RIGHT:

                transform.turn(90, 1);

                break;

            default:


                transform.forward(.5, 1);

                break;
        }
    }

}

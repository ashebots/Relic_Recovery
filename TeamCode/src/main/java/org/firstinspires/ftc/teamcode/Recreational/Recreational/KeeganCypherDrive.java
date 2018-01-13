package org.firstinspires.ftc.teamcode.Recreational.Recreational;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.Transform;

/**
 * Created by Lenovo on 10/3/2017.
 */
//@Autonomous
public class KeeganCypherDrive {

    DcMotor boxMotor;
    Transform transform;
    public KeeganCypherDrive (HardwareMap hardwareMap){

        boxMotor = hardwareMap.dcMotor.get("BoxMotor");
        transform = new Transform(hardwareMap, 4, 0);

    }

    public void go (RelicRecoveryVuMark vuMark) {

        switch (vuMark) {

            case RIGHT:

                transform.turn(60, 0.5);
                transform.forward(2, 0.5);
                transform.turn(-60, 0.5);

                break;

            case LEFT:

                transform.turn(-60, 0.5);
                transform.forward(2, 0.5);
                transform.turn(60, 0.5);

                break;

            default:

                transform.forward(2, 0.5);

                boxMotor.setPower(1);

                try {
                    wait(3500);
                } catch (InterruptedException e) {}

                break;

        }

    }
}
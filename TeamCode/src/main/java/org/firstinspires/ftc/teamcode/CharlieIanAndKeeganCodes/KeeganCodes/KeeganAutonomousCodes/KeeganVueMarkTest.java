package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganAutonomousCodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.MaincompetitiveTeleOp.VuMarkCode;

/**
 * Created by Lenovo on 11/14/2017.
 */
@Autonomous
public class KeeganVueMarkTest extends LinearOpMode {
    VuMarkCode drive;
    RelicRecoveryVuMark mark;
    public void runOpMode() {
        mark = RelicRecoveryVuMark.RIGHT;
        waitForStart();
        drive = new VuMarkCode(hardwareMap, mark);
    }
}
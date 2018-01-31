package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Test
@TeleOp
public class VueTest extends OpMode{

    private VueMarkID mark;

    public void init() {
        mark = new VueMarkID(hardwareMap);
    }

    public void loop() {
        telemetry.addData("Mark", mark.vueName());
    }
}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@TeleOp
public class VueTest extends OpMode{

    VueMarkID mark;

    @Override
    public void init() {
        mark = new VueMarkID(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Mark", mark.vueName());
    }
}

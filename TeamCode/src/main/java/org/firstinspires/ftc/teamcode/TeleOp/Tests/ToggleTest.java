package org.firstinspires.ftc.teamcode.TeleOp.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.Toggle;

/**
 * Created by famil on 11/18/2017.
 */
@TeleOp
public class ToggleTest extends OpMode {

    public void init(){
        Toggle.resetStates();
    }

    public void loop(){

        telemetry.addData("Speed", 100d/Toggle.numChange(gamepad1.dpad_right, gamepad1.dpad_left, 5, 0));
        telemetry.addData("Reverse", Toggle.toggle(gamepad1.b, 0));
        telemetry.addData("Lift position", Toggle.numChange(gamepad1.left_trigger > 0.5, gamepad1.left_bumper, 4, 1));
        telemetry.addData("Adjuster lowered", Toggle.toggle(gamepad1.x, 4));

    }
}




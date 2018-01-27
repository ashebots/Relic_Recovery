package org.firstinspires.ftc.teamcode.TeleOp.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOp.Toggle;

/**
 * Created by famil on 11/18/2017.
 */
//@TeleOp
public class ToggleTest extends OpMode {

    public void init(){

    }

    public void loop(){

        if (Toggle.toggle(gamepad1.a, 0)){
            telemetry.addData("A ", "On");
        }else {
            telemetry.addData("A", "Off");
        }

        if (Toggle.toggle(gamepad1.b, 1)){
            telemetry.addData("B", "On");
        }else {
            telemetry.addData("B", "Off");
        }

    }
}




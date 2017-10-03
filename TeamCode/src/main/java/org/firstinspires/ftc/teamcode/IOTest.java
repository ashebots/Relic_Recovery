package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.io.File;

/**
 * Created by rostifar on 9/30/17.
 */

public class IOTest extends LoggerOpMode {
    private Context appContext;

    public void runOpMode() {
        appContext = hardwareMap.appContext;
        waitForStart();
    }
}

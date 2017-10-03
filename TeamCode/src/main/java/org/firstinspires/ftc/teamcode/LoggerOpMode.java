package org.firstinspires.ftc.teamcode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by rostifar on 9/30/17.
 */

public abstract class LoggerOpMode extends LinearOpMode implements FileIO.FileCallback {

    @Override
    public void onFileCreation(boolean success) {
    }

    @Override
    public void onWriteCompletion(boolean success) {

    }
}

package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by rostifar on 1/25/18.
 */

public abstract class AutonomousOpMode extends LinearOpMode {
    protected boolean running = false;
    protected boolean testing = true;

    protected long startTime;
    protected long currentime;
    protected long objectiveTime;

    public abstract void update();

    public abstract void disable();

    public boolean opModeRunning() {
        return running;
    }
}

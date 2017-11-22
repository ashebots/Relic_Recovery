package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ModularConstants;

/**
 * Created by jezebelquit on 9/21/17.
 */

@Autonomous
public class ModularTest extends LinearOpMode{

    ImuChassis imuChassis;
    HardwareMap map;

    float[] b = {1, 1};
<<<<<<< HEAD

    float[][] test = {ModularConstants.BALANCE_STONE_A, ModularConstants.Mid_COLUMN_A};

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right wheel");

        leftMotor = hardwareMap.dcMotor.get("Left wheel");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        lTray = hardwareMap.servo.get("Left rotator");
        rTray = hardwareMap.servo.get("Right rotator");
        rTray.setDirection(Servo.Direction.REVERSE);
=======
    float[][] test = {ModularConstants.BALANCE_STONE_A, ModularConstants.MID_COLUMN_A};
>>>>>>> 5ed81744a9706e59d6153d6a81f8955405eb2488


    public void runOpMode(){
        imuChassis = new ImuChassis(map, 2959.0);
        imuChassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);
        float[][] test = {ModularConstants.BALANCE_STONE_A,ModularConstants.MID_COLUMN_A};

        waitForStart();

        imuChassis.driveToCoords(test, 0.8, 0.3, false);
    }
}

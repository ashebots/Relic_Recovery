package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

@Autonomous
public class ModularTest extends LinearOpMode{

    ImuChassis imuChassis;

    DcMotor leftMotor;
    DcMotor rightMotor;
    BNO055IMU imu;
    Servo lTray;
    Servo rTray;
    Servo gemArm;
    VueMarkID mark;
    RelicRecoveryVuMark vuMark;
    float[] s = {0,0};
    float[] b = {1, 1};
    float[][] test = {ModularConstants.BALANCE_STONE_A, ModularConstants.CRYPTOBOX_A};

    public void runOpMode(){

        imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2959.0, this);

        waitForStart();

        imuChassis.test();
    }
}

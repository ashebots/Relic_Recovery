package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.AnnualModule;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ModularConstants;
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
    AnnualModule annualModule;
    float[] s = {0,0};
    float[] b = {1, 1};
    float[][] test = {ModularConstants.BALANCE_STONE_A, ModularConstants.MID_COLUMN_A};

    public void runOpMode(){

        leftMotor = hardwareMap.dcMotor.get("Left wheel");

        rightMotor = hardwareMap.dcMotor.get("Right wheel");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        lTray = hardwareMap.servo.get("Left rotator");
        rTray = hardwareMap.servo.get("Right rotator");
        gemArm = hardwareMap.servo.get("gemArm");
        mark = new VueMarkID(hardwareMap);


        annualModule = new AnnualModule(lTray, rTray, gemArm, mark);
        imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2959.0, annualModule);

        imuChassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        imuChassis.turnToAngle(45, 0.3);

    }
}

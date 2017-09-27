package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;

/**
 * Created by jezebelquit on 9/21/17.
 */

@Autonomous (name = "Modular Autonomous Test")
public class ModularTest extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    BNO055IMU imu;

    ImuChassis imuChassis;

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right");

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2966);

        waitForStart();

        while (opModeIsActive()) {
            imuChassis.driveAtSpeed(0.5);
        }

    }
}

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

@Autonomous
public class ModularTest extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    BNO055IMU imu;

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right");

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        //imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2966);

        waitForStart();

        leftMotor.setPower(1);
        rightMotor.setPower(1);
        sleep(10000);
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        telemetry.addData("Left Encoder", leftMotor.getCurrentPosition());
        telemetry.addData("Right Encoder", rightMotor.getCurrentPosition());
        telemetry.update();

        sleep(10000);

    }
}

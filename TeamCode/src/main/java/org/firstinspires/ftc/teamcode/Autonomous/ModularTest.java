package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous.ModularConstants;

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

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightMotor = hardwareMap.dcMotor.get("Right");

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        imuChassis = new ImuChassis()

        waitForStart();

        while (opModeIsActive()) {
            leftMotor.setPower(1);
            rightMotor.setPower(1);
        }


    }
}

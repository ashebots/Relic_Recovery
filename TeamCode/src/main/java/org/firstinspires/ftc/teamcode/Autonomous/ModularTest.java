package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.motors.NeveRest60Gearmotor;
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

@Autonomous
public class ModularTest extends LinearOpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;

    BNO055IMU imu;

    ImuChassis imuChassis;

    float[] b = {1, 1};

    public void runOpMode(){

        rightMotor = hardwareMap.dcMotor.get("Right");

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "Imu");

        imuChassis = new ImuChassis(leftMotor, rightMotor, imu, 2959);
        imuChassis.driveSetup(ModularConstants.NEVERREST_40, 1.5f, 4);

        waitForStart();

        imuChassis.driveXFeet(1.5, -0.1);

    }
}

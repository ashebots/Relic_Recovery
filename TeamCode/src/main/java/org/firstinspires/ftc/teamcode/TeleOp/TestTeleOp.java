package org.firstinspires.ftc.teamcode.TeleOp;

<<<<<<< HEAD
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/*
 * Created by jezebelquit on 9/30/17.
 */
@TeleOp
public class TestTeleOp extends OpMode{

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor rearLeftMotor;
    DcMotor rearRightMotor;

    Chassis chassis;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeftMotor = hardwareMap.dcMotor.get("Left Rear");
        rearLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor = hardwareMap.dcMotor.get("Right");
        rearRightMotor = hardwareMap.dcMotor.get("Right Rear");

        chassis = new Chassis(leftMotor, rightMotor, rearLeftMotor, rearRightMotor);

    }

    public void loop() {
        chassis.HoloMecaDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);
=======
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.Move.ConfigStrings;
import org.firstinspires.ftc.teamcode.Autonomous.Move.GoodIMU;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by secre on 9/26/2017.
 */

public class TestTeleOp extends OpMode{

    DcMotor left;
    DcMotor right;
    BNO055IMU IMU;

    GoodIMU imu;
    Chassis drive;

    @Override
    public void init() {
        left = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        right = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        IMU = hardwareMap.get(BNO055IMU.class, ConfigStrings.IMU);

        imu = new GoodIMU(IMU, org.firstinspires.ftc.teamcode.Autonomous.Move.IMU.Unit.DEGREE);
        drive = new Chassis(left, right);
    }

    @Override
    public void loop() {
        drive.NormalDrive(gamepad1.left_stick_x, gamepad1.left_stick_y);
        if(gamepad1.a){
            while (imu.pitch() < 3){
                left.setPower(0.5);
                right.setPower(0.5);
                telemetry.addData("Angle", imu.pitch());
            }
            while (imu.pitch() > 3){
                left.setPower(0.2);
                right.setPower(0.2);
                telemetry.addData("Angle", imu.pitch());
            }
            left.setPower(0);
            right.setPower(0);
            telemetry.addData("Angle", imu.pitch());
        }
>>>>>>> origin/master
    }
}

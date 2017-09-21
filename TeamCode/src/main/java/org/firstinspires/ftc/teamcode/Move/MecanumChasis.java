package org.firstinspires.ftc.teamcode.Move;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by secre on 9/13/2017.
 */

public class MecanumChasis {

    DcMotor leftF;
    DcMotor leftB;
    DcMotor rightF;
    DcMotor rightB;
    BNO055IMU IMU;
    GoodIMU imu;

    public void MecanumChasis(HardwareMap hardwareMap){
        leftF = hardwareMap.dcMotor.get(ConfigStrings.LeftMotor);
        leftB = hardwareMap.dcMotor.get(ConfigStrings.LeftBackMotor);
        rightF = hardwareMap.dcMotor.get(ConfigStrings.RightMotor);
        rightB = hardwareMap.dcMotor.get(ConfigStrings.RightBackMotor);
        IMU = hardwareMap.get(BNO055IMU.class, ConfigStrings.IMU);
        imu = new GoodIMU(IMU, org.firstinspires.ftc.teamcode.Move.IMU.Unit.RADIAN);
    }

    public void drive(double driveAngle, double targetAngle, double speed){
        double turnSpeed;

        if (targetAngle > imu.yaw()){
            turnSpeed = 0.5;
        }else if (targetAngle < imu.yaw()){
            turnSpeed = -0.5;
        }else{
            turnSpeed = 0;
        }

        leftF.setPower(speed * Math.sin(driveAngle + (Math.PI/4) + turnSpeed));
        leftB.setPower(speed * Math.cos(driveAngle + (Math.PI/4) + turnSpeed));
        rightF.setPower(speed * Math.cos(driveAngle + (Math.PI/4) - turnSpeed));
        rightB.setPower(speed * Math.sin(driveAngle + (Math.PI/4) - turnSpeed));
    }
}

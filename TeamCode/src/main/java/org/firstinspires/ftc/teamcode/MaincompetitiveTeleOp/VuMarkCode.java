package org.firstinspires.ftc.teamcode.MaincompetitiveTeleOp;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;
import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 11/14/2017.
 */
@Autonomous
public class VuMarkCode {

    DcMotor leftMotor;
    DcMotor rightMotor;

    VueMarkID vueMark;
    Chassis chassis;
    BNO055IMU Imu;
    ImuChassis ImuChassis;
    public VuMarkCode (HardwareMap hardwareMap, RelicRecoveryVuMark VuMark)  {

        leftMotor = hardwareMap.dcMotor.get("Left");
        rightMotor = hardwareMap.dcMotor.get("Right");

        Imu = hardwareMap.get(BNO055IMU.class, "imu");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        vueMark = new VueMarkID(hardwareMap);

        chassis = new Chassis (leftMotor,rightMotor);




        switch (VuMark){

            case LEFT:

                ImuChassis.turnToAngle(-70, .7);



        } 




    }
}

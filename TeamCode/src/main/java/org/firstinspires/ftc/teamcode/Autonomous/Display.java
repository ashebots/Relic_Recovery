package org.firstinspires.ftc.teamcode.Autonomous;

<<<<<<< HEAD
public class Display {
=======
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

/**
 * Created by jezebelquit on 9/21/17.
 */
@Autonomous
public class Display extends OpMode {

    BNO055IMU imu;
    public void init(){
        imu = hardwareMap.get(BNO055IMU.class, "Imu");
    }
    public void loop(){
        telemetry.addData("Angle", imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle);

    }
>>>>>>> 8d77e597d8459ed5a44846f78f92cf98bb9556a0
}

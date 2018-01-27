package org.firstinspires.ftc.teamcode.Autonomous.Move;

import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class GoodIMU {

    private Unit unit;

    public enum Unit {
        RADIAN, DEGREE
    }

    private BNO055IMU   imu;

    public GoodIMU (BNO055IMU imu, Unit unit){
        this.imu    = imu;
        this.unit   = unit;

        BNO055IMU.Parameters param  = new BNO055IMU.Parameters();
        param.angleUnit             = BNO055IMU.AngleUnit.DEGREES;
        param.calibrationDataFile   = ConfigStrings.IMU_CALIBRATION_FILE;
        imu.initialize(param);
    }
    public Unit getUnit() {return unit;}

    public float yaw() {
        return unit == Unit.RADIAN
                ? -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).thirdAngle
                : -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle;
    }

    public float pitch() {
        return unit == Unit.RADIAN
                ? -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).firstAngle
                : -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle;
    }

    public float roll() {
        return unit == Unit.RADIAN
                ? -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.RADIANS).secondAngle
                : -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle;
    }

    //Clamped between 180 and -180
    public static float IMU2GlobalAngle(float imuAngle) {
        return imuAngle >= 0 ? imuAngle : imuAngle + 360;
    }
}

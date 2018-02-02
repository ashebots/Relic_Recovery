package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Autonomous.Move.GoodIMU;
import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;
import org.firstinspires.ftc.teamcode.Autonomous.Types.Coordinate;
import java.util.Queue;

public class ImuChassis {

    private LinearOpMode opMode;

    //encodersPerFoot is required for calculating the encoder position to drive
    private int encodersPerFoot;
    private DriveSpec driveSpec;
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private BNO055IMU imu;
    private GoodIMU goodIMU;

    private Vector2 initialPosition;
    private Vector2 currentPosition;
    private Vector2 forward;

    public enum Direction {
        Left, Right
    }

    public class DriveSpec {
        public float encodersPerRotation;
        public float gearRatio;
        public float wheelDiameter;
    }

    public ImuChassis(DcMotor leftMotor, DcMotor rightMotor, BNO055IMU imu, LinearOpMode opMode) {

        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.imu = imu;
        this.opMode = opMode;
        this.goodIMU = new GoodIMU(imu, GoodIMU.Unit.DEGREE);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        initImu();
    }

    public ImuChassis(DcMotor leftMotor, DcMotor rightMotor, BNO055IMU imu, LinearOpMode opMode, DriveSpec driveSpec) {
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.imu = imu;
        this.opMode = opMode;
        this.driveSpec = driveSpec;

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        initImu();
    }

    private void initImu() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.mode = BNO055IMU.SensorMode.NDOF;
        imu.initialize(parameters);
    }

    public void driveAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(speed);
    }
    private void turnAtSpeed(double speed) {
        leftMotor.setPower(speed);
        rightMotor.setPower(-speed);
    }

    private  void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    //SmartImu converts angles to Imu angles, which also serves to loop the Imu angle when adding two angles
    private float smartImu (float input){
        while (input <= -180 && opMode.opModeIsActive()) {
            input += 360;
        }
        while (input > 180 && opMode.opModeIsActive()){
            input -= 360;
        }
        return input;
    }

    public void turnToAngle (float angleTo, double speed) {

        //speed = speed * maxSpeed / 4000;

        float currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;

        float angleDifference = Math.abs(currentAngle - angleTo);
        if (angleDifference > 360-angleDifference) angleDifference = 360-angleDifference;

        boolean turnToRight = false;
        if (smartImu(currentAngle + angleDifference) == angleTo) turnToRight = true;

        if (turnToRight) {

            while (currentAngle <= angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle < angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }

            stop();

        } else{

            while (currentAngle < angleTo && opMode.opModeIsActive()) {

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

            while (currentAngle > angleTo && opMode.opModeIsActive()){

                turnAtSpeed(-speed);
                currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            }
            stop();

        }
    }

    public void turnXDegrees (float angleTo, double speed){
        angleTo = smartImu(-imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle + angleTo);
        turnToAngle(angleTo, speed);
    }

    //driveSetup is needed to calculate the encoders per foot of the robot, using the encoders per rotation, the gear ratio, and the wheel diameter.
    public void driveSetup(float encodersPerRotation, float gearRatio, float wheelDiameter){
        encodersPerFoot = (int)((12 * encodersPerRotation) / (gearRatio * Math.PI * wheelDiameter));
    }

    public void driveSetup(DriveSpec spec) {
        encodersPerFoot = (int)((12 * spec.encodersPerRotation) / (spec.gearRatio * Math.PI * spec.wheelDiameter));
    }

    public void driveFromStart(double feet, double speed) {

        int leftGoal = (int)(feet * encodersPerFoot);

        if (leftMotor.getCurrentPosition() < leftGoal) {
            while (leftMotor.getCurrentPosition() < leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(speed);
            }
            while (leftMotor.getCurrentPosition() > leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(-speed/2);
            }
        } else {
            while (leftMotor.getCurrentPosition() > leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(-speed);
            }
            while (leftMotor.getCurrentPosition() < leftGoal && opMode.opModeIsActive()) {
                driveAtSpeed(speed/2);
            }
        }

        stop();
    }

    public void driveXFeet(double feet, double speed) {
        feet = feet + leftMotor.getCurrentPosition()/encodersPerFoot;
        driveFromStart(feet, speed);
    }

    public void navigateTo(Queue<Coordinate> coordinates) {
        for (Coordinate c : coordinates) {
            Vector2 target = c.position();
            float distance = target.norm() - currentPosition.norm();
            float angle = Vector2.Vec2Angle(Vector2.Normalize(currentPosition), Vector2.Normalize(target));

            angle = GoodIMU.RadiansToDegrees(angle);
            turnXDegrees(angle, c.speed());
            driveXFeet(distance, c.speed());
        }
    }
}
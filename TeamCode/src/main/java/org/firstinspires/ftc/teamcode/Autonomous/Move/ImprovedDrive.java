package org.firstinspires.ftc.teamcode.Autonomous.Move;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by secre on 1/9/2018.
 */

public class ImprovedDrive {
    DcMotor left;
    DcMotor right;

    BNO055IMU Imu;
    GoodIMU imu;

    double encodersPerFoot;

    enum mode {drive, turn, end, check}

    int step = 0;

    public ImprovedDrive(DcMotor Left, DcMotor Right, BNO055IMU IMU, double encodersPerRotation, double gearRatio, double wheelDiameter){
        left = Left;
        right = Right;

        Imu = IMU;
        imu = new GoodIMU(Imu, org.firstinspires.ftc.teamcode.Autonomous.Move.IMU.Unit.DEGREE);

        encodersPerFoot = (int)((12 * encodersPerRotation) / (gearRatio * Math.PI * wheelDiameter));
    }

    public void Drive (mode[] state, Double[][] settings){

        int startPosition = 0;
        int targetPosition = 0;
        boolean setup = true;
        switch(state[step]){

            case drive:
                if(setup == true){
                    startPosition = (left.getCurrentPosition() + right.getCurrentPosition()) /2;
                    targetPosition = (int) (settings[step][0] * encodersPerFoot);
                    setup = false;
                }else if(((left.getCurrentPosition() + right.getCurrentPosition()) / 2) - startPosition < targetPosition){
                    left.setPower(settings[step][1]);
                    right.setPower(settings[step][1]);
                }else if(((left.getCurrentPosition() + right.getCurrentPosition()) / 2) - startPosition > targetPosition){
                    left.setPower(-settings[step][1]);
                    right.setPower(-settings[step][1]);
                }else{
                    left.setPower(0);
                    right.setPower(0);
                    step++;
                    setup = true;
                }
                break;

            case turn:
                if(setup == true){
                    startPosition = imu.yaw();
                }else if(imu.yaw() - startPosition < settings[step][0]){
                    if (settings[step][0] > 0){
                        left.setPower(settings[step][1]);
                        right.setPower(-settings[step][1]);
                    }else{
                        left.setPower(-settings[step][1]);
                        right.setPower(settings[step][1]);
                    }
                }else{
                    left.setPower(0);
                    right.setPower(0);
                    step++;
                    setup = true;
                }

        }



    }
}

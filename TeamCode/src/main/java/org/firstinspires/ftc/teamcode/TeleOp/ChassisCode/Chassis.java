package org.firstinspires.ftc.teamcode.TeleOp.ChassisCode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class Chassis {
    DcMotor motorLeft;
    DcMotor motorRight;
    DcMotor motorLeftRear;
    DcMotor motorRightRear;
    DcMotor colL;
    DcMotor colR;
    DcMotor lift;
    Servo turningServo;
    enum directions{

        FORWARD, BACKWARD;}

    boolean priority;

    public Chassis(DcMotor motorLeft, DcMotor motorRight){
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;
    }

    public Chassis (DcMotor motorLeft, DcMotor motorRight, DcMotor motorLeftRear, DcMotor motorRightRear){
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;
        this.motorLeftRear = motorLeftRear;
        this.motorRightRear = motorRightRear;
    }

    public Chassis (DcMotor motorLeft,Servo turningServo){
        this.motorLeft = motorLeft;
        this.turningServo = turningServo;
    }

    public Chassis(DcMotor motorLeft, DcMotor motorRight, DcMotor colL, DcMotor colR, DcMotor lift){
        this.motorLeft = motorLeft;
        this.motorRight = motorRight;
        this.colL = colL;
        this.colR = colR;
        this.lift = lift;
        motorLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void NormalDrive(double xPos, double yPos){

        if (xPos != 0 || yPos != 0) {
            priority = true;
        }else{
            priority = false;
        }
        
        double[] motorSpeeds = Joystick.calculateNormal(xPos, yPos);
        motorLeft.setPower(motorSpeeds[0]);
        motorRight.setPower(motorSpeeds[1]);
        if (motorLeftRear != null){
            motorLeft.setPower(motorSpeeds[0]);
            motorRight.setPower(motorSpeeds[1]);
            motorLeftRear.setPower(motorSpeeds[0]);
            motorRightRear.setPower(motorSpeeds[1]);
        }
    }

    public void HoloMecaDrive (double xPos, double yPos){
        if (!priority) {
            double[] motorSpeeds = Joystick.calculateNormal(xPos, yPos);
            motorLeft.setPower(motorSpeeds[0]);
            motorRight.setPower(motorSpeeds[1]);
            motorLeftRear.setPower(motorSpeeds[1]);
            motorRightRear.setPower(motorSpeeds[0]);
        }
    }
    public void CarDrive (double xPos, double yPos){
        double[] motorSpeeds = Joystick.calculateCar(xPos, yPos);

        motorLeft.setPower(motorSpeeds[0]);
        turningServo.setPosition(motorSpeeds[1]);
    }


    public void glyphColecter(boolean pressedButton, double pow){
        if (pressedButton) {
            colL.setPower(pow);
            colR.setPower(pow);
        }else{
            colL.setPower(0);
            colR.setPower(0);
        }
    }

    public void lift(boolean pressedButton, double pow){
        if(pressedButton){
            lift.setPower(pow);
        }else {
            lift.setPower(0);
        }
    }
    public void glyphCollector(boolean buttonrightbumper) {


        if (buttonrightbumper) {

            colL.setPower(0.8);
            colR.setPower(0.8);

        }else {
            colL.setPower(0);
            colR.setPower(0);
        }

    }


}

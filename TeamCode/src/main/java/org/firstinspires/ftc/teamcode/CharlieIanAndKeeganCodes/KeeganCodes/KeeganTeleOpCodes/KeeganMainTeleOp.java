package org.firstinspires.ftc.teamcode.CharlieIanAndKeeganCodes.KeeganCodes.KeeganTeleOpCodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TeleOp.ChassisCode.Chassis;

/**
 * Created by Lenovo on 10/7/2017.
 */
@TeleOp
public class KeeganMainTeleOp extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor boxMotors;
    DcMotor boxExtenderMotor;
    DcMotor armMotors;
    DcMotor armExtenders;
    Chassis driveChassis;

    public void init() {

        leftMotor = hardwareMap.dcMotor.get("Left");
        rightMotor = hardwareMap.dcMotor.get("Right");
        boxMotors = hardwareMap.dcMotor.get("BoxMotors");
        armMotors = hardwareMap.dcMotor.get("ArmMotors");
        armExtenders = hardwareMap.dcMotor.get("ArmExtensionsMotor");
        boxExtenderMotor = hardwareMap.dcMotor.get("BoxExtensionMotor");

        driveChassis = new Chassis(leftMotor, rightMotor);
    }

    public void loop() {

        driveChassis.NormalDrive(-gamepad1.left_stick_x, -gamepad1.left_stick_y);

        if (gamepad1.y){boxMotors.setPower(0.8);}
        else if (gamepad1.a){boxMotors.setPower(-0.8);}
        else boxMotors.setPower(0);

        if (gamepad1.x){armMotors.setPower(0.8);}
        else if (gamepad2.b){armMotors.setPower(-0.8);}
        else armMotors.setPower(0);

        if (gamepad1.right_bumper){armExtenders.setPower(0.8);}
        else if (gamepad1.left_bumper){armExtenders.setPower(-0.8);}
        else armExtenders.setPower(0);

        if (gamepad1.dpad_up){armExtenders.setPower(0.8);}
        else if (gamepad1.dpad_down){armExtenders.setPower(-0.8);}
        else armExtenders.setPower(0);

        if (gamepad1.left_stick_button){boxExtenderMotor.setPower(0.4);}
        else if (gamepad1.right_stick_button){boxExtenderMotor.setPower(-0.4);}
        else boxExtenderMotor.setPower(0);

    }
}
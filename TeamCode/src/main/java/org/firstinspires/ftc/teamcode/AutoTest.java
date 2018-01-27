package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ImuChassis;
import org.firstinspires.ftc.teamcode.Autonomous.ModularAuto.ModularConstants;
import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;

import java.util.Queue;

/**
 * Created by rostifar on 1/11/18.
 */

public class AutoTest extends LinearOpMode {
    private ImuChassis chassis;
    private float initialAngle = 90;
    private float angle;

    private Vector2 initial = Vector2.Array2Vec(ModularConstants.BALANCE_STONE_A);
    private Vector2 forwardVec;


    @Override
    public void runOpMode() throws InterruptedException {
        angle = initialAngle;
        forwardVec = Vector2.Angle2Vec(angle);

        waitForStart();

        T_VECTOR_ANGLE_CALC();

    }

    public void T_VECTOR_ANGLE_CALC() {
        Vector2 a1 = new Vector2(12, 12);
        float theta1 = Vector2.Vec2Angle(a1, forwardVec);
        Vector2 f1 = Vector2.Angle2Vec(theta1);
        T_VECTOR_STRING_HELPER("TEST1", a1, theta1, f1);


        Vector2 a2 = new Vector2(10, 10);
        float theta2 = Vector2.Vec2Angle(a2, forwardVec);
        Vector2 f2 = Vector2.Angle2Vec(theta2);
        T_VECTOR_STRING_HELPER("TEST2", a2, theta2, f2);
        T_VECTOR_STRING_HELPER("TEST3", theta1 == theta2);
        T_VECTOR_STRING_HELPER("TEST4", f1.equals(f2));


        Vector2 a3 = new Vector2(6, 6);
        float theta3 = Vector2.Vec2Angle(a2, forwardVec);
        Vector2 f3 = Vector2.Angle2Vec(theta3);
        T_VECTOR_STRING_HELPER("TEST5", a3, theta3, f3);
        T_VECTOR_STRING_HELPER("TEST6", theta2 == theta3);
        T_VECTOR_STRING_HELPER("TEST7", f2.equals(f3));
    }

    public void T_ROTATE() {
    }

    public void T_VECTOR_STRING_HELPER(String id, Vector2 a, float angle, Vector2 forward) {
        telemetry.addData(id, a.toString());
        telemetry.addData(id, angle);
        telemetry.addData(id, forward);
        telemetry.update();
    }

    public void T_VECTOR_STRING_HELPER(String id, boolean a) {
        telemetry.addData(id, a);
        telemetry.update();
    }
}

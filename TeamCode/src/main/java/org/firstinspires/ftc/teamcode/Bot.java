package org.firstinspires.ftc.teamcode;

//Bot is a base class, containing sensors, dynamic capabilities, and events.

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.Vector2;

public class Bot {

    public enum Mode {
        AUTONOMOUS, TELE
    }

    public enum State {
        DISABLED, STATIC, DYNAMIC, ENABLED
    }

    //Transform is a data class that contains global and local characteristics of the Bot.
    private class Transform {
        Vector2 position;
        Vector2 forward;
        private float angle = 0.0f;

        private Transform(final Vector2 position, final Vector2 forward, final float angle) throws InvalidAngleException, InvalidPositionException {
            if (this.position.x() > WORLD_LIMITS.x() || this.position.y() > WORLD_LIMITS.y())
                throw new InvalidPositionException("Error. Robot's set position cannot be larger than the size of the field.");
            else this.position = position;

            if (this.angle > 360)
                throw new InvalidAngleException("Error. Robot's set angle cannot be larger than 360.");
            else this.forward = forward;
        }
    }

    private RelicRecoveryVuMark vuemark;
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private final float DEAULT_POWER = -0.3f;

    private final Vector2 ZERO_VECTOR = new Vector2(0.0f, 0.0f);
    private final Vector2 UNIT_I = new Vector2(1.0f, 0.0f);
    private final Vector2 UNIT_J = new Vector2(0.0f, 1.0f);
    private final Vector2 WORLD_LIMITS = new Vector2(12.0f, 12.0f);

    private Mode mode;
    private Transform transform;


    public Bot(HardwareMap map, final Mode mode, final Vector2 startingPosition, final Vector2 forwardVector, final float angle) {
        try {
            this.transform = new Transform(startingPosition, forwardVector, angle);
        }
        catch (InvalidAngleException e) {
            System.out.println(e.getMessage());
            System.out.println("WARNING! Clamping angle.");
        }
        catch (InvalidPositionException e) {
            e.printStackTrace();
        }
    }

    public Bot(HardwareMap map, final Mode mode) {
        this.mode = mode;
    }

    public void update() {

    }

    public void move(final Vector2 v, final float powerL, final float powerR) {}

    public void move(final Vector2 v, final float powerL) {}

    public void move() {}

    public void rotate(float beta) {}

    public final Vector2 forwardVector() {
        return transform.forward;
    }

    public void lookat(Vector2 v) {}

    //clamp: {R} -> [a, b]
    public final float clamp(float v, float a, float b) {
        if (v > b) return b;
        if (v < a) return a;
        return v;
    }
}

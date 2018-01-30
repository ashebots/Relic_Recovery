package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;

import java.util.List;
import java.util.Queue;

/**
 * Created by rostifar on 1/25/18.
 */

public class Coordinate {
    private static final int FIELD_OFFSET_X = 6;
    private static final int FIELD_OFFSET_Y = 6;
    private static final int EPSILON = 2;
    private Vector2 position;
    private Queue<Action> actions;

    public Coordinate(Vector2 position) {
        this.position = position;
    }

    public Vector2 position() {
        return position;
    }

    public static Vector2 EasyToGlobalPosition(Vector2 easyVec) {
        return new Vector2(easyVec.x() - FIELD_OFFSET_X, easyVec.y() - FIELD_OFFSET_Y);
    }
}

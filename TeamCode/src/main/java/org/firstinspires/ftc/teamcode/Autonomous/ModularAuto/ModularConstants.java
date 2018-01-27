package org.firstinspires.ftc.teamcode.Autonomous.ModularAuto;

import org.firstinspires.ftc.teamcode.LinearAlgebra.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jezebelquit on 9/23/17.
 */

public class ModularConstants {

    //Coordinate constants
    public static float[] ORIGIN = {0,0};

    public static float[] BALANCE_STONE_A = {2, 2};
    public static float[] BALANCE_STONE_B = {2, 8};

    public static float[] LEFT_COLUMN_A = {0.5f, 4.875f};
    public static float[] MID_COLUMN_A = {0.5f, 5};
    public static float[] RIGHT_COLUMN_A = {0.5f, 5.125f};
    public static float[] CRYPTOBOX_A = {2, 5};

    public static float[] LEFT_COLUMN_B = {2.125f, 11.5f};
    public static float[] MID_COLUMN_B = {3, 11.5f};
    public static float[] RIGHT_COLUMN_B = {3.875f, 11.5f};
    public static float[] CRYPTOBOX_B = {3,10};

    //Pictograph scanning positions
    public static float[] PICTOGRAPH_A = {2, 3};
    public static float[] PICTOGRAPH_B = {2, 9};

    //Coordinate set constants
    public static float[][] placePosA = {ModularConstants.LEFT_COLUMN_A, ModularConstants.RIGHT_COLUMN_A,ModularConstants.MID_COLUMN_A};
    public static float[][] placePosB = {ModularConstants.LEFT_COLUMN_B, ModularConstants.RIGHT_COLUMN_B,ModularConstants.MID_COLUMN_B};

    //Encoders per rotation constants
    public static int NEVERREST_20 = 560;
    public static int NEVERREST_40 = 1120;
    public static int NEVERREST_60 = 1680;


    public static Vector2 V_BALANCE_STONE_A = new Vector2(-4, -4);


}

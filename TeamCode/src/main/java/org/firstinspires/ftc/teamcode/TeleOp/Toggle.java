package org.firstinspires.ftc.teamcode.TeleOp;

/**
 * Created by famil on 11/16/2017.
 */

public class Toggle {

    private static boolean[] output = {false, false, false, false, false};
    private static boolean[] gate = {true, true, true, true, true, true, true, true, true, true};

    public static boolean toggle (boolean input, int modeNum){

        if (input && gate[modeNum]){

            output[modeNum] = !output[modeNum];
            gate[modeNum] = false;

        }

        if (!input) gate[modeNum] = true;

        return output[modeNum];
    }

    private static int[] numOutput = {1, 1, 1, 1, 1};

    public static int numChange (boolean lower, boolean rise, int numCap, int modeNum){

        if (rise && gate[modeNum+5] && numOutput[modeNum] < numCap){

            numOutput[modeNum]++;
            gate[modeNum+5] = false;

        }else if (lower && gate[modeNum+5] && numOutput[modeNum] > 1){

            numOutput[modeNum]--;
            gate[modeNum+5] = false;

        }

        if (!rise && !lower) gate[modeNum+5] = true;

        return numOutput[modeNum];
    }

    public static void setToggle (int modeNum, boolean state){ output[modeNum] = state; }
    public static void setNum (int modeNum, int num){ numOutput[modeNum] = num; }

    public static void resetStates(){

        boolean[] newOutput = {false, false, false, false, false};
        output = newOutput;

        int[] newNumOutput = {1, 1, 1, 1, 1};
        numOutput = newNumOutput;

    }

}




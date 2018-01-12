package org.firstinspires.ftc.teamcode.TeleOp;

/**
 * Created by famil on 11/16/2017.
 */

public class Toggle {

    static boolean[] output = {false, false, false, false, false};
    static boolean[] gate = {true, true, true, true, true};

    public static boolean toggle (boolean input, int modeNum){

        if (input && gate[modeNum]){
            output[modeNum] = !output[modeNum];
            gate[modeNum] = false;
        }

        if (!input) gate[modeNum] = true;

        return output[modeNum];
    }

}




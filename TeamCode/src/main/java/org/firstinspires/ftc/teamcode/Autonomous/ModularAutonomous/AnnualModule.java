package org.firstinspires.ftc.teamcode.Autonomous.ModularAutonomous;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.Autonomous.Move.VueMarkID;

/**
 * Created by famil on 12/7/2017.
 */

public class AnnualModule {

    static Servo leftRotator;
    static Servo rightRotator;
    static Servo gemArm;

    VueMarkID mark;
    RelicRecoveryVuMark vuMark;

    public AnnualModule (Servo leftRotator, Servo rightRotoator, Servo gemArm, VueMarkID mark){

        this.leftRotator = leftRotator;
        this.rightRotator = rightRotoator;

        this.gemArm = gemArm;

        this.mark = mark;
    }

    public void coordCheck(float[][] currentPos, int i){

        if(currentPos[i] == ModularConstants.PICTOGRAPH_A){
            vuMark = mark.vueName();
            if(vuMark == RelicRecoveryVuMark.UNKNOWN){
                vuMark = mark.vueName();
            }

            if(vuMark == RelicRecoveryVuMark.LEFT){
                currentPos[i + 1] = ModularConstants.LEFT_COLUMN_A;
            }else if(vuMark == RelicRecoveryVuMark.RIGHT){
                currentPos[i + 1] = ModularConstants.RIGHT_COLUMN_A;
            }else{
                currentPos[i + 1] = ModularConstants.MID_COLUMN_A;
            }
        }else if(currentPos[i] == ModularConstants.PICTOGRAPH_B){
            vuMark = mark.vueName();
            if(vuMark == RelicRecoveryVuMark.UNKNOWN){
                vuMark = mark.vueName();
            }

            if(vuMark == RelicRecoveryVuMark.LEFT){
                currentPos[i + 1] = ModularConstants.LEFT_COLUMN_B;
            }else if(vuMark == RelicRecoveryVuMark.RIGHT){
                currentPos[i + 1] = ModularConstants.RIGHT_COLUMN_B;
            }else{
                currentPos[i + 1] = ModularConstants.MID_COLUMN_B;
            }//Check if robot is at placing locations
        }else if (posCheck(currentPos[i], ModularConstants.placePosA)){
            glyphPlace(90);
        }else if(posCheck(currentPos[i], ModularConstants.placePosB)){
            glyphPlace(180);
        }

    }

    boolean posCheck(float[] curPos, float[][] checkPos){

        for(int i = 0; checkPos.length < i; i++){
            if(curPos == checkPos[i]){
                return true;
            }
        }

        return false;
    }

    public static void glyphPlace(float angle){

        ImuChassis.turnToAngle(angle, .4);
        ImuChassis.driveXFeet(-2, .8);
        leftRotator.setPosition(0.4);
        rightRotator.setPosition(0.4);
        ImuChassis.driveXFeet(2,.8);
    }

}

package org.firstinspires.ftc.teamcode.Autonomous.DisplayPrograms;

/**
 * Created by secre on 11/23/2017.
 */

public class ArrayDis {

    public String displayMatrix(int[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(Integer.toString(input[i][n]) + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(long[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(Long.toString(input[i][n]) + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(double[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(Double.toString(input[i][n]) + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(float[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(Float.toString(input[i][n]) + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(String[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(input[i][n] + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(char[][] input) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                output = output.concat(input[i][n] + " , ");
            }
            output = output.concat("\n");
        }
        return output;
    }

    public String displayMatrix(boolean[][] input, String trueText, String falseText) {
        String output = "";

        for(int i = 0; i < input.length; i++) {
            for(int n = 0; n < input[i].length; n++) {
                if(input[i][n]) {
                    output = output.concat(trueText + " , ");
                }else{
                    output = output.concat(falseText + " , ");
                }
            }
            output = output.concat("\n");
        }
        return output;
    }

    public boolean[][] arrayInverter(boolean[][] input){
        boolean[][] output = {};

        for(int i = 0; i < input.length; i++){
            for(int n = 0; n < input[i].length; n++){
                if(input[i][n]){
                    output[i][n] = false;
                }else{
                    output[i][n] = true;
                }
            }
        }
        return output;
    }
}


package com.patrikduch.graphics.ticTacToe.graphics;

/**
 * Conversion from grid system to the single format and vice versa
 *<pre>
 *Example: 1,0 -> 3  and  3 -> 1,0
 *</pre>
 */
public class Conversions {

    // Get x,y cordinate for occupying proper cell in Board class
    public static int[] convertToCellGrid(int input){

        // Allocation result array
        int[] result = new int[2];

        // Coordinate variable  which will be calculated
        int x = 0;
        int y = 0;


        if(input >= 0 && input<=2) {
            x = input;
            y =  0;

        } else if (input>= 3 && input<= 5) {

            x = input-3;
            y = 1;

        } else if (input>= 6 && input<= 8) {

            x = input-6;
            y = 2;

        }

        result[0] = x;
        result[1] = y;

        return result;
    }



    public static int converToSingleCoordinate(int x, int y){

        if(y!=0){

            x = y == 1 ? 3 + x : 6+x;
        }

        return x;

    }

















}

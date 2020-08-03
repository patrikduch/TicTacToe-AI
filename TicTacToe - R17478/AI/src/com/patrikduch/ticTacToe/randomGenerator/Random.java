package com.patrikduch.ticTacToe.randomGenerator;

import java.time.LocalTime;

public class Random {

    public int generateInt(int max, int min){

        java.util.Random random = new java.util.Random(LocalTime.now().getNano());
        return random.nextInt(max-min);
    }


}

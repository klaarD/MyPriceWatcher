package edu.utep.cs.cs4330.mypricewatcher;

import java.util.Random;



public class PriceFinder {
    public static final int FIRST = 130;
    public static final int LAST = 170;

    private int getRandom(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int getPrice(String Source){
        return getRandom(FIRST,LAST);
    }
}

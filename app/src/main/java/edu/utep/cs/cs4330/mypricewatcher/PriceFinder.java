package edu.utep.cs.cs4330.mypricewatcher;

import java.util.Random;


/**
 * Class specifies methods for finding new price of a product based on a specified source value
 * In this iteration only random prices are generated
 */
public class PriceFinder {

    public static final int FIRST = 130; // lower bound of random number generation
    public static final int LAST = 170; //higher bound of random number generation

    /**
     *
     * @param min   lower bound of the generated number
     * @param max   higher bound of the generated number
     * @return randomly generated number, variable of a type int
     */
    private int getRandom(int min, int max){
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     *
     * @param Source    A string variable that will be used in future iterations
     * @return  A variable of type int, new price
     */
    public int getPrice(String Source){
        return getRandom(FIRST,LAST);
    }
}

package edu.utep.cs.cs4330.mypricewatcher;

/**
 * Item --- class to store one concrete item
 * @author Klara Dvorakova
 */
public class Item {

    private int InitialPrice; // initial price of the item
    private int CurrentPrice; //current price (the last calculated one) of the item
    private int ChangePrice; // price change - from the initial price to the current price, in percentage
    String Source; // URL of the item
    private PriceFinder priceFinder = new PriceFinder(); //object used for calculating new price

    /**
     * Constructor of class Item
     * @param Source A varivable of a type String, URL of the item
     */
    Item(String Source){
        this.Source = Source;
        InitialPrice = priceFinder.getPrice(Source);
        CurrentPrice = InitialPrice;
        ChangePrice = 0;
    }

    /**
     * Method that changed CurrentPrice variable to new price
     */
    public void CalculateNewPrice(){
        CurrentPrice = priceFinder.getPrice(Source);
        CalculateChange();
    }

    /**
     * @return initial price of the item
     */
    public int getInitialPrice(){
        return InitialPrice;
    }

    /**
     * @return current price of the item
     */
    public int getCurrentPrice(){
        return CurrentPrice;
    }

    /**
     * @return price change of the item
     */
    public int getChangePrice(){
        return ChangePrice;
    }

    /**
     * @return true if the current price is higher than the initial price, false otherwise
     */
    public boolean ChangePositive(){
        if(ChangePrice > 0) return true;
        return false;
    }

    /**
     * calculates price change
     * sets ChangePrice variable to new value
     */
    private void CalculateChange(){
        ChangePrice = (CurrentPrice*100/InitialPrice)-100;
    }








}

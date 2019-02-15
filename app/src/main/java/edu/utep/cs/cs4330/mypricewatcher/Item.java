package edu.utep.cs.cs4330.mypricewatcher;

public class Item {

    private int InitialPrice;
    private int CurrentPrice;
    private int ChangePrice;
    String Source;
    private PriceFinder priceFinder = new PriceFinder();

    Item(String Source){
        this.Source = Source;
        InitialPrice = priceFinder.getPrice(Source);
        CurrentPrice = InitialPrice;
        ChangePrice = 0;
    }

    public void CalculateNewPrice(){
        CurrentPrice = priceFinder.getPrice(Source);
        CalculateChange();
    }

    public int getInitialPrice(){
        return InitialPrice;
    }

    public int getCurrentPrice(){
        return CurrentPrice;
    }

    public int getChangePrice(){
        return ChangePrice;
    }

    public boolean ChangePositive(){
        if(ChangePrice > 0) return true;
        return false;
    }

    private void CalculateChange(){
        ChangePrice = (CurrentPrice*100/InitialPrice)-100;
    }








}

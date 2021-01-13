package com.example.coinstrike.coinstrike.models;

/**
 * This model stores board related information that number of coins of each type.
 */
public class Board {
    private int blackCoins;
    private int redCoins;

    public Board()
    {
        this.blackCoins = 0;
        this.redCoins = 0;
    }
    public Board(int blackCoins, int redCoins)
    {
        this.blackCoins = blackCoins;
        this.redCoins = redCoins;
    }
    public void setBlackCoinsCount(int blackCoins)
    {
        this.blackCoins = blackCoins;
    }
    public void setRedCoinsCount(int redCoins)
    {
        this.redCoins = redCoins;
    }
    public int getBlackCoinsCount()
    {
        return this.blackCoins;
    }
    public int getRedCoinsCount(){
        return this.redCoins;
    }       
}

package com.example.coinstrike.coinstrike.controllers;

import com.example.coinstrike.coinstrike.constants.CoinType;
import com.example.coinstrike.coinstrike.models.Board;

/**
 * This is controller class which increases/decreases the coins count.
 */
public class BoardController {
    private Board board;
    /**
     * Creates new instance of Board class and this board is used to perform moves on it by
     * increasing and decreasing the coins count.
     * @param blackCoinsCount This parameter sets black coins count
     * @param redCoinsCount   This parameter sets red coins count
     */
    public BoardController(int blackCoinsCount, int redCoinsCount)
    {
        this.board = new Board(blackCoinsCount, redCoinsCount);
    }

    /**
     * Increases count of a particular type of coin.
     * @param coinType This holds value RED or BLACK these are type of coins
     * @param count    This holds count by which coins will be increased
     */
    public void increaseCoins(CoinType coinType, int count)
    {
        int oldCoinsCount, newCoinsCount;

        if(CoinType.RED == coinType){
            oldCoinsCount = this.board.getRedCoinsCount();
            newCoinsCount = oldCoinsCount + count;
            this.board.setRedCoinsCount(newCoinsCount);
        }else if(CoinType.BLACK == coinType){
            oldCoinsCount = this.board.getBlackCoinsCount();
            newCoinsCount = oldCoinsCount + count;
            this.board.setBlackCoinsCount(newCoinsCount);
        }
        return;
    }

    /**
     * Following function decreases the count of a particular coin type.
     * @param coinType
     * @param count
     */
    public void decreaseCoins(CoinType coinType, int count)
    {
        int oldCoinsCount, newCoinsCount;

        if(CoinType.RED == coinType){
            oldCoinsCount = this.board.getRedCoinsCount();
            newCoinsCount = oldCoinsCount - count;
            this.board.setRedCoinsCount(newCoinsCount);
        }else if(CoinType.BLACK == coinType){
            oldCoinsCount = this.board.getBlackCoinsCount();
            newCoinsCount = oldCoinsCount - count;
            this.board.setBlackCoinsCount(newCoinsCount);
        }
        return;
    }
    
    /**
     * Returns count of a requested coin type.
     * If coin type is not found then it returns -1
     * @param coinType this param holds which type of coins count is requested
     * @return 
     */
    public int getCoinsCount(CoinType coinType)
    {
        // If coin type is not found then this function returns -1
        int coinsCount = -1;
        if(CoinType.BLACK == coinType){
            coinsCount = this.board.getBlackCoinsCount();
        }else if(CoinType.RED == coinType){
            coinsCount = this.board.getRedCoinsCount();
        }
        return coinsCount;
    }

    /**
     * This function returns true if there is atleast one coin of either type and 
     * returns false in case of all type of coins count is zero
     * @return
     */
    public boolean isBoardEmpty()
    {
        if(this.board.getBlackCoinsCount() <= 0 && this.board.getRedCoinsCount() <= 0){
            return true;
        }
        return false;
    }
}

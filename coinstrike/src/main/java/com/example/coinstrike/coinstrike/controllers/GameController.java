package com.example.coinstrike.coinstrike.controllers;

import com.example.coinstrike.coinstrike.constants.CoinType;
import com.example.coinstrike.coinstrike.constants.GameConfiguration;

/**
 * This controller is used to make the changes on board as well as to update the
 * state of player which is passed in every funtion apart from constructor by
 * changing points, fouls count and by changing unsuccessful attempts count.
 */
public class GameController {
    private BoardController board;

    /**
     * Following constructor sets the number of coins, of each type 
     * that are being used for this game
     * @param blackCoinsCount
     * @param redCoinsCount
     */
    public GameController(int blackCoinsCount, int redCoinsCount)
    {
        this.board = new BoardController(blackCoinsCount, redCoinsCount);
    }
    
    /**
     * Following function will be called to play the strike move by passing 
     * playerController param by which player's state is updated.
     * @param playerController
     */
    public void strikeMove(PlayerController playerController)
    {
        // Black coins count is decreased by one and player gets one point
        this.board.decreaseCoins(CoinType.BLACK, 1);
        playerController.increasePoints(GameConfiguration.STRIKE_POINTS);
        playerController.resetUnsuccessfulAttempts();
    }
    
    /**
     * Following function applies multi strike move to black coins and changes the state of
     * player passed as a parameter
     * @param playerController player who played this move he wins 2 points
     * @param count count is the number of coins the player pocketed the black coins
     */
    public void multiStrikeMove(PlayerController playerController, int count)
    {
        // Black coins count decreased by two and Player gets two points
        this.board.decreaseCoins(CoinType.BLACK, GameConfiguration.MAX_COINS_ACCEPTED_IN_MULTI_STRIKE_MOVE);
        playerController.increasePoints(GameConfiguration.MULTI_STRIKE_POINTS);
        playerController.resetUnsuccessfulAttempts();
    }

    /**
     * redStrikeMove brings RED_STRIKE_POINTS increased in players points and removes the only red
     * coin from the board.
     * @param playerController playerController changes the points of player who played this move
     */
    public void redStrikeMove(PlayerController playerController)
    {
        // Red coins count decreased by one and Player gets three points
        this.board.decreaseCoins(CoinType.RED, 1);
        playerController.increasePoints(GameConfiguration.RED_STRIKE_POINTS);
        playerController.resetUnsuccessfulAttempts();
    }

    /**
     * This move is a foul move this will deduct the points from the player's points and 
     * increases the fouls count and unsuccessful attempts
     * @param playerController playerController is used the change the state of the player
     */
    public void strikerStrikeMove(PlayerController playerController)
    {
        // Player loses one point
        playerController.decreasePoints(GameConfiguration.STRIKER_STRIKE_POINTS);
        playerController.increaseUnsuccessfulAttempts();
        if(playerController.getRecentUnsuccessfulAttemptsCount()>=3)
        {
            playerController.decreasePoints(GameConfiguration.UNSUCCESSFUL_ATTEMPTS_POINTS);
            playerController.increaseFoulsCount();
        }
        playerController.increaseFoulsCount();
        if(playerController.getTotalFoulsCount() >= 3)
        {
            playerController.decreasePoints(GameConfiguration.FOUL_POINTS);
        }
    }

    /**
     * This function removes a coin on the board and player will lose the points
     * @param playerController playerController is used to modify the state of player
     * @param coinType Type of coin which is defunct by player
     */
    public void defunctCoinMove(PlayerController playerController, CoinType coinType)
    {
        // Here coin goes out of play the coin might be red or black
        // and player loses 2 points
        this.board.decreaseCoins(coinType, 1);
        playerController.decreasePoints(GameConfiguration.DEFUNCT_STRIKE_POINTS);
        playerController.increaseUnsuccessfulAttempts();
        if(playerController.getRecentUnsuccessfulAttemptsCount()>=3)
        {
            playerController.decreasePoints(GameConfiguration.UNSUCCESSFUL_ATTEMPTS_POINTS);
            playerController.increaseFoulsCount();
        }
        playerController.increaseFoulsCount();
        if(playerController.getTotalFoulsCount() >= 3)
        {
            playerController.decreasePoints(GameConfiguration.FOUL_POINTS);
        }
    }

    /**
     * This function does not change state of coins but it will increase the unsuccessful attempts
     * count by one
     * @param playerController playerController is used to change the state of player
     */
    public void noneMove(PlayerController playerController)
    {
        playerController.increaseUnsuccessfulAttempts();
        if(playerController.getRecentUnsuccessfulAttemptsCount()>=3)
        {
            playerController.decreasePoints(GameConfiguration.UNSUCCESSFUL_ATTEMPTS_POINTS);
            playerController.increaseFoulsCount();
        }
        if(playerController.getTotalFoulsCount() >= 3)
        {
            playerController.decreasePoints(GameConfiguration.FOUL_POINTS);
        }
    }
    
    /**
     * This function returns true if board is empty else returns false
     * @return
     */
    public boolean isBoardEmpty()
    {
        return this.board.isBoardEmpty();
    }
    
    /**
     * This function returns the count of a particular type of coin
     * @param coinType Type of coin whoes count is requested
     * @return
     */
    public int getCoinsCount(CoinType coinType)
    {
        return this.board.getCoinsCount(coinType);
    }
}

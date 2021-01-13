package com.example.coinstrike.coinstrike.controllers;

import com.example.coinstrike.coinstrike.constants.CoinType;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Here GameController class is tested by playing different moves and verifying whether
 * state of board and player's state are changing correctly or not
 */
@SpringBootTest
public class GameControllerTests {
    /**
     * Testing the strike move
     * 1) On a board having 9-black coins and 1-red coin
     * 2) On a board having 0-black coins and 1-red coin
     * In second case function will not throw any error because exception handling is done
     * in MatchController
     */
    @Test
    void testStrikeMove(){
        GameController gameController = new GameController(9, 1);
        PlayerController playerController = new PlayerController(13);
        // playing strike move on board having 9 black coins and 1 red coin
        gameController.strikeMove(playerController);
        // State of board
        assertEquals(8, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player's state
        assertEquals(1, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());

        gameController = new GameController(0, 1);
        playerController = new PlayerController(10);
        // playing strike move on board having 0 black coins and 1 red coin
        gameController.strikeMove(playerController);
        // State of board
        assertEquals(-1, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player's state
        assertEquals(1, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing multi Strike move by playing following moves
     * 1) multi strike by pocketing 4 coins 
     * 2) multi strike by pocketing 2 coins
     */
    @Test
    void testMultiStrikeMove(){
        GameController gameController = new GameController(9, 1);
        PlayerController playerController = new PlayerController(10);
        // Playing multi strike move by pocketing 4 coins
        gameController.multiStrikeMove(playerController, 4);
        // state of board
        assertEquals(7, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player's state
        assertEquals(2, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());

        gameController = new GameController(7, 1);
        playerController = new PlayerController(10);
        // playing multi strike move by pocketing 2 coins
        gameController.multiStrikeMove(playerController, 2);
        // state of board
        assertEquals(5, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player's state
        assertEquals(2, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing red strike move
     * 1) Playing red strike on a board having 7 black coins and 1 red coin
     */
    @Test
    void testRedStrikeMove(){
        GameController gameController = new GameController(7, 1);
        PlayerController playerController = new PlayerController(10);
        // playing red strike move
        gameController.redStrikeMove(playerController);
        // state of board
        assertEquals(7, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(0, gameController.getCoinsCount(CoinType.RED));
        // Player's state
        assertEquals(3, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing when a player strikes a striker it self
     * Here player will lose 1 point
     * fouls count will be increased by 1
     * unsuccessful attempts count will also increased by 1
     */
    @Test
    void testStrikerStrikeMove(){
        GameController gameController = new GameController(9, 1);
        PlayerController playerController = new PlayerController(10);
        gameController.strikerStrikeMove(playerController);
        // state of board will not change
        assertEquals(9, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player will lose 1 point
        assertEquals(-1, playerController.getPoints());
        assertEquals(1, playerController.getTotalFoulsCount());
        assertEquals(1, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing defunct coin move
     * Here defuncted coin will go out of play/board 
     * player will lose 2 points
     * this also considered as foul
     * unsuccessful attempts count will also increased by one
     */
    @Test
    void testDefunctCoinMove(){
        GameController gameController = new GameController(9, 1);
        PlayerController playerController = new PlayerController(10);
        gameController.defunctCoinMove(playerController, CoinType.BLACK);
        // state of board
        // one black coin go out of game
        assertEquals(8, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player will lose 2 points
        assertEquals(-2, playerController.getPoints());
        assertEquals(1, playerController.getTotalFoulsCount());
        assertEquals(1, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing none move where none of the coin is pocketed
     * Here player will not get any points
     * Unsuccessful attempts count will be increased by one
     * this move is not considered as foul move
     */
    @Test
    void testNoneMove(){
        GameController gameController = new GameController(9, 1);
        PlayerController playerController = new PlayerController(10);
        gameController.noneMove(playerController);
        // state of board will not change
        assertEquals(9, gameController.getCoinsCount(CoinType.BLACK));
        assertEquals(1, gameController.getCoinsCount(CoinType.RED));
        // Player will not lose any points instead increases unsuccessful attempts count
        assertEquals(0, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(1, playerController.getRecentUnsuccessfulAttemptsCount());
    }
}

package com.example.coinstrike.coinstrike.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.coinstrike.coinstrike.constants.CoinType;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class tests BoardController
 */

@SpringBootTest
public class BoardControllerTests {
    /**
     * Testing increaseCoins function
     * Increase Black coins count by one
     * Increase Red coins count by two
     */
    @Test
    void testIncreaseCoins(){
        BoardController boardController = new BoardController(9, 1);
        // Increasing black coins count by one
        boardController.increaseCoins(CoinType.BLACK, 1);
        assertEquals(10, boardController.getCoinsCount(CoinType.BLACK), "Increased Black coins count by one");
        
        // Increasing red coins count by two
        boardController.increaseCoins(CoinType.RED, 2);
        assertEquals(3, boardController.getCoinsCount(CoinType.RED));
    }

    /**
     * Testing decreaseCoins function
     * Decrease Black coins count by five
     * Decrease Red coins count by one
     * Decrease Black coins count by zero
     */
    @Test
    void testDecreaseCoins(){
        BoardController boardController = new BoardController(8, 1);

        // Decrease black coins count by five
        boardController.decreaseCoins(CoinType.BLACK, 5);
        assertEquals(3, boardController.getCoinsCount(CoinType.BLACK));

        // Decrease red coins count by one
        boardController.decreaseCoins(CoinType.RED, 1);
        assertEquals(0, boardController.getCoinsCount(CoinType.RED));

        // Decrease black coins count by zero
        boardController.decreaseCoins(CoinType.BLACK, 0);
        assertEquals(3, boardController.getCoinsCount(CoinType.BLACK));
    }

    /**
     * Testing whether BoardController's isBoardEmpty function for an 
     * non empty board as well as for empty board
     */
    @Test
    void testIsBoardEmpty(){
        // Testing for non empty board
        BoardController boardController1 = new BoardController(10, 1);
        assertEquals(false, boardController1.isBoardEmpty());

        // Testing for empty board
        BoardController boardController2 = new BoardController(0, 0);
        assertEquals(true, boardController2.isBoardEmpty());
    }
}

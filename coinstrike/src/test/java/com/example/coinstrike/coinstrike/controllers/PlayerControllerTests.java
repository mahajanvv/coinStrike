package com.example.coinstrike.coinstrike.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class tests playerController
 */
@SpringBootTest
public class PlayerControllerTests {
    /**
     * Testing increasePoints function from PlayerController class
     * 1) By increasing points by 20
     * 2) By increasing points by 0
     */
    @Test
    void testIncreasePoints(){
        PlayerController playerController = new PlayerController(23);
        // Increasing players points by 20
        playerController.increasePoints(20);
        assertEquals(20, playerController.getPoints());
        assertEquals(23, playerController.getPlayerId());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());

        // Increasing players points by zero
        playerController = new PlayerController(221);
        playerController.increasePoints(0);
        assertEquals(0, playerController.getPoints());
        assertEquals(221, playerController.getPlayerId());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
    }

    /**
     * Testing decreasePoints function from PlayerController class
     * 1) by decreasing points by 10
     * 2) by decreasing points by 0
     */
    @Test
    void testDecreasePoints(){
        PlayerController playerController = new PlayerController(21);
        // decreasing points by 10
        playerController.decreasePoints(10);
        assertEquals(-10, playerController.getPoints());
        assertEquals(0, playerController.getTotalFoulsCount());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
        assertEquals(21, playerController.getPlayerId());

        playerController = new PlayerController(12);
        // decreasing points by zero
        playerController.decreasePoints(0);
        assertEquals(0, playerController.getPoints());
        assertEquals(12, playerController.getPlayerId());
        assertEquals(0, playerController.getRecentUnsuccessfulAttemptsCount());
        assertEquals(0, playerController.getTotalFoulsCount());
    }

}

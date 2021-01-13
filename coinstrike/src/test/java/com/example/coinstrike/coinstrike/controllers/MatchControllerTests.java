package com.example.coinstrike.coinstrike.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Here Will test different moves with the edge case senarios
 * by passing asctual strings that are coming from input*.txt files
 */
@SpringBootTest
public class MatchControllerTests {
    
    /**
     * Testing when striker it self is striked in that case player will lose 1 point
     */
    @Test
    void testStrikerStrikeMove(){
        MatchController matchController = new MatchController();
        // Playing Striker strike move
        matchController.validateMoveAndPlay("STRIKER_STRIKE");
        assertEquals("Black_Coins Count:9 Red_Coins Count:1", matchController.getStateOfBoard());
        assertEquals(-1, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());
    }
    
    /**
     * Testing for a None move in which no coin is pocketed
     */
    @Test
    void testNoneMove(){
        MatchController matchController = new MatchController();
        // Playing None move
        matchController.validateMoveAndPlay("NONE");
        assertEquals("Black_Coins Count:9 Red_Coins Count:1", matchController.getStateOfBoard());
        assertEquals(0, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());
    }
    
    /**
     * Testing defunct move played by two players by defuncting black coin as well as red coin
     */
    @Test
    void testDefunctCoinMove(){
        MatchController matchController = new MatchController();
        // Playing Defunct coin move
        matchController.validateMoveAndPlay("DEFUNCT_COIN RED");
        assertEquals("Black_Coins Count:9 Red_Coins Count:0", matchController.getStateOfBoard());
        assertEquals(-2, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());

        matchController.validateMoveAndPlay("DEFUNCT_COIN BLACK");
        assertEquals("Black_Coins Count:8 Red_Coins Count:0", matchController.getStateOfBoard());
        assertEquals(-2, matchController.findPlayerById(0).getPoints());
        assertEquals(-2, matchController.findPlayerById(1).getPoints());
    }
    
    /**
     * 1) Testing red strike move by pocketing red coin with other 4 black coins 
     * 2) by pocketing only red coin
     */
    @Test
    void testRedStrikeMove(){
        MatchController matchController = new MatchController();
        // Playing red strike move
        matchController.validateMoveAndPlay("RED_STRIKE 4");
        assertEquals("Black_Coins Count:9 Red_Coins Count:0", matchController.getStateOfBoard());
        assertEquals(3, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());

        matchController = new MatchController();
        matchController.validateMoveAndPlay("RED_STRIKE");
        assertEquals("Black_Coins Count:9 Red_Coins Count:0", matchController.getStateOfBoard());
        assertEquals(3, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());
    }
    
    /**
     * Testing multi strike move on a board having 9 black coins and 1 red coin
     * by pocketing 4 coins
     */
    @Test
    void testMultiStrikeMove(){
        MatchController matchController = new MatchController();
        // Playing multi strike move
        matchController.validateMoveAndPlay("MULTI_STRIKE 4");
        assertEquals("Black_Coins Count:7 Red_Coins Count:1", matchController.getStateOfBoard());
        assertEquals(2, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());
    }
    
    /**
     * Testing strike move on a board having 9 black coins and 1 red coin
     */
    @Test
    void testStrikeMove(){
        MatchController matchController = new MatchController();
        matchController.validateMoveAndPlay("STRIKE");
        // Playing strike move 
        assertEquals("Black_Coins Count:8 Red_Coins Count:1", matchController.getStateOfBoard());
        assertEquals(1, matchController.findPlayerById(0).getPoints());
        assertEquals(0, matchController.findPlayerById(1).getPoints());
    }
}

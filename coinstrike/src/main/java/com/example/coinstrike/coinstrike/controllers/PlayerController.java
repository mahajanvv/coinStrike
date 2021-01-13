package com.example.coinstrike.coinstrike.controllers;

import com.example.coinstrike.coinstrike.models.Player;

/**
 * This class deals with modification that can be carried out by on the player object
 * such as Increasing/Decreasing the points, fouls count, unsuccessful attempts, etc.
 */
public class PlayerController {

    private Player player;
    /**
     * Following constructor used to instantiate player object with playerId
     * @param playerId
     */
    public PlayerController(int playerId){
        this.player = new Player(playerId);
    }

    /**
     * Following function increases the points of the current player
     * @param points
     */
    public void increasePoints(int points){
        int old_points = this.player.getPoints();
        int new_points = old_points + points;
        this.player.setPoints(new_points);
        
    }

    /**
     * Following function decreases the points of the current player
     * @param points
     */
    public void decreasePoints(int points){
        int old_points = this.player.getPoints();
        int new_points = old_points - points;
        this.player.setPoints(new_points);
    }

    /**
     * Following function increases fouls count of the current player by one
     */
    public void increaseFoulsCount(){
        int old_fouls_count = this.player.getTotalFoulsCount();
        int new_fouls_count = old_fouls_count + 1;
        this.player.setTotalFoulsCount(new_fouls_count);
    }

    /**
     * Following function increases unsuccessful attempts of the current player by one 
     */
    public void increaseUnsuccessfulAttempts(){
        int old_attempts = this.player.getRecentUnsuccessfulAttempts();
        int new_attempts = old_attempts + 1;
        this.player.setRecentUnsuccessfulAttempts(new_attempts);
    }

    /**
     * Following funtion sets the current players recent unsuccessful attempts to zero
     */
    public void resetUnsuccessfulAttempts(){
        this.player.setRecentUnsuccessfulAttempts(0);
    }

    /**
     * Following function returns the points of the current player
     * @return
     */
    public int getPoints(){
        return this.player.getPoints();
    }

    /**
     * Following function returns the total fouls that the current player did
     * @return
     */
    public int getTotalFoulsCount(){
        return this.player.getTotalFoulsCount();
    }

    /**
     * Following function returns the recent unsuccessful attempts of the current player
     * @return
     */
    public int getRecentUnsuccessfulAttemptsCount(){
        return this.player.getRecentUnsuccessfulAttempts();
    }

    /**
     * Following function returns player id of the current player
     * @return
     */
    public int getPlayerId(){
        return this.player.getPlayerId();
    }
}

package com.example.coinstrike.coinstrike.models;

/**
 * This model stores values for Player's ID, points, totalFoulsCount, recentUnsuccessfulAttemptsCount
 */
public class Player {
    private int playerId;
    private int points;
    private int totalFoulsCount;
    private int recentUnsuccessfulAttemptsCount;
    
    public Player(int playerId){
        this.playerId = playerId;
        this.points = 0;
        this.totalFoulsCount = 0;
        this.recentUnsuccessfulAttemptsCount = 0;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    public void setId(int playerId){
        this.playerId = playerId;
    }
    
    public void setTotalFoulsCount(int totalFoulsCount){
        this.totalFoulsCount = totalFoulsCount;
    }
    
    public void setRecentUnsuccessfulAttempts(int recentUnsuccessfulAttemptsCount){
        this.recentUnsuccessfulAttemptsCount = recentUnsuccessfulAttemptsCount;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public int getTotalFoulsCount(){
        return this.totalFoulsCount;
    }
    
    public int getRecentUnsuccessfulAttempts(){
        return this.recentUnsuccessfulAttemptsCount;
    }
    
    public int getPlayerId(){
        return this.playerId;
    }    
}

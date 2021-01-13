package com.example.coinstrike.coinstrike.constants;
/* 
    This interface is used to configure the points & coins. So that we don't have to change 
    anywhere in the code.
 */
public interface GameConfiguration {
    /* 
        game play related constants
    */
    public final int NUMBER_OF_PLAYERS = 2;  
    public final int BLACK_COINS_COUNT = 9;
    public final int RED_COINS_COUNT = 1; 

    /* 
        coins related constants
    */
    // number of coins accepted in case of multi strike move
    public final int MAX_COINS_ACCEPTED_IN_MULTI_STRIKE_MOVE = 2;

    /* 
        constants related to strikes. Following shows number of points gained or lose.
    */
    public final int STRIKE_POINTS = 1;
    public final int MULTI_STRIKE_POINTS = 2;
    public final int RED_STRIKE_POINTS = 3;
    public final int STRIKER_STRIKE_POINTS = 1;
    // Following points are removed from the total points
    public final int DEFUNCT_STRIKE_POINTS = 2;
    public final int UNSUCCESSFUL_ATTEMPTS_POINTS = 1;
    public final int FOUL_POINTS = 1;
    // Winning criteria points
    // Minimum difference of points with other player required for one to win
    public final int MINIMUM_DIFF_POINTS = 3; 
    // Minimum number of points one should have to win
    public final int MINIMUM_POINTS_REQUIRED = 5; 
}

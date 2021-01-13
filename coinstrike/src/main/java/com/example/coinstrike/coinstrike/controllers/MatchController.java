package com.example.coinstrike.coinstrike.controllers;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.example.coinstrike.coinstrike.constants.CoinType;
import com.example.coinstrike.coinstrike.constants.GameConfiguration;
import com.example.coinstrike.coinstrike.exceptions.InvalidMoveException;

/**
 * This class is used to take the inputs and pass to playerController and boardController.
 * Also determines the winner/draw of the match. Here turns are determined and appplied to
 * the player having turn
 */
public class MatchController {
    
    private PriorityQueue<PlayerController> playerControllerList; //This stores all the players playing this match
    private GameController gameController; //This is used to change the state of player and board
    private int turn; //This is used to determine the turn of a player

    /**
     * This constructor instantiate all the attributes that are defined with the help of GameConfiguration interface
     * like number of players, number of coins and its type, etc.
     */
    public MatchController()
    {
        // Here from Game_Constants interface constants such as number of players, black coins count
        // and red coins count are fetched.
        this.playerControllerList = new PriorityQueue<PlayerController>(new Comparator<PlayerController>(){
          @Override
          public int compare(PlayerController p1, PlayerController p2) 
          {
            return (p1.getPoints() > p2.getPoints() ) ? -1: (p1.getPoints() < p2.getPoints()) ? 1:0 ;
          }
        });
        for(int i=0; i<GameConfiguration.NUMBER_OF_PLAYERS; i++)
        {
          this.playerControllerList.add(new PlayerController(i));
        }
        this.gameController = new GameController(GameConfiguration.BLACK_COINS_COUNT, GameConfiguration.RED_COINS_COUNT);
        this.turn = 0;
    }

    /**
     * Following funtion returns plaeyerController and it finds the playerController from 
     * playerControllerList. if not found the player having playerId equal to playerId then it
     * returns null
     * @param playerId ID of the player whom you are looking for
     * @return
     */
    public PlayerController findPlayerById(int playerId)
    {
      // By iterating through all the players it will return player object with the matching id provided
      // in case of not matching id it will return null
      for(PlayerController player : this.playerControllerList)
      {
        if(player.getPlayerId() == playerId)
        {
          return player;
        }
      }
      return null;
    }

    /**
     * This function will return the playerController of the player who is going
     * to make a move
     * @return
     */
    public PlayerController getNextPlayer()
    {
      // This will return a player who is going to make a move
      int turn = this.turn;
      int NUMBER_OF_PLAYERS = GameConfiguration.NUMBER_OF_PLAYERS;
      if(turn%NUMBER_OF_PLAYERS == 0) 
      {
        turn = 0;
      }
      else
      {
        turn = turn%NUMBER_OF_PLAYERS;
      }
      PlayerController player = findPlayerById(turn);
      return player;
    }

    /**
     * This functions returns a string stating the points, fouls count, etc..
     * of the player who is passed as a parameter
     * @param playerContoller
     * @return
     */
    public String getStateOfPlayer(PlayerController playerController)
    {
      // This function is used to print the state of the player
      String str = "";
      str += "Player ID:"+playerController.getPlayerId()+" Points-> "+playerController.getPoints()+" Total_Fouls_Count-> "+ playerController.getTotalFoulsCount()+ " Recent_Unsuccessful_Attempts_Count -> "+ playerController.getRecentUnsuccessfulAttemptsCount();
      return str;
    }

    /**
     * This function returns the state of the board.like number of coins of each type
     * in the form of string.
     * @return
     */
    public String getStateOfBoard()
    {
      // This function is used to print state of the board. such as number of coins  present on the board
      String str="";
      str += "Black_Coins Count:"+this.gameController.getCoinsCount(CoinType.BLACK) +" Red_Coins Count:"+this.gameController.getCoinsCount(CoinType.RED);
      return str;
    }

    /**
     * This function returns the winner after playing a move if none of the player is winner
     * then it returns null
     * @return
     */
    public PlayerController calculateWinner()
    {
      // This function will find the winner based on the move that is made by player
      // Here we are checking only the two players who are leading the match
      PlayerController player1 = this.playerControllerList.poll();
      PlayerController player2 = this.playerControllerList.poll();
      this.playerControllerList.add(player1);
      this.playerControllerList.add(player2);
      if(player1.getPoints() >= player2.getPoints()+GameConfiguration.MINIMUM_DIFF_POINTS && player1.getPoints() >= GameConfiguration.MINIMUM_POINTS_REQUIRED)
      {
        return player1;
      }
      else if(this.gameController.isBoardEmpty() == true)
      {
        if(player1.getPoints() >= player2.getPoints()+GameConfiguration.MINIMUM_DIFF_POINTS || player1.getPoints() >= GameConfiguration.MINIMUM_POINTS_REQUIRED)
        {
          return player1;
        }
      }
      return null;
    }
    
    /**
     * This function changes the turn variable which then later on used to get the player
     * who is going to play the move
     */
    public void changeTurn(){
      // this function is used to calculate the turn of a player after every move is played
      int turn = this.turn;
      turn++;
      this.turn = turn%GameConfiguration.NUMBER_OF_PLAYERS;
    }
    
    /**
     * This function accepts command and plays the move and returns false if enough commands are 
     * processed if board is not empty and winner is not present then it returns true
     * @param input_command
     * @return
     */
    public boolean playMove(String input_command) {
      // This function will take a command, validates input and calls for a move for a
      // player
      // whoes turn is there.
      String[] inputs = input_command.split(" ");
      String move = inputs[0];
      PlayerController playerController = getNextPlayer();
      this.playerControllerList.remove(playerController);
      System.out.println("\nPlayer" + playerController.getPlayerId() + " choosed:" + input_command + " move");
      System.out.println("Before playing move=>" + getStateOfPlayer(playerController));
      System.out.println("Before playing move=> Board State:" + getStateOfBoard());
      switch (move) {
        case "STRIKE":
            this.gameController.strikeMove(playerController);
            break;
        case "MULTI_STRIKE":
            // We should take one more input that is count of striked coins
            this.gameController.multiStrikeMove(playerController, Integer.parseInt(inputs[1]));
            break;
        case "RED_STRIKE":
            this.gameController.redStrikeMove(playerController);
            break;
        case "NONE":
            this.gameController.noneMove(playerController);
            break;
        case "DEFUNCT_COIN":
            if(inputs[1].equals("BLACK")){
                this.gameController.defunctCoinMove(playerController, CoinType.BLACK);
            }else if(inputs[1].equals("RED")){
                this.gameController.defunctCoinMove(playerController, CoinType.RED);
            }
            break;
        case "STRIKER_STRIKE":
            this.gameController.strikerStrikeMove(playerController);
            break;
      }
      this.playerControllerList.add(playerController);
      System.out.println("After playing move=> Board State:" + getStateOfBoard());
      System.out.println("After playing move=>" + getStateOfPlayer(playerController) + "\n");
      changeTurn();
      PlayerController winner = calculateWinner();
      if (winner != null) {
        System.out.println("Winner is:Player" + winner.getPlayerId() + " having points:" + winner.getPoints());
        return false;
      } else {
        if (this.gameController.isBoardEmpty() == true) {
          System.out.println("Match is draw");
          return false;
        }
      }
      return true;
    }

    /**
     * This function accepts the input and catches the InvalidMoveException if the move is not valid
     * and in case of valid move it calls for playMove function.
     * @param input
     * @return
     */
    public boolean validateMoveAndPlay(String input){
      // This function validates the move. such as whether coins are present or not.
      try{
        InvalidMoveException.validateMove(input, this.gameController);
        if(playMove(input) == false){
          return false;
        }
      }catch(InvalidMoveException e){
        System.out.println("\nDiscarding "+input+ " move because of following exception");
        System.out.println(e.getMessage()+"\n");
      }
      return true;
    }
}

package com.example.coinstrike.coinstrike.exceptions;

import com.example.coinstrike.coinstrike.constants.CoinType;
import com.example.coinstrike.coinstrike.constants.GameConfiguration;
import com.example.coinstrike.coinstrike.controllers.GameController;

/**
 * InvalidMoveException throws an exception in edge cases. It validates the move
 * if move can be carried out on the board then only that move is applied to the
 * board as well as for player.
 */
public class InvalidMoveException extends Exception{
    
    private static final long serialVersionUID = 1L;

    /**
     * Constructor sends the message to parent class exception
     * @param message This parameter is passed to parent class exception.
     */
    public InvalidMoveException(String message){
        super(message);
    }

    /**
     * Following function validates the move and in case of invalid move it throws 
     * exception of type InvalidMoveException
     * @param move This param is coming from the input text files 
     * @param gameController This param is used to check whether move can be successfully applied to board as well as to player 
     */
    public static void validateMove(String move, GameController gameController)throws InvalidMoveException{
        String[] inputs = move.split(" ");
        if(inputs.length>2){
            throw new InvalidMoveException("Invalid move only 2 parameters has to be passed");
        }else{
            switch(inputs[0]){
                case "STRIKE":
                    if(gameController.getCoinsCount(CoinType.BLACK)<1){
                        throw new InvalidMoveException("Invalid move there is no BLACK COIN present on the board");
                    }
                break;
                case "MULTI_STRIKE":
                    int coinsCount = Integer.parseInt(inputs[1]);
                    if(coinsCount < GameConfiguration.MAX_COINS_ACCEPTED_IN_MULTI_STRIKE_MOVE){
                        throw new InvalidMoveException("You should pocket atleast "+GameConfiguration.MAX_COINS_ACCEPTED_IN_MULTI_STRIKE_MOVE+" coins in MULTI_STRIKE move");
                    }
                    if(gameController.getCoinsCount(CoinType.BLACK)<coinsCount){
                        throw new InvalidMoveException("Invalid move, BLACK COINs count is less than the requested count");
                    }
                break;
                case "RED_STRIKE":
                    if(inputs.length>1){
                        int blackCoinsCount = Integer.parseInt(inputs[1]);
                        if(gameController.getCoinsCount(CoinType.BLACK) < blackCoinsCount){
                            throw new InvalidMoveException("Invalid move, BLACK COINs count is less than the requested count");
                        }
                    }
                    if(gameController.getCoinsCount(CoinType.RED)<1){
                        throw new InvalidMoveException("There is no RED coin present on the board");
                    }
                break;
                case "NONE":
                    if(gameController.isBoardEmpty()){
                        throw new InvalidMoveException("There is not a single coin present on the board");
                    }
                break;
                case "DEFUNCT_COIN":
                    if(gameController.isBoardEmpty()){
                        throw new InvalidMoveException("There is not a single coin present on the board");
                    }
                break;
            }
        }
    }    
}

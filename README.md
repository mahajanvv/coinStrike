# Problem Statement: Clean Strike

A new game in carrom-board called ​ Clean Strike is played by 2 players with multiple ​ turn ​ s. A turn has a player attempting to strike a coin with the striker. Players alternate in taking turns.

## The game is described as follows:
* There are 9 black coins, a red coin and a striker on the carrom-board
* Strike​ - When a player pockets a coin he/she wins a point (Ambiguous statement what if there are white coins as well and whether it is applied for red_coins also)
* Multi-strike - When a player pockets more than one coin he/she wins 2 points. All, but 2 coins, that were pocketed, get back on to the carrom-board.
**(Here I am considering the among all the coins that were pocketed only 2 will go out of play and remaining will get back to the board)**

* Red strike - When a player pockets red coin he/she wins 3 points. If other coins are pocketed along with red coin in the same turn, other coins get back on to the carrom-board
**(Here in sample I have considered RED_STRIKE <int> also only RED_STRIKE both commands as valid)**
* Striker strike​ - When a player pockets the striker he/she loses a point
* Defunct coin - When a coin is thrown out of the carrom-board, due to a strike, the player loses 2 points, and the coin goes out of play **(This is applicable for both the coins i.e RED, BLACK)**
* When a player does not pocket a coin for 3 successive turns he/she loses a point
**(If A player successively failed to pocket a coin for 5 time then will he lose 3 points or He loses single point every cycle of 3 failed attempts)**
**(I considered here Strike_strike, Defunct coin and also NONE move)**
* When a player ​ fouls 3 times (a ​ foul is a turn where a player loses, at least, 1 point), he/she loses an additional point
**(If A player fouled for 5 time then will he lose 3 points or He loses single point every cycle of 3 failed attempts)**
**(Do we need to reset the fouls count or it will be there throughout the end of match)**

A game is won by the first player to have won at least 5 points, in total, and, at least, 3
points more than the opponent

When the coins are exhausted on the board, if the highest scorer is not leading by, at
least, 3 points or does not have a minimum of 5 points, the game is considered a draw
Write a program that takes in the outcome of each turn as input and outputs the result of the
game as and when applicable along with necessary statistics that supports the result.

/*
* Problem 1: Escape Room
*
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;


/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{
  /* TO-DO: Process game commands from user input:
      right, left, up, down: move player size of move, m, if player try to go off grid or bump into wall, score decreases
      jump over 1 space: player cannot jump over walls
      pick up prize: score increases, if there is no prize, penalty
      help: display all possible commands
      end: reach the far right wall, score increase, game ends, if game ends without reaching far right wall, penalty
      replay: shows number of player steps and resets the board, player or another player can play the same board
       
      if player land on a trap, spring a trap to increase score: the program must first check if there is a trap, if none exists, penalty
      Note that you must adjust the score with any method that returns a score
      Optional: create a custom image for player - use the file player.png on disk
    */


  public static void main(String[] args)
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
   
    GameGUI game = new GameGUI();
    game.createBoard();


    // size of move
    int m = 60;
    // individual player moves
    int px = 0;
    int py = 0;
   
    int score = 0;


    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?", "det"};
 
    // set up game
    boolean play = true;
    //long startTime = System.currentTimeMillis();
    while (play)
    {
        // if (System.currentTimeMillis() - startTime >= 60000) {
        // System.out.println("Timer up!");
        // play = false;
    //}
      // get user command and validate
      System.out.print("Enter command:");
      String input = UserInput.getValidInput(validCommands);


        /* process user commands*/
      /* process user commands*/
if (input.equals("right") || input.equals("r")) {
    score += game.movePlayer(m, 0);
    game.decrementMultiplierMoves();
} else if (input.equals("left") || input.equals("l")) {
    score += game.movePlayer(-m, 0);
    game.decrementMultiplierMoves();
} else if (input.equals("up") || input.equals("u")) {
    score += game.movePlayer(0, -m);
    game.decrementMultiplierMoves();
} else if (input.equals("down") || input.equals("d")) {
    score += game.movePlayer(0, m);
    game.decrementMultiplierMoves();
} else if (input.equals("quit") || input.equals("q")) {
    play = false;
}
/* process jump commands */
else if (input.equals("jumpright") || input.equals("jr")) {
    // check if there's a wall in the way
    if (game.isWall(m, 0) == false) {
        score += game.movePlayer(2 * m, 0);
        game.decrementMultiplierMoves();
    } else {
        System.out.println("YOU CANNOT JUMP OVER A WALL.");
    }
} else if (input.equals("jumpleft") || input.equals("jl")) {
    if (game.isWall(-m, 0) == false) {
        score += game.movePlayer(-2 * m, 0);
        game.decrementMultiplierMoves();
    } else {
        System.out.println("YOU CANNOT JUMP OVER A WALL.");
    }
} else if (input.equals("jumpup") || input.equals("ju")) {
    if (game.isWall(0, -m) == false) {
        score += game.movePlayer(0, -2 * m);
        game.decrementMultiplierMoves();
    } else {
        System.out.println("YOU CANNOT JUMP OVER A WALL.");
    }
} else if (input.equals("jumpdown") || input.equals("jd")) {
    if (game.isWall(0, m) == false) {
        score += game.movePlayer(0, 2 * m);
        game.decrementMultiplierMoves();
    } else {
        System.out.println("YOU CANNOT JUMP OVER A WALL.");
    }}
//
    else if (input.equals("pickup") || input.equals("p")) {
    int pointsGained = game.pickupPrize();
    score += pointsGained * game.getMultiplier();
}
else if (input.equals("detrap") || input.equals("det")) {
    game.detrapTrap();
}
else if (input.equals("replay")) {
    score = 0;
    game.replay();
    System.out.println("Game replayed! Board and score have been reset.");
}
else if (input.equals("quit") || input.equals("q")) {
        play = false;
    }
else if (input.equals("help")) {
    System.out.println("Available commands: right (r), left (l), up (u), down (d)");
    System.out.println("jumpright (jr), jumpleft (jl), jumpup (ju), jumpdown (jd)");
    System.out.println("pickup (p), detrap (det), replay, quit (q)");
}

//traps
      if (game.isTrap(px, py)) {
    score -= game.springTrap(px, py);
}
      /* uncomment when user quits */
      // play = false;
    System.out.println("score=" + score);


    }


    score += game.endGame();


    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}


       

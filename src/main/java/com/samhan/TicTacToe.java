package com.samhan;

import com.samhan.player.HumanPlayer;
import com.samhan.ui.ConsoleDisplay;
import com.samhan.ui.ConsolePlayerInputOutput;

public class TicTacToe {
  public static void main(String args[]) {
    ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(System.in, System.out);
    HumanPlayer player1 = new HumanPlayer(Marker.X, console);
    HumanPlayer player2 = new HumanPlayer(Marker.O, console);
    ConsoleDisplay display = new ConsoleDisplay(System.out);
    Game game = new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(new Board())
            .display(display)
            .build();

    game.render();
    while (game.isRunning()) {
      game.playTurn();
      game.render();
    }
  }
}

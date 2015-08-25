package com.samhan;

public class TicTacToe {
  public static void main(String args[]) {
    ConsolePlayerInputOutput console = new ConsolePlayerInputOutput(System.in, System.out);
    HumanPlayer player1 = new HumanPlayer(Marker.X, console);
    HumanPlayer player2 = new HumanPlayer(Marker.O, console);
    ConsoleDisplay display = new ConsoleDisplay(System.out);
    Game game = new Game(player1, player2, new Board(), display);

    game.render();
    while (game.isRunning()) {
      game.playTurn();
      game.render();
    }
  }
}

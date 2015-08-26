package com.samhan;

import com.samhan.player.Player;
import com.samhan.ui.Display;

public class Game {
  private final Player player1;
  private final Player player2;
  private Board board;
  private final Display display;

  public Game(GameParams params) {
    this.player1 = params.player1;
    this.player2 = params.player2;
    this.board = params.board;
    this.display = params.display;
  }

  public void run() {
    render();
    while (isRunning()) {
      playTurn();
      render();
    }
  }

  public boolean isRunning() {
    return !board.isFinished();
  }

  public void render() {
    display.render(board, currentPlayer().getMarker());
  }

  public void playTurn() {
    int move = currentPlayer().nextMove(board);
    board = board.placeAt(move, currentPlayer().getMarker());
  }

  private Player currentPlayer() {
    if (board.movesMade() % 2 == 0) {
      return player1;
    }
    else {
      return player2;
    }
  }
}

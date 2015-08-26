package com.samhan;

import com.samhan.player.Player;
import com.samhan.ui.Display;

public class Game {
  private final Player player1;
  private final Player player2;
  private Board board;
  private final Display display;

  public Game(Builder builder) {
    this.player1 = builder.player1;
    this.player2 = builder.player2;
    this.board = builder.board;
    this.display = builder.display;
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

  public static class Builder {
    private Player player1;
    private Player player2;
    private Board board;
    private Display display;

    public Builder() {

    }

    public Builder player1(Player player1) {
      this.player1 = player1;
      return this;
    }

    public Builder player2(Player player2) {
      this.player2 = player2;
      return this;
    }

    public Builder board(Board board) {
      this.board = board;
      return this;
    }

    public Builder display(Display display) {
      this.display = display;
      return this;
    }

    public Game build() {
      return new Game(this);
    }
  }
}

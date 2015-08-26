package com.samhan;

import com.samhan.player.Player;
import com.samhan.ui.Display;

public class GameParams {
  public final Player player1;
  public final Player player2;
  public final Board board;
  public final Display display;

  public GameParams(Player player1, Player player2, Board board, Display display) {
    this.player1 = player1;
    this.player2 = player2;
    this.board = board;
    this.display = display;
  }
}

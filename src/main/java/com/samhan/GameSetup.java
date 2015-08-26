package com.samhan;

import com.samhan.player.EasyComputerPlayer;
import com.samhan.player.HardComputerPlayer;
import com.samhan.player.HumanPlayer;
import com.samhan.player.Player;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;

public class GameSetup {

  private final Display display;
  private final PlayerInputOutput playerInputOutput;

  public GameSetup(Display display, PlayerInputOutput playerInputOutput) {
    this.display = display;
    this.playerInputOutput = playerInputOutput;
  }

  public Game buildGame(PlayerType player1Type, PlayerType player2Type, BoardType boardType) {
    Player player1 = createPlayer(player1Type, Marker.X);
    Player player2 = createPlayer(player2Type, Marker.X);
    Board board = createBoard(boardType);

    return new Game.Builder()
            .player1(player1)
            .player2(player2)
            .board(board)
            .display(display)
            .build();
  }

  private Player createPlayer(PlayerType playerType, Marker marker) {
    Player player;
    switch (playerType) {
      case HUMAN:
        player = new HumanPlayer(marker, playerInputOutput);
        break;
      case EASY_COMPUTER:
        player = new EasyComputerPlayer(marker);
        break;
      case HARD_COMPUTER:
        player = new HardComputerPlayer(marker);
        break;
      default:
        player = new HumanPlayer(marker, playerInputOutput);
        break;
    }
    return player;
  }

  private Board createBoard(BoardType boardType) {
    Board board;
    switch (boardType) {
      case THREE:
        board = new Board(3);
        break;
      case FOUR:
        board = new Board(4);
        break;
      default:
        board = new Board(3);
        break;
    }
    return board;
  }


}

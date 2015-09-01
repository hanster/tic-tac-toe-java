package com.samhan;

import com.samhan.player.EasyComputer;
import com.samhan.player.HardComputer;
import com.samhan.player.Human;
import com.samhan.player.Player;
import com.samhan.ui.BoardSelection;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;
import com.samhan.ui.PlayerSelection;

public class GameSetup {

    public static final String PLAYER_ONE = "1";
    public static final String PLAYER_TWO = "2";
    private final Display display;
    private final PlayerInputOutput playerInputOutput;
    private final PlayerSelection playerSelection;
    private final BoardSelection boardSelection;

    public GameSetup(Display display, PlayerInputOutput playerInputOutput, PlayerSelection playerSelection, BoardSelection boardSelection) {
        this.display = display;
        this.playerInputOutput = playerInputOutput;
        this.playerSelection = playerSelection;
        this.boardSelection = boardSelection;
    }

    public GameParams buildGame() {
        Player player1 = createPlayer(playerSelection.selectType(PLAYER_ONE), Marker.X);
        Player player2 = createPlayer(playerSelection.selectType(PLAYER_TWO), Marker.O);
        Board board = createBoard(boardSelection.selectType());

        return new GameParams(player1, player2, board, display);
    }

    private Player createPlayer(PlayerType playerType, Marker marker) {
        Player player;
        switch (playerType) {
            case HUMAN:
                player = new Human(marker, playerInputOutput);
                break;
            case EASY_COMPUTER:
                player = new EasyComputer(marker);
                break;
            case HARD_COMPUTER:
                player = new HardComputer(marker);
                break;
            default:
                player = new Human(marker, playerInputOutput);
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

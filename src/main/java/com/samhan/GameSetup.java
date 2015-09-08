package com.samhan;

import com.samhan.player.EasyComputer;
import com.samhan.player.HardComputer;
import com.samhan.player.Human;
import com.samhan.player.Player;
import com.samhan.ui.*;

import static com.samhan.Marker.*;

public class GameSetup {

    private static final String PLAYER_ONE = "1";
    private static final String PLAYER_TWO = "2";
    private final Display display;
    private final PlayerInput playerInput;
    private final OptionMenu optionMenu;

    public GameSetup(Display display, PlayerInput playerInput, OptionMenu optionMenu) {
        this.display = display;
        this.playerInput = playerInput;
        this.optionMenu = optionMenu;
    }

    public GameParams buildGame() {
        Player player1 = createPlayer(optionMenu.getPlayerSelection(PLAYER_ONE), X);
        Player player2 = createPlayer(optionMenu.getPlayerSelection(PLAYER_TWO), O);
        Board board = createBoard(optionMenu.getBoardSelection());

        return new GameParams(player1, player2, board, display);
    }

    private Player createPlayer(PlayerType playerType, Marker marker) {
        Player player;
        switch (playerType) {
            case HUMAN:
                player = new Human(marker, playerInput);
                break;
            case EASY_COMPUTER:
                player = new EasyComputer(marker);
                break;
            case HARD_COMPUTER:
                player = new HardComputer(marker);
                break;
            default:
                player = new Human(marker, playerInput);
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

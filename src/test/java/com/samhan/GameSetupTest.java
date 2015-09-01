package com.samhan;

import com.samhan.Fakes.DisplaySpy;
import com.samhan.Fakes.PlayerInputOutputSpy;
import com.samhan.Fakes.PlayerSelectionStub;
import com.samhan.player.EasyComputer;
import com.samhan.player.HardComputer;
import com.samhan.player.Human;
import com.samhan.ui.BoardSelection;
import com.samhan.ui.Display;
import com.samhan.ui.PlayerInputOutput;
import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class GameSetupTest {
    private PlayerSelectionStub setUpPlayerSelectionStub(PlayerType player1, PlayerType player2) {
        LinkedList<PlayerType> playerTypes = new LinkedList<>();
        playerTypes.add(player1);
        playerTypes.add(player2);
        return new PlayerSelectionStub(playerTypes);
    }

    @Test
    public void getParamsForAGame() {
        Display display = new DisplaySpy();
        PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
        PlayerSelectionStub playerSelection = setUpPlayerSelectionStub(PlayerType.HUMAN, PlayerType.EASY_COMPUTER);
        BoardSelectionStub boardSelection = new BoardSelectionStub(BoardType.THREE);
        GameSetup gameSetup = new GameSetup(display, playerInputOutput, playerSelection, boardSelection);

        GameParams params = gameSetup.buildGame();

        assertThat(params.player1, instanceOf(Human.class));
        assertThat(params.player2, instanceOf(EasyComputer.class));
        assertThat(params.board.size(), is(3));
    }

    @Test
    public void getParamsForHardComputerAndFourBoard() {
        Display display = new DisplaySpy();
        PlayerInputOutput playerInputOutput = new PlayerInputOutputSpy();
        PlayerSelectionStub playerSelection = setUpPlayerSelectionStub(PlayerType.HUMAN, PlayerType.HARD_COMPUTER);
        BoardSelectionStub boardSelection = new BoardSelectionStub(BoardType.FOUR);
        GameSetup gameSetup = new GameSetup(display, playerInputOutput, playerSelection, boardSelection);

        GameParams params = gameSetup.buildGame();

        assertThat(params.player1, instanceOf(Human.class));
        assertThat(params.player2, instanceOf(HardComputer.class));
        assertThat(params.board.size(), is(4));
    }

    public class BoardSelectionStub implements BoardSelection {
        private final BoardType boardType;

        public BoardSelectionStub(BoardType boardType) {
            this.boardType = boardType;
        }

        @Override
        public BoardType selectType() {
            return boardType;
        }
    }
}
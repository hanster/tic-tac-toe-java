package com.samhan.player;


import com.samhan.Board;
import com.samhan.fakes.PlayerInputSpy;
import com.samhan.ui.PlayerInput;
import org.junit.Test;

import static com.samhan.Marker.X;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HumanTest {
    @Test
    public void hasAMarker() {
        PlayerInput input = new PlayerInputSpy();
        Player humanPlayer = new Human(X, input);

        assertThat(humanPlayer.getMarker(), is(X));
    }

    @Test
    public void usesInputToGetTheMove() {
        PlayerInputSpy input = new PlayerInputSpy();
        Player humanPlayer = new Human(X, input);

        humanPlayer.nextMove(new Board());

        assertThat(input.getMoveTimesCalled(), is(1));
    }

}

package com.samhan;

import java.util.Arrays;
import java.util.Optional;

public class BoardCreationHelper {
    public static final Optional<Marker> EMPTY = Optional.empty();
    public static final Optional<Marker> O = Optional.of(Marker.O);
    public static final Optional<Marker> X = Optional.of(Marker.X);

        public static Board createBoard(int boardSize, Optional<Marker>...elements) {
            return new Board(boardSize, Arrays.asList(elements));
        }
}

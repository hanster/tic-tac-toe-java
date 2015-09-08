package com.samhan.ui;

import com.samhan.BoardType;
import com.samhan.PlayerType;

import java.util.Map;

public interface OptionMenu {
    String getSelection(String prompt, Map<String, String> options);

    BoardType getBoardSelection();

    PlayerType getPlayerSelection(String player);
}

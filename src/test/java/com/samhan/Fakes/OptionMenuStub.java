package com.samhan.Fakes;

import com.samhan.ui.OptionMenu;

import java.util.Map;

public class OptionMenuStub implements OptionMenu {
    private final String selection;

    public OptionMenuStub(String selection) {
        this.selection = selection;
    }

    @Override
    public String getSelection(String prompt, Map<String, String> options) {
        return selection;
    }
}

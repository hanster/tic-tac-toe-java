package com.samhan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserInput {

    private final BufferedReader input;

    public UserInput(InputStream inputStream){
        this.input = new BufferedReader(new InputStreamReader(inputStream));
    }

    public String readInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

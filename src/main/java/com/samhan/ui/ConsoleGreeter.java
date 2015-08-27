package com.samhan.ui;

import java.io.PrintStream;

public class ConsoleGreeter {
  private static final String ANSI_CLS = "\u001b[2J";
  private static final String ANSI_HOME = "\u001b[H";
  private static final String WELCOME_MESSAGE = "Welcome to TicTcToe\n\nTime to set up!\n";
  private final PrintStream output;

  public ConsoleGreeter(PrintStream output) {
    this.output = output;
  }

  public void greet() {
    output.print(ANSI_CLS + ANSI_HOME);
    output.println(WELCOME_MESSAGE);
  }
}

package com.samhan.ui;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleOptionMenuTest {
  private ByteArrayOutputStream output;
  private ConsoleOptionMenu console;
  private String prompt;
  private Map<String, String> options;


  private void setUpQueuedConsoleInput (String[] listOfInputs) {
    String totalInputs = "";
    for (String input : listOfInputs) {
      totalInputs = totalInputs + input + "\n";
    }
    output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    console = new ConsoleOptionMenu(new ByteArrayInputStream(totalInputs.getBytes()), printStream);
    prompt = "Enter an option:";
    options = new LinkedHashMap<>();
    options.put("1", "the first option");
    options.put("2", "the second option");
  }

  @Test
  public void displayEnterPrompt() {
    setUpQueuedConsoleInput(new String[] {"1"});

    console.getSelection(prompt, options);

    assertThat(output.toString(), containsString("Enter an option"));
  }

  @Test
  public void displayTheOptions() {
    setUpQueuedConsoleInput(new String[]{"1"});

    console.getSelection(prompt, options);

    assertThat(output.toString(), containsString("1 - the first option"));
    assertThat(output.toString(), containsString("2 - the second option"));
  }

  @Test
  public void getsASelection() {
    setUpQueuedConsoleInput(new String[] {"1"});

    String selection = console.getSelection(prompt, options);

    assertThat(selection, is("1"));
  }

  @Test
  public void keepsLoopingUntilAnSelectionFromTheOptions() {
    setUpQueuedConsoleInput(new String[] {"asdf", "1"});

    String selection = console.getSelection(prompt, options);

    assertThat(selection, is("1"));
  }

  @Test
  public void displayAnErrorMessageForInvalidInput() {
    setUpQueuedConsoleInput(new String[] {"asdf", "1"});

    String selection = console.getSelection(prompt, options);

    assertThat(output.toString(), containsString("Invalid Selection"));
  }
}

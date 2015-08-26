package com.samhan.ui;

import java.util.Map;

public interface OptionMenu {
  String getSelection(String prompt, Map<String, String> options);
}

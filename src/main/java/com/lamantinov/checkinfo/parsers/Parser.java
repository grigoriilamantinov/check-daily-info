package com.lamantinov.checkinfo.parsers;

import java.util.Map;

public interface Parser {

    String parsTemperature();

    Map<String, String> parsStonks();

    String[] parsNews();

    boolean isTemperature(String[] stringArray);
}

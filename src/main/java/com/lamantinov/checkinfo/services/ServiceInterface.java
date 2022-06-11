package com.lamantinov.checkinfo.services;

import java.util.Map;

public interface ServiceInterface {
    String getTemperature();

    Map<String, String> getStonks();

    String[] getNews();
}

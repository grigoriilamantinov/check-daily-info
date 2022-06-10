package com.lamantinov.checkinfo.services;

import com.lamantinov.checkinfo.parsers.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class YandexService implements ServiceInterface {
    final Parser parser;

    public YandexService(
        @Autowired Parser parser
    ) {
        this.parser = parser;
    }

    @Override
    public String getTemperature() {
        return parser.parsTemperature();
    }

    @Override
    public Map<String, String> getStonks() {
        return parser.parsStonks();
    }

    @Override
    public String[] getNews() {
        return parser.parsNews();
    }
}

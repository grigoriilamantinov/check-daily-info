package com.lamantinov.checkinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {
    final Parser parser;

    public MainService(
        @Autowired Parser parser
    ) {
        this.parser = parser;
    }

    public MainDTO getTemperature() {
        return parser.parsTemperature();
    }

    public MainDTO getDollar() {
        return parser.parsDollar();
    }

    public List<String> getNews() {
        return parser.parsNews().getNews();
    }
}

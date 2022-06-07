package com.lamantinov.checkinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
    final Parser parser;

    public MainService(
        @Autowired Parser parser
    ) {
        this.parser = parser;
    }

    public String getTemperature() {
        return parser.parsTemperature();
    }
}

package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {

    String response = HTMLKeeper.getHTMLCode();
    MainDTO mainDTO = new MainDTO();

//    String URL = "https://yandex.ru/";
//    RestTemplate restTemplate = new RestTemplate();
//    String response = restTemplate.getForObject(URL, String.class);

    public MainDTO parsTemperature() {
        String openTag = "<div class='weather__temp'>";
        String closeTag = "</div>";
        String result = "no result";


        String[] stringArray = StringUtils.substringsBetween(response, openTag, closeTag);
        for (String s : stringArray){
            result = s;
        }
        mainDTO.setTemperature(result);
        return mainDTO;
    }

    public MainDTO parsDollar() {
        String openTag = "<div class='stocks__item-value'>";
        String closeTag = "</div>";

        String[] stringArray = StringUtils.substringsBetween(response, openTag, closeTag);
        mainDTO.setDollar(stringArray[0]);
        mainDTO.setEuro(stringArray[1]);
        mainDTO.setOil(stringArray[4]);
        return mainDTO;
    }

    public MainDTO parsNews() {
        String openTag = "<a aria-label=";
        String closeTag = "target=";

        String[] stringArray = StringUtils.substringsBetween(response, openTag, closeTag);
        List<String> news = new ArrayList<>();
        for (int i = 2; i < 7; i++) {
            news.add(stringArray[i]);
        }

        mainDTO.setNews(news);
        return mainDTO;
    }
}
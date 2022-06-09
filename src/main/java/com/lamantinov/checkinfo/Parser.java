package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Map<String, String> parsStonks() {
        String bigOpenTag = "<div class='stocks__item-left'>";
        String bigCloseTag = "<div class='stocks__item-delta'>";


        Pattern firstPattern = Pattern.compile(".{3}.ЦБ");
        Pattern secondPattern = Pattern.compile("Нефть");
        Pattern valuePattern = Pattern.compile("\\d+,\\d\\d..");

        Matcher firstMatcher;
        Matcher secondMatcher;
        Matcher valueMatcher;

        String[] stringArray = StringUtils.substringsBetween(response, bigOpenTag, bigCloseTag);
        Map<String, String> stonksValues = new TreeMap<>();

        for (String s : stringArray){

            firstMatcher = firstPattern.matcher(s);
            secondMatcher = secondPattern.matcher(s);
            valueMatcher = valuePattern.matcher(s);

            if (firstMatcher.find() && valueMatcher.find()) {
                stonksValues.put(firstMatcher.group(), valueMatcher.group());
            } else if (secondMatcher.find() && valueMatcher.find()) {
                stonksValues.put(secondMatcher.group(), valueMatcher.group());
            }
        }

        return stonksValues;
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
package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Parser {

    private static String response = HTMLKeeper.getHTMLCode();
    private static String newsOpenTag = "news__item-content'>";
    private static String newsCloseTag = "</span>";

//    String URL = "https://yandex.ru/";
//    RestTemplate restTemplate = new RestTemplate();
//    String response = restTemplate.getForObject(URL, String.class);

    public TemperatureDTO parsTemperature() {
        final String openTag = "<div class='weather__temp'>";
        final String closeTag = "</div>";
        String result = "no result";

        String[] stringArray = StringUtils.substringsBetween(response, openTag, closeTag);
        for (String s : stringArray){
            result = s;
        }

        return new TemperatureDTO(result);
    }

    public Map<String, String> parsStonks() {
        String bigOpenTag = "<div class='stocks__item-left'>";
        String bigCloseTag = "<div class='stocks__item-delta'>";


        Pattern firstPattern = Pattern.compile(".{3}.ЦБ");
        Pattern valuePattern = Pattern.compile("\\d+,\\d\\d..");
        Pattern secondPattern = Pattern.compile("Нефть");

        Matcher currencyMatcher;
        Matcher oilMatcher;
        Matcher valueMatcher;

        String[] stringArray = StringUtils.substringsBetween(response, bigOpenTag, bigCloseTag);
        Map<String, String> stonksValues = new TreeMap<>();

        for (String stonks : stringArray){

            currencyMatcher = firstPattern.matcher(stonks);
            oilMatcher = secondPattern.matcher(stonks);
            valueMatcher = valuePattern.matcher(stonks);

            if (currencyMatcher.find() && valueMatcher.find()) {
                stonksValues.put(currencyMatcher.group(), valueMatcher.group());
            } else if (oilMatcher.find() && valueMatcher.find()) {
                stonksValues.put(oilMatcher.group(), valueMatcher.group());
            }
        }
        return stonksValues;
    }

    public String[] parsNews() {
        return StringUtils.substringsBetween(response, newsOpenTag, newsCloseTag);
    }
}
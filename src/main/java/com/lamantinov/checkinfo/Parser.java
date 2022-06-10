package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Parser {
    private final static String newsOpenTag = "news__item-content'>";
    private final static String newsCloseTag = "</span>";
    private final static String temperatureOpenTag = "<div class='weather__temp'>";
    private final static String temperatureCloseTag = "</div>";
    private final static String stonksOpenTag = "<div class='stocks__item-left'>";
    private final static String stonksCloseTag = "<div class='stocks__item-delta'>";
    private final static Pattern currencyPattern = Pattern.compile(".{3}.ЦБ");
    private final static Pattern oilPattern = Pattern.compile("\\d+,\\d\\d..");
    private final static Pattern secondPattern = Pattern.compile("Нефть");
    private final static String response = HTMLKeeper.getHTMLCode();

//    Code below needed for real http request.
//    String URL = "https://yandex.ru/";
//    RestTemplate restTemplate = new RestTemplate();
//    String response = restTemplate.getForObject(URL, String.class);

    public String parsTemperature() {
        String[] stringArray = StringUtils.substringsBetween(response, temperatureOpenTag, temperatureCloseTag);
        if (stringArray.length == 1) {
            return stringArray[0];
        } else {
            return "Sorry, guys from yandex change tags, and"
                   + "here's what now instead of temperature: "
                   + stringArray[0];
        }
    }

    public Map<String, String> parsStonks() {
        Matcher currencyMatcher;
        Matcher oilMatcher;
        Matcher valueMatcher;

        String[] stringArray = StringUtils.substringsBetween(response, stonksOpenTag, stonksCloseTag);
        Map<String, String> stonksValues = new TreeMap<>();

        for (String stonks : stringArray){

            currencyMatcher = currencyPattern.matcher(stonks);
            oilMatcher = secondPattern.matcher(stonks);
            valueMatcher = oilPattern.matcher(stonks);

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
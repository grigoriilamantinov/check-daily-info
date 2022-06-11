package com.lamantinov.checkinfo.parsers;

import com.lamantinov.checkinfo.htmlKeepers.YandexHTMLKeeper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParserImpl implements Parser {
    private final static String newsOpenTag = "news__item-content'>";
    private final static String newsCloseTag = "</span>";
    private final static String temperatureOpenTag = "<div class='weather__temp'>";
    private final static String temperatureCloseTag = "</div>";
    private final static String stonksOpenTag = "<div class='stocks__item-left'>";
    private final static String stonksCloseTag = "<div class='stocks__item-delta'>";

    private final static Pattern currencyPattern = Pattern.compile("[A-Z]{3}.ЦБ");
    private final static Pattern valuePattern = Pattern.compile("\\d+,\\d\\d..");
    private final static Pattern oilPattern = Pattern.compile("Нефть");
    private final static Pattern temperaturePattern = Pattern.compile("[+-]\\d+[°]");

//    private final static String response = YandexHTMLKeeper.getHTMLCode();

//    Code below needed for real http request.
    private final String URL = "https://yandex.ru/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final String response = restTemplate.getForObject(URL, String.class);

    @Override
    public String parsTemperature() {
        final String[] stringArray = StringUtils.substringsBetween(response, temperatureOpenTag, temperatureCloseTag);
        if (isTemperature(stringArray)) {
            return stringArray[0];
        } else {
            return "Sorry, guys from yandex change tags, and "
                   + "here's what now instead of temperature: \n"
                   + stringArray[0];
        }
    }

    @Override
    public Map<String, String> parsStonks() {
        Matcher currencyMatcher;
        Matcher oilMatcher;
        Matcher valueMatcher;

        final String[] stringArray = StringUtils.substringsBetween(response, stonksOpenTag, stonksCloseTag);
        final Map<String, String> stonksValues = new LinkedHashMap<>();

        for (String stonks : Objects.requireNonNull(stringArray)){
            currencyMatcher = currencyPattern.matcher(stonks);
            oilMatcher = oilPattern.matcher(stonks);
            valueMatcher = valuePattern.matcher(stonks);

            if (currencyMatcher.find() && valueMatcher.find()) {
                stonksValues.put(currencyMatcher.group(), valueMatcher.group());
            } else if (oilMatcher.find() && valueMatcher.find()) {
                stonksValues.put(oilMatcher.group(), valueMatcher.group());
            }
        }
        return stonksValues;
    }

    @Override
    public String[] parsNews() {
        return StringUtils.substringsBetween(response, newsOpenTag, newsCloseTag);
    }

    @Override
    public boolean isTemperature(final String[] stringArray) {
        final Matcher temperatureMatcher = temperaturePattern.matcher(stringArray[0]);
        return stringArray.length == 1 && temperatureMatcher.find();
    }

}
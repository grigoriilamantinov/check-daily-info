package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Parser {
    String URL = "https://yandex.ru/";
    RestTemplate restTemplate = new RestTemplate();

    public String parsTemperature() {

//        String response = restTemplate.getForObject(URL, String.class);
        String response = "<div class='weather__temp'>+19</div>";
        String result = "no result";

        String[] stringArray = StringUtils.substringsBetween(response, "<div class='weather__temp'>", "</div>");
        for (String s : stringArray){
            result = s;
        }
        return result;
    }
}

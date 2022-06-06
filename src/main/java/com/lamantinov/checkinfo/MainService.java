package com.lamantinov.checkinfo;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class MainService {
    String URL = "https://yandex.ru/";

    void getResponse() {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(URL, String.class);
//        String response = ("\t\t\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\" rel=\"noopener\" data-statlog=\"weather.grade\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\tdata-statlog-showed=\"1\">\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class=\"weather__icon weather__icon_skc_n\" title=\"ясно\"></div>\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t<div class='weather__temp'>+9°</div>\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t</a>\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t<div class='weather__forecast'><a aria-label=\"утром +15 °C\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"home-link2 weather__forecast-link home-link2_color_inherit home-link2_hover_red\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t\thref=\"https://yandex.ru/pogoda/?utm_medium=web&utm_campaign=informer&utm_source=home&utm_content=main_informer&utm_term=current_day_part\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t\ttarget=\"_blank\" rel=\"noopener\" data-statlog=\"weather.grade_soon\"\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tdata-statlog-showed=\"1\">утром&#160;+15°</a><br><a\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\t\t\taria-label=\"днём +20 °C\"");

        String[] stringArray = StringUtils.substringsBetween(response, "<div class='weather__temp'>", "</div>");
        for (String s : stringArray) {
            System.out.println("Output: " + s);
        }
    }

    void getSomeTestFromSoup() {
        Document doc = null;
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String title = doc.title();
        System.out.println("Title : " + title);
    }
}

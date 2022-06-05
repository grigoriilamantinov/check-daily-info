package com.lamantinov.checkinfo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MyService {

    void getResponse() {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "https://yandex.ru/";
        String response = restTemplate.getForObject(URL, String.class);
        System.out.println(response);
    }
}

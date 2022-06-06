package com.lamantinov.checkinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text")
public class MainController {
    final MainService myService;

    @Autowired
    public MainController(MainService myService) {
        this.myService = myService;
    }

    @GetMapping()
    public String getMainPage() {
        myService.getResponse();
        return "main-page.html";
    }
}

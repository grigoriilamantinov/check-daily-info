package com.lamantinov.checkinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text")
public class Controller1 {
    final MyService myService;

    @Autowired
    public Controller1(MyService myService) {
        this.myService = myService;
    }

    @GetMapping()
    public String checkText() {
        myService.getResponse();
        return "text1.html";
    }
}

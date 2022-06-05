package com.lamantinov.checkinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text")
public class Controller1 {

    @GetMapping()
    public String checkText() {
        return "text1.html";
    }
}

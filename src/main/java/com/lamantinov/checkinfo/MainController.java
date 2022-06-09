package com.lamantinov.checkinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping ("/main")
    public String showMainPage() {
        return "main-page";
    }

    @GetMapping ("/main/temperature")
    public String showTemperature(Model model) {
        model.addAttribute("temperature", mainService.getTemperature());
        return "temperature";
    }

    @GetMapping ("/main/stonks")
    public String showStonks(Model model) {
        model.addAttribute("stonks", mainService.getStonks());
        return "stonks";
    }

    @GetMapping ("/main/news")
    public String showNews(Model model) {
        model.addAttribute("news", mainService.getNews());
        return "news";
    }
}

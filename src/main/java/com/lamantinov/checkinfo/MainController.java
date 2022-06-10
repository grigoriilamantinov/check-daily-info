package com.lamantinov.checkinfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    final MainService mainService;

    public MainController(final MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping ()
    public String showMainPage() {
        return "main-page";
    }

    @GetMapping ("/temperature")
    public String showTemperature(final Model model) {
        model.addAttribute("temperature", mainService.getTemperature());
        return "temperature";
    }

    @GetMapping ("/stonks")
    public String showStonks(final Model model) {
        model.addAttribute("stonks", mainService.getStonks());
        return "stonks";
    }

    @GetMapping ("/news")
    public String showNews(final Model model) {
        model.addAttribute("news", mainService.getNews());
        return "news";
    }
}

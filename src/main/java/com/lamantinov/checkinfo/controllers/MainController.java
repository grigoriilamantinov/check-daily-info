package com.lamantinov.checkinfo.controllers;

import com.lamantinov.checkinfo.services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    final ServiceInterface serviceInterface;

    public MainController(
        @Autowired final ServiceInterface serviceInterface
    ) {
        this.serviceInterface = serviceInterface;
    }

    @GetMapping()
    public String showMainPage() {
        return "main-page";
    }

    @GetMapping("/temperature")
    public String showTemperature(final Model model) {
        model.addAttribute("temperature", serviceInterface.getTemperature());
        return "temperature";
    }

    @GetMapping("/stonks")
    public String showStonks(final Model model) {
        model.addAttribute("stonksList", serviceInterface.getStonks());
        return "stonks";
    }

    @GetMapping("/news")
    public String showNews(final Model model) {
        model.addAttribute("newsList", serviceInterface.getNews());
        return "news";
    }
}

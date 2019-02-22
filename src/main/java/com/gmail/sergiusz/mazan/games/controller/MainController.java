package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private GameService gameService;

    @Autowired
    private void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/search")
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("games", gameService.findGames(pattern));
        return "search";
    }
}

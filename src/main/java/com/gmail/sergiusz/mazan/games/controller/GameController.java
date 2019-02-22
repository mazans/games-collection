package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/addGame")
public class GameController {

    private GameService gameService;

    @Autowired
    private void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String addGameGetMethod(Model model) {
        model.addAttribute("publishers", gameService.getAllPublishers());
        model.addAttribute("newGame", new Game());
        return "add_game_get";
    }

    @PostMapping
    public String addGamePostMethod(@Valid @ModelAttribute("newGame") Game newGame,
                                    BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("newGame", newGame);
            return "add_game_get";
        }
        else {
            gameService.addGame(newGame);
            return "redirect:/";
        }
    }
}

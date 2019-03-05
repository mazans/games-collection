package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.AddedGame;
import com.gmail.sergiusz.mazan.games.model.Game;
import com.gmail.sergiusz.mazan.games.service.GameService;
import com.gmail.sergiusz.mazan.games.service.ImageSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/addGame")
public class GameController {

    private GameService gameService;
    private ImageSaverService imageSaverService;

    @Autowired
    private void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @Autowired
    private void setImageSaverService(ImageSaverService imageSaverService) {
        this.imageSaverService = imageSaverService;
    }

    @GetMapping
    public String addGameGetMethod(Model model) {
        model.addAttribute("publishers", gameService.getAllPublishers());
        model.addAttribute("newGame", new AddedGame());
        return "add_game_get";
    }

    @PostMapping
    public String addGamePostMethod(@Valid @ModelAttribute("newGame") AddedGame newGame, BindingResult bindingResult,
                                    Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("newGame", newGame);
            return "add_game_get";
        }
        else {
            Game game = new Game(newGame.getTitle(), newGame.getDateOfPublication(), newGame.getPublisher());
            int key = gameService.addGame(game);
            imageSaverService.saveImage(newGame.getCover(), key);
            return "redirect:/";
        }
    }
}

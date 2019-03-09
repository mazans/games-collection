package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.Publisher;
import com.gmail.sergiusz.mazan.games.service.AddPublisherResult;
import com.gmail.sergiusz.mazan.games.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/addPublisher")
public class PublisherController {

    private GameService gameService;

    @Autowired
    private void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String addPublisherGetMethod(Model model) {
        model.addAttribute("newPublisher", new Publisher());
        return "add_publisher_get";
    }

    @PostMapping
    public String addPublisherPostMethod(@Valid @ModelAttribute("newPublisher") Publisher newPublisher,
                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "add_publisher_get";
        else {
            AddPublisherResult result = gameService.addPublisher(newPublisher);
            if(result.equals(AddPublisherResult.OK))
                return "add_publisher_confirmation";
            else {
                bindingResult.addError(new FieldError("newPublisher", "name",
                        "Publisher with given name already exists"));
                return "add_publisher_get";
            }
        }
    }
}

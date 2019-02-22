package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.GameUser;
import com.gmail.sergiusz.mazan.games.service.AddGameToUserResult;
import com.gmail.sergiusz.mazan.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    private void setCommonService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String user() {
        return "user";
    }

    @GetMapping("/add/{gameId}")
    public String addGame(@SessionAttribute GameUser user, @PathVariable int gameId,
                          RedirectAttributes redirectAttributes) {
        AddGameToUserResult result = userService.addGameToUser(user, gameId);
        redirectAttributes.addFlashAttribute("addResult", result);
        return "redirect:/user";
    }
}
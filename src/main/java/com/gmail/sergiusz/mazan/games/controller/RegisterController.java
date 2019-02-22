package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.UserRegistration;
import com.gmail.sergiusz.mazan.games.service.RegistrationResult;
import com.gmail.sergiusz.mazan.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class RegisterController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("newUser", new UserRegistration());
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("newUser") UserRegistration newUser,
                               BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())
            return "register";
        else {
            Set<RegistrationResult> result = userService.registerUser(newUser);
            if(result.contains(RegistrationResult.OK))
                return "registration_confirmation";
            else {
                model.addAttribute("registrationError", result);
                return "register";
            }
        }
    }
}

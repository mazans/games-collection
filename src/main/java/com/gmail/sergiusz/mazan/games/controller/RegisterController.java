package com.gmail.sergiusz.mazan.games.controller;

import com.gmail.sergiusz.mazan.games.model.UserRegistration;
import com.gmail.sergiusz.mazan.games.service.RegistrationResult;
import com.gmail.sergiusz.mazan.games.service.UserService;
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
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegister(Model model) {
        model.addAttribute("newUser", new UserRegistration());
        return "register";
    }

    @PostMapping
    public String postRegister(@Valid @ModelAttribute("newUser") UserRegistration newUser,
                               BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "register";
        else {
            Set<RegistrationResult> registrationResults = userService.registerUser(newUser);
            if(registrationResults.contains(RegistrationResult.OK))
                return "registration_confirmation";
            else {
                addRegistrationErrors(registrationResults, bindingResult);
                return "register";
            }
        }
    }

    private void addRegistrationErrors(Set<RegistrationResult> registrationResults, BindingResult bindingResult) {
        if(registrationResults.contains(RegistrationResult.DUPLICATE_EMAIL))
            bindingResult.addError(new FieldError("newUser", "email",
                    "validation.registration.email.used"));
        if(registrationResults.contains(RegistrationResult.DUPLICATE_USERNAME))
            bindingResult.addError(new FieldError("newUser", "username",
                    "validation.registration.username.used"));
    }
}

package com.gmail.sergiusz.mazan.games.model;

import com.gmail.sergiusz.mazan.games.validation.MatchingPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@MatchingPassword(message = "validation.registration.password.match")
public class UserRegistration {

    @NotNull(message = "validation.registration.username.required")
    @NotEmpty(message = "validation.registration.username.required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,50}$",message = "validation.registration.username.pattern")
    private String username;

    @NotNull(message = "validation.registration.email.required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "validation.registration.email.pattern")
    private String email;


    @NotNull(message = "validation.registration.password.required")
    @NotEmpty(message = "validation.registration.password.required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{5,50}$", message="validation.registration.password.pattern")
    private String password;
    private String confirmPassword;

    public UserRegistration() {}

    public UserRegistration(String username, String email, String password, String confirmPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

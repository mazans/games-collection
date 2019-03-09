package com.gmail.sergiusz.mazan.games.model;

import com.gmail.sergiusz.mazan.games.validation.MatchingPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@MatchingPassword(message = "Passwords must match")
public class UserRegistration {

    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,50}$",
            message = "Username must contain 5 to 50 characters. Allowed characters: letters, numbers, _")
    private String username;

    @NotNull(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "Email address is invalid")
    private String email;


    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]{5,50}$",
            message="Password must contain 5 to 50 characters. Allowed charaters: letters, numbers, _!#$%&’*+/=?`{|}~^.-")
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

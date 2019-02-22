package com.gmail.sergiusz.mazan.games.config;

import com.gmail.sergiusz.mazan.games.model.GameUser;
import com.gmail.sergiusz.mazan.games.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GameApplicationAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private UserService userService;

    @Autowired
    private void setCommonService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        GameUser user = userService.getUser(authentication.getName());
        httpServletRequest.getSession().setAttribute("user", user);
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
    }
}

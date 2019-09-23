package com.piotrek.apps.orderHaircutApp.config;

import com.piotrek.apps.orderHaircutApp.entity.User;
import com.piotrek.apps.orderHaircutApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String userName = authentication.getName();

        User theUser = userService.findByUserName(userName);

        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);

        if (theUser.getRole().get(0).getRoleName().equals("ROLE_OWNER")) {
            response.sendRedirect("/owner/showOwnerPanel");
        } else {
            response.sendRedirect("/");
        }
    }

}

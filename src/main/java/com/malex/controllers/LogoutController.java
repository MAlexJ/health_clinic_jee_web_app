package com.malex.controllers;

import com.malex.entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.malex.controllers.AbstractController.LOGOUT_URL;

@WebServlet(LOGOUT_URL)
public class LogoutController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User emptyUser = new User();
        setHttpSessionAttributes(req, emptyUser);
        sendRedirect(req, resp, LOGIN_URL);
    }

}

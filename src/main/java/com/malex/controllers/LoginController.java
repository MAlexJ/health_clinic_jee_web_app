package com.malex.controllers;

import com.malex.entities.User;
import com.malex.services.UserService;
import com.malex.services.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static com.malex.controllers.AbstractController.LOGIN_URL;

@WebServlet(LOGIN_URL)
public class LoginController extends AbstractController {


    private static final String USERNAME_PARAMETER = "username";
    private static final String PWD_PARAMETER = "password";

    private final UserService userServices = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        forward(req, resp, LOGIN_PAGE);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // get from form by name - username
        String username = req.getParameter(USERNAME_PARAMETER);

        // get from form by name - password
        String password = req.getParameter(PWD_PARAMETER);

        if (isEmpty(username) || isEmpty(password)) {
            String errorMessage = "User credential invalid!";
            req.setAttribute("error", errorMessage);
            forward(req, resp, LOGIN_PAGE);
            return;
        }

        User user = userServices.getUserByCredentials(username, password);
        if (Objects.isNull(user)) {
            String errorMessage = "User not found!";
            req.setAttribute("error", errorMessage);
            forward(req, resp, LOGIN_PAGE);
            return;
        }

        // set username and role to session
        setHttpSessionAttributes(req, user);

        // route URL
        switch (user.getRole()) {
            // redirect to admin page
            case ADMIN: {
                sendRedirect(req, resp, ADMIN_URL);
                break;
            }
            // redirect to home page
            case CLIENT: {
                sendRedirect(req, resp, HOME_URL);
                break;
            }

            // redirect to home page
            default:
                sendRedirect(req, resp, HOME_URL);
        }
    }

    private boolean isEmpty(String val) {
        return val == null || val.trim().equals("");
    }
}
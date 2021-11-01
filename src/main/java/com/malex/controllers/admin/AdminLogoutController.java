package com.malex.controllers.admin;

import com.malex.controllers.AbstractController;
import com.malex.entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.malex.controllers.AbstractController.ADMIN_LOGOUT_URL;

@WebServlet(ADMIN_LOGOUT_URL)
public class AdminLogoutController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User emptyUser = new User();
        setHttpSessionAttributes(req, emptyUser);
        sendRedirect(req, resp, LOGIN_URL);
    }
}

package com.malex.controllers.admin;

import com.malex.controllers.AbstractController;
import com.malex.entities.Role;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.malex.controllers.AbstractController.ADMIN_URL;
import static com.malex.entities.Role.ADMIN;
import static com.malex.entities.Role.UNDEFINED;

@WebServlet(ADMIN_URL)
public class AdminPageController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (isRegularUser(req)) {
            sendRedirect(req, resp, HOME_URL);
            return;
        }
        forward(req, resp, ADMIN_PAGE);
    }

    private boolean isRegularUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        Role role = Optional.ofNullable(session)
                .map(s -> session.getAttribute(SESSION_USER_ROLE))
                .map(Role.class::cast)
                .orElse(UNDEFINED);
        return role != ADMIN;
    }
}
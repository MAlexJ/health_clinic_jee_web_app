package com.malex.controllers.filters;

import com.malex.entities.Role;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.malex.controllers.AbstractController.*;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        // todo for test only!!!!!
        response.setHeader("Cache-Control", "no-store");

        // allow css style
        if (request.getRequestURI().endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        // allow js scripts
        if (request.getRequestURI().endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        // allow icon images
        if (request.getRequestURI().endsWith(".ico")) {
            chain.doFilter(request, response);
            return;
        }

        // Returns current HttpSession associated with this request or, if there is no current session
        // is true, returns new session
        // If create is false and request has no valid HttpSession, this method returns null
        HttpSession session = request.getSession(false);

        // Check if there is an existing session or an active user
        boolean isExistingSession = session != null && session.getAttribute(SESSION_USER_NAME) != null;
        if (isExistingSession) {
            Role userRole = Optional.ofNullable(session.getAttribute(SESSION_USER_ROLE))
                    .map(Role.class::cast)
                    .orElseThrow(() -> new IllegalStateException("User role not found in HTTP session"));
            switch (userRole) {
                case ADMIN: {
                    String servletPath = request.getServletPath();
                    if (servletPath.contains(ADMIN_URL)) {
                        chain.doFilter(request, response);
                    } else {
                        // TODO send REDIRECT instance of FROWARD
                        RequestDispatcher dispatcher = request.getRequestDispatcher(ADMIN_URL);
                        dispatcher.forward(request, response);
                    }
                    break;
                }
                case CLIENT:
                case DOCTOR: {
                    chain.doFilter(request, response);
                    break;
                }
                default:
                    chain.doFilter(request, response);
            }
        } else {
            // if user is not logged in ,then forwards to login page
            // TODO send REDIRECT instance of FROWARD
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN_URL);
            dispatcher.forward(request, response);
        }
    }
}

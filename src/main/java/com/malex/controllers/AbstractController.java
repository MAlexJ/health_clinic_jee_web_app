package com.malex.controllers;

import com.google.gson.Gson;
import com.malex.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class AbstractController extends HttpServlet {

    // login
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_PAGE = "/pages/login.jsp";

    // logout
    public static final String LOGOUT_URL = "/logout";

    // home page
    public static final String HOME_URL = "/";
    public static final String HOME_PAGE = "/index.jsp";

    // appointments
    public static final String APPOINTMENTS_PAGE = "/pages/appointments.jsp";

    // clients
    public static final String CLIENTS_PAGE = "/pages/clients.jsp";

    // doctors
    public static final String DOCTORS_PAGE = "/pages/doctors.jsp";

    // admin
    public static final String ADMIN_PAGE = "/admin.jsp";
    public static final String ADMIN_URL = "/admin";

    // admin logout
    public static final String ADMIN_LOGOUT_URL = "/admin/logout";

    // session attributes
    public static final String SESSION_USER_NAME = "user";
    public static final String SESSION_USER_ROLE = "role";

    private final Gson jsonMapper = new Gson();

    protected String mapToString(Object ob){
        return jsonMapper.toJson(ob);
    }


    /**
     * Forwards a request from servlet to another resource (servlet, JSP file, or HTML file) on the server.
     * This method allows one servlet to do preliminary processing of request
     * and another resource to generate the response.
     */
    protected void forward(HttpServletRequest req, HttpServletResponse resp, String path) {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    protected void sendRedirect(HttpServletRequest req, HttpServletResponse resp, String path) {
        try {
            String appUrl = req.getContextPath();
            resp.sendRedirect(appUrl.concat(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void setHttpSessionAttributes(HttpServletRequest req, User user) {
        // set user to session
        HttpSession session = req.getSession(false);
        session.setAttribute(SESSION_USER_NAME, user.getFirstName());
        session.setAttribute(SESSION_USER_ROLE, user.getRole());
    }

}

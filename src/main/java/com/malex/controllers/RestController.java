package com.malex.controllers;

import com.google.gson.Gson;
import com.malex.entities.Role;
import com.malex.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@WebServlet("/rest/users")
public class RestController extends AbstractController {

    private static final Map<Integer, User> map = new HashMap<>();

    static {
        User test = new User();
        test.setFirstName("TestU");
        test.setRole(Role.ADMIN);
        map.put(1, test);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Collection<User> values = map.values();
        String json = new Gson().toJson(values);
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) {
        try (BufferedReader reader = req.getReader()) {
            String jsonAsString = reader.lines().collect(Collectors.joining());
            User user = new Gson().fromJson(jsonAsString, User.class);
            map.put(new Random().nextInt(), user);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}

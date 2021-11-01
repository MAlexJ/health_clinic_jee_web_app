package com.malex.controllers.admin;

import com.malex.controllers.AbstractController;
import com.malex.entities.Category;
import com.malex.entities.Role;
import com.malex.entities.User;
import com.malex.entities.dto.UserDto;
import com.malex.services.CategoryService;
import com.malex.services.RoleService;
import com.malex.services.UserService;
import com.malex.services.impl.CategoryServiceImpl;
import com.malex.services.impl.RoleServiceImpl;
import com.malex.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/rest/*")
public class AdminRestController extends AbstractController {

    private final UserService userService = new UserServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();

        String json = null;
        if (requestURI.contains("/clients")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            if (firstName != null || lastName != null) {
                List<User> users = userService.getUsersByNames(firstName, lastName, Role.CLIENT);
                json = mapToString(users);
            } else {
                UserDto clients = userService.getUsersByRoles(Role.CLIENT);
                json = mapToString(clients);
            }
        }

        if (requestURI.contains("/doctors")) {
            String categoryId = request.getParameter("category");
            if (categoryId != null) {
                List<User> doctors = userService.getUsersByRolesAndCategory(Role.DOCTOR, categoryId);
                json = mapToString(doctors);
            } else {
                UserDto doctors = userService.getUsersByRoles(Role.DOCTOR);
                json = mapToString(doctors);
            }
        }

        if (requestURI.contains("/categories")) {
            List<Category> categories = categoryService.getCategories();
            json = mapToString(categories);
        }

        if (requestURI.contains("/users")) {
            String roleId = request.getParameter("role");
            if (roleId != null) {
                UserDto users = userService.getUsersByRoles(Role.valueOf(roleId.toUpperCase()));
                json = mapToString(users);
            } else {
                UserDto users = userService.getUsersByRoles(Role.DOCTOR);
                json = mapToString(users);
            }
        }

        if (requestURI.contains("/role")) {
            List<Role> roles = roleService.getNotAdminRole();
            json = mapToString(roles);
        }

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        if (requestURI.contains("appointments")){
            String clientId = req.getParameter("client_id");
            String doctorId = req.getParameter("doctor_id");
            String date = req.getParameter("date");
            String time = req.getParameter("time");
            String comments = req.getParameter("comments");
            //Save to appointment statement
        }
    }
}

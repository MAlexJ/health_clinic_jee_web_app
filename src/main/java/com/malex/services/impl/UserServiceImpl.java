package com.malex.services.impl;

import com.malex.dao.UserDao;
import com.malex.dao.impl.UserDaoImpl;
import com.malex.entities.Role;
import com.malex.entities.User;
import com.malex.entities.dto.UserDto;
import com.malex.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    // TODO MySQL impl
    public static Map<String, User> database = new HashMap<>();

    static {
        User admin = new User();
        admin.setFirstName("admin");
        admin.setPassword("12345");
        admin.setRole(Role.ADMIN);
        database.put(admin.getFirstName() + admin.getPassword(), admin);

        User client = new User();
        client.setFirstName("client");
        client.setPassword("12345");
        client.setRole(Role.CLIENT);
        database.put(client.getFirstName() + client.getPassword(), client);
    }


    @Override
    public User getUserByCredentials(String username, String password) {
        String primaryKey = username + password;
        // SELECT id,role,username FROM USER_TABLES WHERE name = 'username 'AND password = 'password'
        return database.get(primaryKey);
    }


    @Override
    public UserDto getUsersByRoles(Role role) {
        List<User> users = userDao.getUserByRole(role);
        int count = userDao.getCountOfUserByRole(role);
        UserDto userDto = new UserDto();
        userDto.setUsers(users);
        userDto.setCountOfUsers(count);
        return userDto;
    }

    @Override
    public List<User> getUsersByNames(String firstName, String lastName, Role role) {
        List<User> users = userDao.getUsersByNames(firstName, lastName, role);

        // part of logic
        return users;
    }

    @Override
    public List<User> getUsersByRolesAndCategory(Role role, String categoryId) {
        List<User> users = userDao.getUsersByRolesAndCategory(role, categoryId);

        // part of logic
        return users;
    }
}

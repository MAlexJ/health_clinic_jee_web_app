package com.malex.dao;

import com.malex.entities.Role;
import com.malex.entities.User;

import java.util.List;

public interface UserDao {

    User getUserByCredentials(String userName, String password);

    List<User> getUserByRole(Role role);

    List<User> getUsersByNames(String firstName, String lastName, Role role);

    List<User> getUsersByRolesAndCategory(Role role, String categoryId);

    int getCountOfUserByRole(Role role);

}

package com.malex.services;

import com.malex.entities.Role;
import com.malex.entities.User;
import com.malex.entities.dto.UserDto;

import java.util.List;

public interface UserService {

    User getUserByCredentials(String username, String password);

    UserDto getUsersByRoles(Role role);

    List<User> getUsersByNames(String firstName, String lastName, Role role);

    List<User> getUsersByRolesAndCategory(Role role, String categoryId);

}

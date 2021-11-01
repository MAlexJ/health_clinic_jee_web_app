package com.malex.services;

import com.malex.entities.Role;

import java.util.List;

public interface RoleService {

    List<Role> getNotAdminRole();
}

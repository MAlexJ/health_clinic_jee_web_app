package com.malex.services.impl;

import com.malex.entities.Role;
import com.malex.services.RoleService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleServiceImpl implements RoleService {

    @Override
    public List<Role> getNotAdminRole() {
        return Stream.of(Role.values())
                .filter(role -> role != Role.ADMIN)
                .filter(role -> role != Role.UNDEFINED)
                .collect(Collectors.toList());
    }
}

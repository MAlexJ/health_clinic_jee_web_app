package com.malex.entities.dto;

import com.malex.entities.User;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }

    public void setCountOfUsers(int countOfUsers) {
        this.countOfUsers = countOfUsers;
    }

    private int countOfUsers;
}

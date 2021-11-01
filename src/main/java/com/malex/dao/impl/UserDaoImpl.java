package com.malex.dao.impl;

import com.malex.dao.UserDao;
import com.malex.dao.database.Database;
import com.malex.entities.Role;
import com.malex.entities.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    @Override
    public User getUserByCredentials(String userName, String password) {
        return null;
    }


    @Override
    public List<User> getUserByRole(Role role) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, first_name, second_name, birthday, phone, room_number FROM users WHERE role_id IN (SELECT role.id FROM role WHERE role.name = ? )";
        try (PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, role.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setPhone(rs.getString("phone"));
                user.setRoom(rs.getString("room_number"));
                user.setDate(Optional.ofNullable(rs.getDate("birthday"))
                        .map(Date::toLocalDate)
                        .orElse(null));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> getUsersByNames(String firstName, String lastName, Role role) {
        List<User> users = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT id, first_name, second_name, birthday, phone, room_number ")
                .append("FROM users WHERE role_id IN (SELECT role.id FROM role WHERE role.name = ?) ")
                .append("AND first_name LIKE ? ")
                .append("AND second_name LIKE ?");

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = Database.getConnection().prepareStatement(new String(sqlBuilder))) {
            preparedStatement.setString(1, role.toString());
            preparedStatement.setString(2, "%".concat(firstName).concat("%"));
            preparedStatement.setString(3, "%".concat(lastName).concat("%"));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setPhone(rs.getString("phone"));
                user.setRoom(rs.getString("room_number"));
                user.setDate(Optional.ofNullable(rs.getDate("birthday"))
                        .map(Date::toLocalDate)
                        .orElse(null));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Optional.ofNullable(rs)
                    .ifPresent(r -> {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
        return users;
    }

    @Override
    public List<User> getUsersByRolesAndCategory(Role role, String categoryId) {
        List<User> users = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder("SELECT id, first_name, second_name, birthday, phone, room_number ")
                .append("FROM users WHERE role_id IN (SELECT role.id FROM role WHERE role.name = ?) ")
                .append("AND category_id = ?");
        ResultSet rs = null;
        try (PreparedStatement preparedStatement = Database.getConnection().prepareStatement(new String(sqlBuilder))) {
            preparedStatement.setString(1, role.toString());
            preparedStatement.setString(2, categoryId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setSecondName(rs.getString("second_name"));
                user.setPhone(rs.getString("phone"));
                user.setRoom(rs.getString("room_number"));
                user.setDate(Optional.ofNullable(rs.getDate("birthday"))
                        .map(Date::toLocalDate)
                        .orElse(null));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Optional.ofNullable(rs)
                    .ifPresent(r -> {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
        return users;
    }

    @Override
    public int getCountOfUserByRole(Role role) {
        String sql = "select count(*) from users where role_id in (select r.id from role as r where r.name = ?)";
        ResultSet rs = null;
        int count = 0;
        try (PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, role.toString());
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count =  rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        } finally {
            Optional.ofNullable(rs)
                    .ifPresent(r -> {
                        try {
                            r.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    });
        }
        return count;
    }
}

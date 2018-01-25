package com.dao;

import com.models.User;

import java.util.List;

public interface UserDao {
    User findUserByLogin(String login);
    void add(Object object);
    List<User> showAll(String role);
    void updateAdmin(List<User> users);

}

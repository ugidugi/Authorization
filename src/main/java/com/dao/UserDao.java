package com.dao;

import com.models.User;

public interface UserDao {
    User findUserByLogin(String login);
    void add(Object object);
}

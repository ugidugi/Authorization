package com;

import com.dao.impl.UserDaoImpl;
import com.dao.impl.UserRoleDaoImpl;
import com.models.User;
import com.models.UserRole;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setLogin("dugi");
        user.setFirstName("Sergry");
        user.setLastName("Kurinniy");
        user.setMail("serkurinniy@gmail.com");
        user.setPassword(passwordEncoder.encode("pass"));
        user.setEnable(true);
        userDao.add(user);


    }
}

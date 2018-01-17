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
        for(User user: userDao.showAll("ROLE_USER")){
            System.out.println(user);
            System.out.println(user.getPassword());
            for(UserRole userRole: user.getRoles()){
                System.out.println("Role: " + userRole.getRole());
            }
        }
    }
}

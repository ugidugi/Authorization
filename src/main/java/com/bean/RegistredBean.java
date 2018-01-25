package com.bean;



import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.models.User;
import com.models.UserRole;
import com.util.HibernateUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "register_bean")
@ViewScoped
public class RegistredBean {

    private User user = new User();
    private UserRole  role = new UserRole("ROLE_USER",user);


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String add(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable(true);
        UserDao userDao = new UserDaoImpl();
        userDao.add(user);
        return "customLogin.xhtml";
    }
}

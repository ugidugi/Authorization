package com.bean;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "show_users_bean")
@ViewScoped
public class ShowUsersBean {
    @Autowired
    private static UserDao userDao;

    private List<User> show_admins;

    public ShowUsersBean(){
        show_admins = userDao.showAll("ROLE_ADMIN");
    }

    public List<User> getShow_admins() {
        return show_admins;
    }

    public void setShow_admins(List<User> show_admins) {
        this.show_admins = show_admins;
    }
}

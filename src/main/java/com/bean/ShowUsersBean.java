package com.bean;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.models.User;
import com.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.util.List;

@ManagedBean(name = "show_users_bean")
@SessionScoped
public class ShowUsersBean {

    private String userLogin;
    private User user;

    private List<User> show_admins;

    public ShowUsersBean(){
        show_admins = new UserDaoImpl().showAll(null);
    }

    public List<User> getShow_admins() {
        return show_admins;
    }

    public String admin_setting(){
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.updateAdmin(show_admins);
        return "\\admin\\admins.xhtml";
    }

    public boolean check_admin(User user){
        for(UserRole role: user.getRoles()){
            if(role.getRole().equals("ROLE_ADMIN")){
                return true;
            }
        }
        return false;
    }
    public String searshUser(String login){
        user = new UserDaoImpl().findUserByLogin(login);
        return "\\admin\\users.xhtml";
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShow_admins(List<User> show_admins) {
        this.show_admins = show_admins;
    }
}

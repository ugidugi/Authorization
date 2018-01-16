package com.models;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="users", catalog="dbase")
public class User {
    private long user_id;
    private String login;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private Date birth;
    private boolean enable;
    private Set<UserRole> roles = new HashSet<UserRole>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="user_id")
    public long getUser_id() {
        return user_id;
    }
    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Column(name="login")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name="firstName")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="lastName")
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="mail")
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="birth")
    public Date getBirth() {
        return birth;
    }
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    public Set<UserRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Column(name = "enable")
    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString(){
        return  "User login: " + login + "\n"
                + "First name: " + firstName+ "\n"
                +"Last name: " + lastName + "\n"
                +"Mail: " + mail + "\n"
                +"Birthday: " + convertDate(birth) + "\n"
                +"Enable: " + enable;
    }

    private String convertDate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        if(birth==null){
            return "NO";
        }
        return format.format(date);
    }
}